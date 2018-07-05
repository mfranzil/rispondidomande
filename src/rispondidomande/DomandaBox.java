/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rispondidomande;

import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Matteo Franzil
 */
public class DomandaBox extends TextArea {

    private int domandacorrente;
    private LinkedList<Domanda> domande;

    private ResultWindow results;

    /**
     * Costruttore standard. Ritorna una TextArea dove verranno scritte le
     * domande.
     */
    public DomandaBox() {
        domande = new LinkedList<>();
        domandacorrente = -1;

        setWrapText(true);
        setEditable(false);
        setPrefWidth(500);
        setPrefHeight(250);

        setMaxWidth(1500);
        setMaxHeight(1500);

        for (int i = 0; i < Common.MAXDOMANDE; i++) {
            try {
                Domanda tmp = new Domanda();
                domande.add(tmp);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(DomandaBox.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        appendText("Ciao! Per iniziare, premi il pulsante Successivo. Il test è composto da " + Common.MAXDOMANDE + " domande.\n\n"
                + "Il bottone Mostra risultati mostrerà soltanto la lista di domande e risposte date.\n\n"
                + "Quando l'opzione Voti e risposte corrette, verranno mostrate le risposte corrette e il voto totale.");
    }

    public int changeQuestion(int number) {
        clear();

        Domanda tmp = domande.get(number);
        appendText(tmp.getDomanda() + "\n");

        for (int i = 1; i <= tmp.getNumerorisposte(); i++) {
            try {
                String risposta = tmp.getRisposte().get(i - 1);
                if (!risposta.equals("")) {
                    appendText("\n" + Common.intToLetter(i, false) + ") " + risposta);
                }
            } catch (NullPointerException e) {
                System.err.println("Missing response for question " + domandacorrente);
            }
        }

        try {
            InputStream in = getClass().getResourceAsStream(Common.PATH + tmp.getId() + ".code");
            Locale loc = new Locale("it", "IT");
            Scanner scanner = new Scanner(in);
            scanner.useLocale(loc);
            appendText("\n\n\n");
            while (scanner.hasNext()) {
                appendText(new String(scanner.nextLine().getBytes(), "utf-8") + "\n");
            }
        } catch (UnsupportedEncodingException ex) {
            System.err.println("Encoding error!");
            Logger.getLogger(DomandaBox.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex) {
            System.out.println("File has no correlated attachment for question" + domandacorrente);
        }

        // Posiziono la TextArea in alto
        positionCaret(0);

        return tmp.getNumerorisposte();
    }

    /**
     * @param isNext Cambio alla domanda successiva o meno
     * @return Il numero di bottoni che si rendono necessari per la domanda in
     * questione.
     */
    public int changeQuestion(boolean isNext) {
        return changeQuestion(isNext ? ++domandacorrente : --domandacorrente);
    }

    /**
     * Metodo che memorizza nella Domanda corrente la risposta data dall'utente
     *
     * @param send_buttons Il ToggleGroup da cui prendere il bottone
     *                     selezionato.
     */
    public void processQuestion(ToggleGroup send_buttons) {
        if (domandacorrente > -1 && domandacorrente < Common.MAXDOMANDE) {
            Domanda current = domande.get(domandacorrente);
            String user_answer = null;
            try {
                user_answer = send_buttons.getSelectedToggle().getUserData().toString();
                System.out.println("User answer:" + user_answer + " for question" + domandacorrente);
            } catch (NullPointerException e) {
                user_answer = null;
                System.out.println("No user response found for question" + domandacorrente);
            }
            current.setRispostadata(user_answer);
        }
    }

    /**
     * Metodo che mostra la finestra dei risultati.
     *
     * @param showMark Mostra anche il voto totale e le risposte giuste?
     */
    public void getResults(boolean showMark) {
        try {
            results.close();
        } catch (NullPointerException e) {
            System.out.println("No window present.");
        }
        results = new ResultWindow(domande, showMark);
        results.show();
    }

    /**
     * @return
     */
    public int getDomandacorrente() {
        return domandacorrente;
    }

    public String getRispostacorrente() {
        return domande.get(domandacorrente).getRispostadata();
    }

}
