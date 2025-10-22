package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private final InputStream originalSystemIn = System.in;
    private final PrintStream originalSystemOut = System.out;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        Main.aufgaben.clear();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() {
        System.setIn(originalSystemIn);
        System.setOut(originalSystemOut);
        Main.resetScanner();
    }

    private void simuliereEingabe(String input) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        Main.resetScanner();
    }

    @Test
    void testListeIstLeer() {
        assertEquals(0, Main.aufgaben.size());
        assertTrue(Main.aufgaben.isEmpty());
    }

    @Test
    void testAufgabeHinzufuegen() {
        simuliereEingabe("Einkaufen gehen\n");
        Main.aufgabeHinzufuegen();
        assertEquals(1, Main.aufgaben.size());
        assertEquals("Einkaufen gehen", Main.aufgaben.get(0));
        assertTrue(outputStream.toString().contains("Aufgabe wurde hinzugefügt!"));
    }

    @Test
    void testAufgabeHinzufuegenMehrere() {
        simuliereEingabe("Hausaufgaben machen\n");
        Main.aufgabeHinzufuegen();
        simuliereEingabe("Sport treiben\n");
        Main.aufgabeHinzufuegen();
        assertEquals(2, Main.aufgaben.size());
        assertEquals("Hausaufgaben machen", Main.aufgaben.get(0));
        assertEquals("Sport treiben", Main.aufgaben.get(1));
    }

    @Test
    void testAlleAufgabenAnzeigenLeer() {
        Main.alleAufgabenAnzeigen();
        String output = outputStream.toString();
        assertTrue(output.contains("Keine Aufgaben vorhanden"));
    }

    @Test
    void testAlleAufgabenAnzeigenMitAufgaben() {
        Main.aufgaben.add("Aufgabe 1");
        Main.aufgaben.add("Aufgabe 2");
        Main.alleAufgabenAnzeigen();
        String output = outputStream.toString();
        assertTrue(output.contains("=== Deine Aufgaben ==="));
        assertTrue(output.contains("1. Aufgabe 1"));
        assertTrue(output.contains("2. Aufgabe 2"));
    }

    @Test
    void testAufgabeBearbeiten() {
        Main.aufgaben.add("Alte Aufgabe");
        simuliereEingabe("1\nNeue Aufgabe\n");
        Main.aufgabeBearbeiten();
        assertEquals(1, Main.aufgaben.size());
        assertEquals("Neue Aufgabe", Main.aufgaben.get(0));
        assertTrue(outputStream.toString().contains("Aufgabe wurde bearbeitet"));
    }

    @Test
    void testAufgabeBearbeitenMitMehrerenAufgaben() {
        Main.aufgaben.add("Aufgabe 1");
        Main.aufgaben.add("Aufgabe 2");
        Main.aufgaben.add("Aufgabe 3");
        simuliereEingabe("2\nGeänderte Aufgabe 2\n");
        Main.aufgabeBearbeiten();
        assertEquals(3, Main.aufgaben.size());
        assertEquals("Aufgabe 1", Main.aufgaben.get(0));
        assertEquals("Geänderte Aufgabe 2", Main.aufgaben.get(1));
        assertEquals("Aufgabe 3", Main.aufgaben.get(2));
    }

    @Test
    void testAufgabeBearbeitenUngueltigeNummer() {
        Main.aufgaben.add("Test Aufgabe");
        simuliereEingabe("5\nSollte nicht geändert werden\n");
        Main.aufgabeBearbeiten();
        assertEquals(1, Main.aufgaben.size());
        assertEquals("Test Aufgabe", Main.aufgaben.get(0));
        assertTrue(outputStream.toString().contains("Ungültige Nummer"));
    }

    @Test
    void testAufgabeBearbeitenLeereListeZeigt() {
        Main.aufgabeBearbeiten();
        assertEquals(0, Main.aufgaben.size());
        assertTrue(outputStream.toString().contains("Keine Aufgaben vorhanden"));
    }

    @Test
    void testAufgabeLoeschen() {
        Main.aufgaben.add("Zu löschen");
        simuliereEingabe("1\n");
        Main.aufgabeLoeschen();
        assertEquals(0, Main.aufgaben.size());
        assertTrue(Main.aufgaben.isEmpty());
        assertTrue(outputStream.toString().contains("wurde gelöscht!"));
    }

    @Test
    void testAufgabeLoeschenMitMehrerenAufgaben() {
        Main.aufgaben.add("Aufgabe 1");
        Main.aufgaben.add("Aufgabe 2");
        Main.aufgaben.add("Aufgabe 3");
        simuliereEingabe("2\n");
        Main.aufgabeLoeschen();
        assertEquals(2, Main.aufgaben.size());
        assertEquals("Aufgabe 1", Main.aufgaben.get(0));
        assertEquals("Aufgabe 3", Main.aufgaben.get(1));
        assertTrue(outputStream.toString().contains("Aufgabe 2 wurde gelöscht!"));
    }

    @Test
    void testAufgabeLoeschenUngueltigeNummer() {
        Main.aufgaben.add("Test Aufgabe");
        simuliereEingabe("0\n");
        Main.aufgabeLoeschen();
        assertEquals(1, Main.aufgaben.size());
        assertEquals("Test Aufgabe", Main.aufgaben.get(0));
        assertTrue(outputStream.toString().contains("Ungültige Nummer"));
    }

    @Test
    void testAufgabeLoeschenZuGrosseNummer() {
        Main.aufgaben.add("Test Aufgabe");
        simuliereEingabe("10\n");
        Main.aufgabeLoeschen();
        assertEquals(1, Main.aufgaben.size());
        assertTrue(outputStream.toString().contains("Ungültige Nummer"));
    }

    @Test
    void testKomplexerWorkflow() {
        simuliereEingabe("Einkaufen\n");
        Main.aufgabeHinzufuegen();
        simuliereEingabe("Hausaufgaben\n");
        Main.aufgabeHinzufuegen();
        simuliereEingabe("Sport\n");
        Main.aufgabeHinzufuegen();
        assertEquals(3, Main.aufgaben.size());
        simuliereEingabe("2\nHausaufgaben erledigt\n");
        Main.aufgabeBearbeiten();
        assertEquals("Hausaufgaben erledigt", Main.aufgaben.get(1));
        simuliereEingabe("1\n");
        Main.aufgabeLoeschen();
        assertEquals(2, Main.aufgaben.size());
        assertEquals("Hausaufgaben erledigt", Main.aufgaben.get(0));
        assertEquals("Sport", Main.aufgaben.get(1));
    }

    @Test
    void testZeigeMenu() {
        Main.zeigeMenu();
        String output = outputStream.toString();
        assertTrue(output.contains("Was willst du momentan machen?"));
        assertTrue(output.contains("1: Aufgabe hinzufügen"));
        assertTrue(output.contains("2: Alle Aufgaben anzeigen"));
        assertTrue(output.contains("3: Aufgabe bearbeiten"));
        assertTrue(output.contains("4: Aufgabe löschen"));
        assertTrue(output.contains("5: Programm beenden"));
        assertTrue(output.contains("Deine Wahl:"));
    }

    @Test
    void testLeerzeichenInAufgaben() {
        simuliereEingabe("   Aufgabe mit Leerzeichen   \n");
        Main.aufgabeHinzufuegen();
        assertEquals(1, Main.aufgaben.size());
        assertEquals("   Aufgabe mit Leerzeichen   ", Main.aufgaben.get(0));
    }

    @Test
    void testSonderzeichenInAufgaben() {
        simuliereEingabe("Aufgabe mit Ümlaut äöü & Sonderzeichen!@#\n");
        Main.aufgabeHinzufuegen();
        assertEquals(1, Main.aufgaben.size());
        assertEquals("Aufgabe mit Ümlaut äöü & Sonderzeichen!@#", Main.aufgaben.get(0));
    }

    @Test
    void testMehrereAufgabenBearbeiten() {
        Main.aufgaben.add("Aufgabe A");
        Main.aufgaben.add("Aufgabe B");
        Main.aufgaben.add("Aufgabe C");
        simuliereEingabe("1\nNeue Aufgabe A\n");
        Main.aufgabeBearbeiten();
        simuliereEingabe("3\nNeue Aufgabe C\n");
        Main.aufgabeBearbeiten();
        assertEquals("Neue Aufgabe A", Main.aufgaben.get(0));
        assertEquals("Aufgabe B", Main.aufgaben.get(1));
        assertEquals("Neue Aufgabe C", Main.aufgaben.get(2));
    }

    @Test
    void testMehrereAufgabenLoeschen() {
        Main.aufgaben.add("Aufgabe 1");
        Main.aufgaben.add("Aufgabe 2");
        Main.aufgaben.add("Aufgabe 3");
        Main.aufgaben.add("Aufgabe 4");
        simuliereEingabe("2\n");
        Main.aufgabeLoeschen();
        assertEquals(3, Main.aufgaben.size());
        simuliereEingabe("1\n");
        Main.aufgabeLoeschen();
        assertEquals(2, Main.aufgaben.size());
        assertEquals("Aufgabe 3", Main.aufgaben.get(0));
        assertEquals("Aufgabe 4", Main.aufgaben.get(1));
    }
}