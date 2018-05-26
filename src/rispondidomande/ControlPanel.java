/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rispondidomande;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;

/**
 *
 * @author Matteo Franzil
 */
public class ControlPanel extends FlowPane {

    public ControlPanel(DomandaBox domandaBox, BarraProgresso progress) {

        setPadding(new Insets(15, 15, 15, 15));
        setHgap(10);
        setVgap(10);

        Button previous = new Button("Precedente");
        Button next = new Button("Successivo");
        Button getResults = new Button("Ottieni risultati");

        ToggleButton marks = new ToggleButton("Mostra voti");
        ToggleGroup send_buttons = new ToggleGroup();
        LinkedList<ToggleButton> choice = new LinkedList<ToggleButton>(
                Arrays.asList(new ToggleButton("A"), new ToggleButton("B"),
                        new ToggleButton("C"), new ToggleButton("D"),
                        new ToggleButton("E"), new ToggleButton("F")));

        for (int i = 0; i < choice.size(); i++) {
            choice.get(i).setUserData(String.valueOf((char) ('A' + i)));
        }

        for (ToggleButton button : choice) {
            button.setToggleGroup(send_buttons);
        }

        getChildren().addAll(choice.get(0), choice.get(1), choice.get(2),
                choice.get(3), choice.get(4), choice.get(5),
                previous, next, getResults, marks, progress);

        previous.setOnAction((ActionEvent e) -> {
            domandaBox.processQuestion(send_buttons);
            progress.previous(domandaBox);
            setPreviousToggle(domandaBox, send_buttons, choice);
        });

        next.setOnAction((ActionEvent e) -> {
            domandaBox.processQuestion(send_buttons);
            progress.next(domandaBox);
            setPreviousToggle(domandaBox, send_buttons, choice);
        });

        getResults.setOnAction((ActionEvent e)
                -> domandaBox.getResults(marks.isSelected()));
    }

    private void setPreviousToggle(DomandaBox domandaBox, ToggleGroup send_buttons, LinkedList<ToggleButton> choice) {
        if (send_buttons.getSelectedToggle() != null) {
            send_buttons.getSelectedToggle().setSelected(false);
        }
        /*
        switch (domandaBox.getPreviousAnswer()) {
        case "A":
        send_buttons.selectToggle(choice.get(0));
        break;
        case "B":
        send_buttons.selectToggle(choice.get(1));
        break;
        case "C":
        send_buttons.selectToggle(choice.get(2));
        break;
        case "D":
        send_buttons.selectToggle(choice.get(3));
        break;
        default:
        break;
        }*/
    }

}
