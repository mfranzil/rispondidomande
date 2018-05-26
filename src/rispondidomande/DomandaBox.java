/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rispondidomande;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Matteo Franzil
 */
public class DomandaBox extends TextArea {

    public static final int numerodomande = 12;

    private int domandacorrente;
    private LinkedList<Domanda> domande;

    public DomandaBox(BarraProgresso progress) {
        domande = new LinkedList<>();
        domandacorrente = -1;

        setWrapText(true);
        setEditable(false);
        setPrefWidth(500);
        setPrefHeight(250);

        setMaxWidth(750);
        setMaxHeight(750);

        Domanda.setAvailableQuestions();

        for (int i = 0; i < numerodomande; i++) {
            try {
                Domanda tmp = new Domanda();
                System.out.println(tmp);
                domande.add(tmp);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(DomandaBox.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        progress.next(this);
    }

    public void changeQuestion(boolean isNext) {
        clear();
        Domanda tmp = domande.get(isNext ? ++domandacorrente : --domandacorrente);
        appendText(tmp.getDomanda() + "\n");

        for (int i = 1; i <= Domanda.rispostemassime; i++) {
            try {
                String risposta = tmp.getRisposte().get(i - 1);
                if (!risposta.equals("")) {
                    appendText("\n" + RispondiDomande.intToLetter(i, false) + ") " + risposta);
                }
            } catch (NullPointerException e) {
                System.err.println("Missing response in this question");
            }
        }

        try {
            InputStream in = getClass().getResourceAsStream("domande/" + tmp.getId() + ".code");
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
            System.err.println("File has no correlated attachment");
        }
        
        // Posiziono la TextArea in alto
        positionCaret(0);
    }

    public void processQuestion(ToggleGroup send_buttons) {
        Domanda current = domande.get(domandacorrente);
        String user_answer = null;
        try {
            user_answer = send_buttons.getSelectedToggle().getUserData().toString();
            System.out.println("User answer:" + user_answer);
        } catch (NullPointerException e) {
            user_answer = null;
            System.err.println("No user response found");
        }
        current.setRispostadata(user_answer);
    }

    public void getResults(boolean showMark) {
        ResultWindow results = new ResultWindow(domande, showMark);
        results.show();
    }

    public String getPreviousAnswer() {
        String res = domande.get(domandacorrente).getRispostadata();
        if (res != null) {
            return res;
        } else {
            return "Z";
        }
    }

    public int getDomandacorrente() {
        return domandacorrente;
    }

}
