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
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author Matteo Franzil
 */
public class Domanda {

    private String domanda, rispostadata, rispostacorretta;
    private ArrayList<String> risposte;
    private int id, numerorisposte;
    
    private static int indice_domande;
    private static final ArrayList<Integer> domandedisponibili = new ArrayList<>();

    /**
     * Costruttore standard per una Domanda. All'inizio, un ArrayList viene popolato con gli id possibili delle domande. Ogni volta che 
     * viene invocato il costruttore, viene pescato e rimosso un ID random dall'ArrayList, e una nuova Domanda con quell'id viene generata.
     * @throws UnsupportedEncodingException
     */
    public Domanda() throws UnsupportedEncodingException {
        System.out.println(domandedisponibili.isEmpty());
        System.out.println(indice_domande);
        if (domandedisponibili.isEmpty()) {
            setAvailableQuestions();
        }        
        
        int id_temp = domandedisponibili.get(--indice_domande);

        // Inizializzo lo scanner per evitare errori di codifica errata
        InputStream in = getClass().getResourceAsStream(Common.PATH + String.valueOf(id_temp) + ".txt");
        Locale loc = new Locale("it", "IT");
        Scanner scanner = new Scanner(in);
        scanner.useLocale(loc);
        
        // Vedi README.md per informazioni sulla sintassi dei file scansionati
        id = scanner.nextInt();
        numerorisposte = scanner.nextInt();
        rispostacorretta = Common.intToLetter(scanner.nextInt(), true);

        // Salto una riga per passare alla lettura della domanda
        scanner.nextLine();
        domanda = new String(scanner.nextLine().getBytes(), "utf-8");
        
        risposte = new ArrayList<>();
        for (int i = 0; i < Common.MAXRISPOSTE; i++) {
            try {
                risposte.add(new String(scanner.nextLine().getBytes(), "utf-8"));
            } catch (NullPointerException e) {
                System.err.println("Error while reading answers (index: " + i + ")");
            } catch (NoSuchElementException e) {
                System.err.println("No line found at file" + Common.PATH + String.valueOf(id_temp) + ".txt");
            }
        }
        rispostadata = null;
    }

     private void setAvailableQuestions() {
        indice_domande = Common.DOMANDETOTALI;
        for (int i = 0; i < Common.DOMANDETOTALI; i++) {
            domandedisponibili.add(i);
        }
        Collections.shuffle(domandedisponibili);
    }

    /**
     * Metodo per controllare la correttezza di una risposta data dall'utente alla domanda.
     * @return Un boolean che rappresenta la correttezza o meno della risposta.
     */
    public boolean checkCorrect() {
        if (rispostacorretta.equals(rispostadata)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @return
     */
    public String getDomanda() {
        return domanda;
    }

    /**
     *
     * @return
     */
    public ArrayList<String> getRisposte() {
        return risposte;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @return
     */
    public String getRispostacorretta() {
        return rispostacorretta;
    }

    /**
     *
     * @return
     */
    public String getRispostadata() {
        return rispostadata;
    }

    public int getNumerorisposte() {
        return numerorisposte;
    }
    
     /**
     *
     * @param rispostadata
     */
    public void setRispostadata(String rispostadata) {
        this.rispostadata = rispostadata;
    }
}
