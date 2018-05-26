/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rispondidomande;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
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
        setWidth(500);

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
        appendText(tmp.getDomanda() + "\n\na) " + tmp.getRisposta_a()
                + "\nb) " + tmp.getRisposta_b()
                + "\nc) " + tmp.getRisposta_c());
        try {
            if (!tmp.getRisposta_d().equals("") || tmp.getRisposta_d() == null) {
                appendText("\nd) " + tmp.getRisposta_d());
            }
            if (!tmp.getRisposta_e().equals("") || tmp.getRisposta_e() == null) {
                appendText("\ne) " + tmp.getRisposta_e());
            }
            if (!tmp.getRisposta_f().equals("") || tmp.getRisposta_f() == null) {
                appendText("\nf) " + tmp.getRisposta_f());
            }
        } catch (NullPointerException e) {
            System.out.println("Missing response in this question");
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
            Logger.getLogger(DomandaBox.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex) {
            System.out.println("File has no correlated attachment");
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
            System.out.println("No user response found");
        }
        current.setRispostadata(user_answer);
    }

    public void getResults(boolean showMark) {
        Stage printWindow = new Stage();
        StackPane printPane = new StackPane();
        Scene printScene = new Scene(printPane, 300, 300);
        TextArea txt = new TextArea();
        txt.setWrapText(true);

        int i = 0, correctcounter = 0;
        for (Domanda a : domande) {
            if (a.checkCorrect()) {
                correctcounter++;
            }
            txt.appendText("Domanda " + (i + 1) + "/" + numerodomande + " (ID " + a.getId() + "): " + a.getDomanda() + "\n"
                    + "Risposta data: " + (a.getRispostadata() == null ? "Nessuna" : a.getRispostadata()) + "\n");
            if (showMark) {
                txt.appendText("Risposta corretta: " + a.getRispostacorretta());
            }
            txt.appendText("\n\n");
            i++;
        }
        if (showMark) {
            txt.appendText("\n\nPunteggio totale: " + correctcounter + "/" + numerodomande + "\nVoto: " + correctcounter * 30.0 / numerodomande);
        }

        printPane.getChildren().add(txt);
        printWindow.setScene(printScene);
        printWindow.setTitle("Dati:");
        printWindow.show();
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
