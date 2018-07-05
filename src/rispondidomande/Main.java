/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rispondidomande;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.Optional;

/**
 * @author Matteo Franzil
 */
public class Main extends Application {

    private DomandaView domanda;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        domanda = new DomandaView();

        ButtonType newTest = new ButtonType("Nuovo test");
        ButtonType exit = new ButtonType("Esci");
        ButtonType cancel = new ButtonType("Annulla");

        domanda.setOnCloseRequest((WindowEvent e) -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Vuoi fare un nuovo test o uscire?",
                    newTest, exit, cancel);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == newTest) {
                domanda.renewQuestions();
            } else if (result.isPresent() && result.get() == exit) {
                Platform.exit();
            }
            e.consume();
        });
    }


}
