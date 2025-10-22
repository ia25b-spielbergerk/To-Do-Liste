package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static ArrayList<String> aufgaben = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Willkommen bei deiner To-Do-Liste");

        boolean programmLaueft = true;

        while (programmLaueft) {
            zeigeMenu();
            int auswahl = scanner.nextInt();
            scanner.nextLine();

            if (auswahl == 1) {
                aufgabeHinzufuegen();
            } else if (auswahl == 2) {
                alleAufgabenAnzeigen();
            } else if (auswahl == 3) {
                aufgabeBearbeiten();
            } else if (auswahl == 4) {
                aufgabeLoeschen();
            } else if (auswahl == 5) {
                programmLaueft = false;
                System.out.println("Programm wird beendet");
            } else {
                System.out.println("Ungültige Auswahl");
            }

            System.out.println();
        }

    }

    private static void zeigeMenu() {
        System.out.println("Was willst du momentan machen?");
        System.out.println("1: Aufgabe hinzufügen");
        System.out.println("2: Alle Aufgaben anzeigen");
        System.out.println("3: Aufgabe bearbeiten");
        System.out.println("4: Aufgabe löschen");
        System.out.println("5: Programm beenden");
        System.out.print("Deine Wahl: ");
    }

    private static void aufgabeHinzufuegen() {
        System.out.print("Gib die neue Aufgabe ein: ");
        String neueAufgabe = scanner.nextLine();
        aufgaben.add(neueAufgabe);
        System.out.println("Aufgabe wurde hinzugefügt!");
    }

    private static void alleAufgabenAnzeigen() {
        if (aufgaben.isEmpty()) {
            System.out.println("Keine Aufgaben vorhanden.");
        } else {
            System.out.println("\n=== Deine Aufgaben ===");
            for (int i = 0; i < aufgaben.size(); i++) {
                System.out.println((i + 1) + ". " + aufgaben.get(i));
            }
        }
    }

    private static void aufgabeBearbeiten() {
        alleAufgabenAnzeigen();

        if (!aufgaben.isEmpty()) {
            System.out.println("Welche Task willst du bearbeiten: ");
            int nummer = scanner.nextInt();
            scanner.nextLine();
        }
    }

    private static void aufgabeLoeschen() {

    }
}