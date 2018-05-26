/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rispondidomande;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author Matteo Franzil
 */
public class Domanda {

    private static int domandetotali = 36;
    public static final int rispostemassime = 6;

    private String domanda, rispostadata, rispostacorretta;
    private ArrayList<String> risposte;
    private int id, numerorisposte;

    private static final ArrayList<Integer> domandedisponibili = new ArrayList<>();

    public Domanda() throws UnsupportedEncodingException {
        int id_temp = domandedisponibili.get(--domandetotali);

        // Inizializzo lo scanner per evitare errori di codifica errata
        InputStream in = getClass().getResourceAsStream("domande/" + String.valueOf(id_temp) + ".txt");
        Locale loc = new Locale("it", "IT");
        Scanner scanner = new Scanner(in);
        scanner.useLocale(loc);

        id = scanner.nextInt();
        numerorisposte = scanner.nextInt();
        rispostacorretta = RispondiDomande.intToLetter(scanner.nextInt(), true);

        // Salto una riga per passare alla lettura della domanda
        scanner.nextLine();
        domanda = new String(scanner.nextLine().getBytes(), "utf-8");

        risposte = new ArrayList<>();

        for (int i = 0; i < rispostemassime; i++) {
            try {
                risposte.add(new String(scanner.nextLine().getBytes(), "utf-8"));
            } catch (NullPointerException e) {
                System.err.println("Error while reading answers (index: " + i + ")");
            }
        }

        rispostadata = null;
    }

    public static void setAvailableQuestions() {
        for (int i = 0; i < domandetotali; i++) {
            domandedisponibili.add(i);
        }
        Collections.shuffle(domandedisponibili);
    }

    public boolean checkCorrect() {
        if (rispostacorretta.equals(rispostadata)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Domanda #" + id + " " + domanda + "\n" + numerorisposte + " risposte possibili";
    }

    public String getDomanda() {
        return domanda;
    }

    public ArrayList<String> getRisposte() {
        return risposte;
    }

    public int getId() {
        return id;
    }

    public String getRispostacorretta() {
        return rispostacorretta;
    }

    public String getRispostadata() {
        return rispostadata;
    }

    public void setRispostadata(String rispostadata) {
        this.rispostadata = rispostadata;
    }
}
