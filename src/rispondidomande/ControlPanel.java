/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rispondidomande;

import java.util.LinkedList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;

/**
 *
 * @author Matteo Franzil
 */
public class ControlPanel extends FlowPane {

    public ControlPanel(DomandaBox domandaBox, BarraProgresso progress) {

        setPadding(new Insets(10, 10, 10, 10));
        setHgap(10);
        setVgap(10);

        setPrefHeight(100);

        Button previous = new Button("Precedente");
        Button next = new Button("Successivo");
        Button getResults = new Button("Mostra risultati");

        CheckBox marks = new CheckBox("Voti e risposte corrette");
        ToggleGroup send_buttons = new ToggleGroup();
        LinkedList<ToggleButton> choice = new LinkedList<>();

        for (int i = 0; i < Domanda.rispostemassime; i++) {
            choice.add(new ToggleButton(RispondiDomande.intToLetter(i + 1, true)));
        }
        for (int i = 0; i < Domanda.rispostemassime; i++) {
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

        getResults.setOnAction((ActionEvent e) -> {
            domandaBox.getResults(marks.isSelected());
        });
        
    }

    private void setPreviousToggle(DomandaBox domandaBox, ToggleGroup send_buttons, LinkedList<ToggleButton> choice) {
        if (send_buttons.getSelectedToggle() != null) {
            send_buttons.getSelectedToggle().setSelected(false);
        }
    }

}
