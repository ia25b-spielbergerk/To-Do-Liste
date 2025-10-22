package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static ArrayList<String> aufgaben = new ArrayList<>();
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

    public static void zeigeMenu() {
        System.out.println("Was willst du momentan machen?");
        System.out.println("1: Aufgabe hinzufügen");
        System.out.println("2: Alle Aufgaben anzeigen");
        System.out.println("3: Aufgabe bearbeiten");
        System.out.println("4: Aufgabe löschen");
        System.out.println("5: Programm beenden");
        System.out.print("Deine Wahl: ");
    }

    public static void aufgabeHinzufuegen() {
        System.out.print("Gib die neue Aufgabe ein: ");
        String neueAufgabe = scanner.nextLine();
        aufgaben.add(neueAufgabe);
        System.out.println("Aufgabe wurde hinzugefügt!");
    }

    public static void alleAufgabenAnzeigen() {
        if (aufgaben.isEmpty()) {
            System.out.println("Keine Aufgaben vorhanden.");
        } else {
            System.out.println();
            System.out.println("=== Deine Aufgaben ===");
            for (int i = 0; i < aufgaben.size(); i++) {
                System.out.println((i + 1) + ". " + aufgaben.get(i));
            }
        }
    }

    public static void aufgabeBearbeiten() {
        alleAufgabenAnzeigen();

        if (!aufgaben.isEmpty()) {
            System.out.println("Welche Task willst du bearbeiten: ");
            int nummer = scanner.nextInt();
            scanner.nextLine();

            if (nummer > 0 && nummer <= aufgaben.size()) {
                System.out.println("Gib eine neue Aufgabe ein: ");
                String neueAufgabe = scanner.nextLine();
                aufgaben.set(nummer - 1, neueAufgabe);
                System.out.println("Aufgabe wurde bearbeitet");
            } else {
                System.out.println("Ungültige Nummer");
            }
        }
    }

    public static void aufgabeLoeschen() {
        alleAufgabenAnzeigen();

        if (!aufgaben.isEmpty()) {
            System.out.print("Welche Aufgabe möchtest du löschen: ");
            int nummer = scanner.nextInt();
            scanner.nextLine();

            if (nummer > 0 && nummer <= aufgaben.size()) {
                String geloeschteAufgabe = aufgaben.remove(nummer - 1);
                System.out.println();
                System.out.println("Aufgabe " + geloeschteAufgabe + " wurde gelöscht!");
            } else {
                System.out.println("Ungültige Nummer");
            }
        }
    }
}
