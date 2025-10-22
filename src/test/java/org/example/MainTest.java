package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private ArrayList<String> aufgaben;

    @BeforeEach
    void setUp() {
        aufgaben = new ArrayList<>();
    }

    @Test
    void testListeIstLeer() {
        assertEquals(0, aufgaben.size());
        assertTrue(aufgaben.isEmpty());
    }

    @Test
    void aufgabeHinzufuegen() {
        aufgaben.add("Test");
        assertEquals(1, aufgaben.size());
        aufgaben.add("Hallo");
        assertEquals(2, aufgaben.size());
    }

    @Test
    void alleAufgabenAnzeigen() {
        aufgaben.add("Aufgabe");
        assertEquals(1, aufgaben.size());
        aufgaben.add("Test");
        assertEquals(2, aufgaben.size());
    }

    @Test
    void aufgabeBearbeiten() {
        aufgaben.add("Alt");
        aufgaben.set(0, "Neu");
        assertEquals("Neu", aufgaben.get(0));
    }

    @Test
    void aufgabeLoeschen() {
        aufgaben.add("Löschen");
        aufgaben.remove(0);
        assertEquals(0, aufgaben.size());

        aufgaben.add("Test");
        aufgaben.add("Hallo");
        aufgaben.remove(0);
        assertEquals(1, aufgaben.size());
    }
}