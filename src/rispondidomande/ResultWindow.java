/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rispondidomande;

import java.util.LinkedList;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Matteo Franzil
 */
public class ResultWindow extends Stage {

    /**
     *
     * @param domande
     * @param showMark
     */
    public ResultWindow(LinkedList<Domanda> domande, boolean showMark) {
        StackPane printPane = new StackPane();
        Scene printScene = new Scene(printPane, 300, 300);
        TextArea txt = new TextArea();
        txt.setWrapText(true);

        int i = 0, correctcounter = 0;
        for (Domanda a : domande) {
            if (a.checkCorrect()) {
                correctcounter++;
            }
            
            txt.appendText("Domanda " + (i + 1) + "/" + Common.MAXDOMANDE + " (ID " + a.getId() + "): " 
                    + a.getDomanda() + "\n" + "Risposta data: " +
                    (a.getRispostadata() == null ? "Nessuna" : a.getRispostadata()) + "\n");
            
            if (showMark) {
                txt.appendText("Risposta corretta: " + a.getRispostacorretta());
            }
            
            txt.appendText("\n\n");
            i++;
        }
        
        if (showMark) {
            txt.appendText("Punteggio totale: " + correctcounter + "/" + Common.MAXDOMANDE
                    + "\nVoto: " + correctcounter * 30.0 / Common.MAXDOMANDE);
        }
        
        printPane.getChildren().add(txt);
        setScene(printScene);
        setTitle("Dati:");
    }
    
    
    
}
