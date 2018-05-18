/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rispondidomande;

import java.util.LinkedList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Matteo Franzil
 */
public class RispondiDomande extends Application {

    @Override
    public void start(Stage primaryStage) {
        GridPane root = new GridPane();
        ProgressBar progress = new ProgressBar(0.0f);
        DomandaBox domandaBox = new DomandaBox(progress);

        FlowPane buttons = new FlowPane();
        buttons.setPadding(new Insets(15, 15, 15, 15));
        buttons.setHgap(10);
        buttons.setVgap(10);

        Button previous = new Button("Precedente");
        Button next = new Button("Successivo");
        Button getResults = new Button("Ottieni risultati");
        ToggleButton marks = new ToggleButton("Mostra voti");
        ToggleGroup send_buttons = new ToggleGroup();

        LinkedList<ToggleButton> choice = new LinkedList<>();
        choice.add(new ToggleButton("A"));
        choice.add(new ToggleButton("B"));
        choice.add(new ToggleButton("C"));
        choice.add(new ToggleButton("D"));

        choice.get(0).setUserData("A");
        choice.get(1).setUserData("B");
        choice.get(2).setUserData("C");
        choice.get(3).setUserData("D");

        choice.get(0).setToggleGroup(send_buttons);
        choice.get(1).setToggleGroup(send_buttons);
        choice.get(2).setToggleGroup(send_buttons);
        choice.get(3).setToggleGroup(send_buttons);

        buttons.getChildren().addAll(choice.get(0), choice.get(1), choice.get(2), choice.get(3),
                previous, next, getResults, marks, progress);
        GridPane.setConstraints(domandaBox, 0, 0);
        GridPane.setConstraints(buttons, 0, 1);

        previous.setOnAction((ActionEvent e) -> {
            domandaBox.processQuestion(send_buttons);
            domandaBox.previous(progress);
            setPreviousToggle(domandaBox, send_buttons, choice);

        });

        next.setOnAction((ActionEvent e) -> {
            domandaBox.processQuestion(send_buttons);
            domandaBox.next(progress);
            setPreviousToggle(domandaBox, send_buttons, choice);
        });

        getResults.setOnAction((ActionEvent e) ->
                domandaBox.getResults(marks.isSelected()));

        root.setPadding(new Insets(10, 10, 10, 10));
        root.getChildren().addAll(buttons, domandaBox);

        Scene scene = new Scene(root, domandaBox.getWidth(), 300);

        primaryStage.setTitle("RispondiDomande");
        primaryStage.setScene(scene);
        primaryStage.show();
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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
