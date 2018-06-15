/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rispondidomande;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;

import java.util.LinkedList;

/**
 * @author Matteo Franzil
 */
public class ControlPanel extends FlowPane {

    private LinkedList<ToggleButton> buttons;

    /**
     * Costruttore standard.
     *
     * @param domandaBox Una domandaBox su cui disegnare le domande.
     * @param progress   Una BarraProgresso per tracciare il numero di domande.
     */
    public ControlPanel(DomandaBox domandaBox, BarraProgresso progress) {

        setPadding(new Insets(10, 10, 10, 10));
        setHgap(10);
        setVgap(10);

        setPrefHeight(100);

        Button previous = new Button("⬅");
        Button next = new Button("➡ Inizia");
        Button getResults = new Button("Mostra risultati");
        CheckBox marks = new CheckBox("Voti e risposte corrette");

        ToggleGroup send_buttons = new ToggleGroup();
        buttons = new LinkedList<>();

        for (int i = 0; i < Domanda.getMaxopzioni(); i++) {
            ToggleButton button = new ToggleButton(Common.intToLetter(i + 1, true));
            buttons.add(button);
            button.setUserData(Common.intToLetter(i + 1, true));
            button.setToggleGroup(send_buttons);

            button.setOnAction(e -> domandaBox.processQuestion(send_buttons));

            getChildren().add(button);
        }

        // All'inizio tutti i bottoni sono disabilitati
        activeButtonSwitcher(0);
        previous.setDisable(true);

        getChildren().addAll(previous, next, getResults, marks, progress);

        // Previous e next si disabilitano e abilitano a vicenda a seconda della posizione relativa
        // alle domande totali. Entrambi i metodi processano la domanda corrente, aggiornano la Progress
        // Bar alla domanda successiva (ricevendo un intero che rappresenta il numero di bottoni da attivare)
        // e infine disabilitano tutti i bottoni di scelta.
        previous.setOnAction((ActionEvent e) -> {
            int bottoniattivi = progress.previous(domandaBox);
            setPreviousToggle(send_buttons);
            activeButtonSwitcher(bottoniattivi);
            previous.setText("⬅ " + (domandaBox.getDomandacorrente()));
            next.setText("➡ " + (domandaBox.getDomandacorrente() + 2));


            next.setDisable(false);

            if (domandaBox.getDomandacorrente() <= 0) {
                progress.previous(domandaBox);
                previous.setDisable(true);
            }

        });

        next.setOnAction((ActionEvent e) -> {
            int bottoniattivi = progress.next(domandaBox);
            setPreviousToggle(send_buttons);
            activeButtonSwitcher(bottoniattivi);
            previous.setText("⬅ " + (domandaBox.getDomandacorrente()));
            next.setText("➡ " + (domandaBox.getDomandacorrente() + 2));

            if (domandaBox.getDomandacorrente() > 0) {
                previous.setDisable(false);
            }

            if (domandaBox.getDomandacorrente() + 1 >= Common.MAXDOMANDE) {
                progress.next(domandaBox);
                next.setDisable(true);
            }
        });

        getResults.setOnAction(e -> domandaBox.getResults(marks.isSelected()));
    }

    private void setPreviousToggle(ToggleGroup send_buttons) {
        if (send_buttons.getSelectedToggle() != null) {
            send_buttons.getSelectedToggle().setSelected(false);
        }
    }

    private void activeButtonSwitcher(int buttonNumber) {
        for (int i = 0; i < buttonNumber; i++) {
            buttons.get(i).setDisable(false);
        }

        for (int i = buttonNumber; i < Domanda.getMaxopzioni(); i++) {
            buttons.get(i).setDisable(true);
        }
    }
}
