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
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Matteo Franzil
 */
public class Domanda {

    private String domanda, risposta_a, risposta_b, risposta_c, risposta_d;
    private String rispostadata, rispostacorretta;
    private int id, numerorisposte;

    private static int domandetotali = 164;
    private static final ArrayList<Integer> domandedisponibili = new ArrayList<>();

    public Domanda() throws UnsupportedEncodingException {
        int id_temp = domandedisponibili.get(--domandetotali);

        InputStream in = getClass().getResourceAsStream("domande/" + String.valueOf(id_temp) + ".txt");
        Locale loc = new Locale("it", "IT");
        Scanner scanner = new Scanner(in);
        scanner.useLocale(loc);
        id = scanner.nextInt();
        numerorisposte = scanner.nextInt();
        setRispostacorretta(scanner.nextInt());
        scanner.nextLine();
        domanda = new String(scanner.nextLine().getBytes(), "utf-8");
        
        risposta_a = new String(scanner.nextLine().getBytes(), "utf-8");
        risposta_b = new String(scanner.nextLine().getBytes(), "utf-8");
        risposta_c = new String(scanner.nextLine().getBytes(), "utf-8");
        try {
            risposta_d = new String(scanner.nextLine().getBytes(), "utf-8");
        } catch (Exception e) {
            risposta_d = null;
        }
        rispostadata = null;
    }

    public static void setAvailableQuestions() {
        for (int i = 0; i < domandetotali; i++) {
            domandedisponibili.add(i);
        }
        Collections.shuffle(domandedisponibili, new Random());
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

    public String getRisposta_a() {
        return risposta_a;
    }

    public String getRisposta_b() {
        return risposta_b;
    }

    public String getRisposta_c() {
        return risposta_c;
    }

    public String getRisposta_d() {
        return risposta_d;
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

    private void setRispostadataInt(String rispostadata) {
        switch (Integer.parseInt(rispostadata)) {
            case 1:
                this.rispostadata = "A";
                break;
            case 2:
                this.rispostadata = "B";
                break;
            case 3:
                this.rispostadata = "C";
                break;
            case 4:
                this.rispostadata = "D";
                break;
            default:
                this.rispostadata = rispostadata;
                break;
        }
    }

    private void setRispostacorretta(int rispostacorretta) {
        switch (rispostacorretta) {
            case 1:
                this.rispostacorretta = "A";
                break;
            case 2:
                this.rispostacorretta = "B";
                break;
            case 3:
                this.rispostacorretta = "C";
                break;
            case 4:
                this.rispostacorretta = "D";
                break;
            default:
                this.rispostacorretta = String.valueOf(rispostacorretta);
                break;
        }
    }
}
