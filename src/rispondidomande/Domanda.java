/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rispondidomande;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @author Matteo Franzil
 */
public class Domanda {

    private static final LinkedList<Integer> domandedisponibili = new LinkedList<>();
    private static int maxopzioni = 0;
    private String domanda, rispostadata, rispostacorretta;
    private ArrayList<String> risposte;
    private int id, numerorisposte;

    /**
     * Costruttore standard per una Domanda. All'inizio, un ArrayList viene
     * popolato con gli id possibili delle domande. Ogni volta che viene
     * invocato il costruttore, viene pescato e rimosso un ID random
     * dall'ArrayList, e una nuova Domanda con quell'id viene generata.
     *
     * @throws UnsupportedEncodingException
     */
    public Domanda() throws UnsupportedEncodingException {
        if (domandedisponibili.isEmpty()) {
            setAvailableQuestions();
        }

        int id_temp = domandedisponibili.pollLast();

        // Inizializzo lo scanner per evitare errori di codifica errata
        InputStream in = getClass().getResourceAsStream(Common.PATH + String.valueOf(id_temp) + ".txt");
        Locale loc = new Locale("it", "IT");
        Scanner scanner = new Scanner(in);
        scanner.useLocale(loc);

        // Vedi README.md per informazioni sulla sintassi dei file scansionati
        try {
            id = scanner.nextInt();
            numerorisposte = scanner.nextInt();
            rispostacorretta = Common.intToLetter(scanner.nextInt(), true);
        } catch (InputMismatchException e) {
            System.err.println("InputMismatchException caused by " + Common.PATH + String.valueOf(id_temp) + ".txt");
        }

        // Salto una riga per passare alla lettura della domanda
        scanner.nextLine();
        domanda = new String(scanner.nextLine().getBytes(), "utf-8");

        risposte = new ArrayList<>();
        for (int i = 0; i < numerorisposte; i++) {
            try {
                risposte.add(new String(scanner.nextLine().getBytes(), "utf-8"));
            } catch (NullPointerException e) {
                System.err.println("Error while reading answers (index: " + i + ")");
            } catch (NoSuchElementException e) {
                System.err.println("No line found at file" + Common.PATH + String.valueOf(id_temp) + ".txt");
            }
        }
        rispostadata = null;

        if (numerorisposte > maxopzioni) {
            maxopzioni = numerorisposte;
        }
    }

    public static int getMaxopzioni() {
        return maxopzioni;
    }

    private void setAvailableQuestions() {
        for (int i = 0; i < Common.DOMANDETOTALI; i++) {
            domandedisponibili.add(i);
        }
        Collections.shuffle(domandedisponibili);
    }

    /**
     * Metodo per controllare la correttezza di una risposta data dall'utente
     * alla domanda.
     *
     * @return Un boolean che rappresenta la correttezza o meno della risposta.
     */
    public boolean checkCorrect() {
        return rispostacorretta.equals(rispostadata);
    }

    /**
     * @return Una String contenente la domanda.
     */
    public String getDomanda() {
        return domanda;
    }

    /**
     * @return L'elenco di risposte ammesse alla domanda.
     */
    public ArrayList<String> getRisposte() {
        return risposte;
    }

    /**
     * @return L'ID univoco della domanda.
     */
    public int getId() {
        return id;
    }

    /**
     * @return Una String contenente la risposta corretta alla domanda.
     */
    public String getRispostacorretta() {
        return rispostacorretta;
    }

    /**
     * @return Una String contenente la risposta data alla domanda.
     */
    public String getRispostadata() {
        return rispostadata;
    }

    /**
     * Metodo per impostare la risposta data.
     *
     * @param rispostadata La risposta fornita dall'utente.
     */
    public void setRispostadata(String rispostadata) {
        this.rispostadata = rispostadata;
    }

    public int getNumerorisposte() {
        return numerorisposte;
    }
}
