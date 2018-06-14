/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rispondidomande;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Matteo Franzil
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        GridPane root = new GridPane();
        BarraProgresso progress = new BarraProgresso();
        DomandaBox domandaBox = new DomandaBox();
        ControlPanel buttons = new ControlPanel(domandaBox, progress);

        // Aggiungo i constraint per un layout adattivo
        RowConstraints row1 = new RowConstraints();
        row1.setVgrow(Priority.ALWAYS);

        RowConstraints row2 = new RowConstraints();
        row2.setMinHeight(40);
        row2.setMaxHeight(80);
        row2.setVgrow(Priority.SOMETIMES);

        ColumnConstraints col = new ColumnConstraints();
        col.setHgrow(Priority.ALWAYS);
        col.setPercentWidth(100);

        root.getRowConstraints().addAll(row1, row2);
        root.getColumnConstraints().add(col);

        GridPane.setConstraints(domandaBox, 0, 0);
        GridPane.setConstraints(buttons, 0, 1);


        root.setPadding(new Insets(10, 10, 10, 10));
        root.getChildren().addAll(buttons, domandaBox);

        Scene scene = new Scene(root, domandaBox.getPrefWidth(), domandaBox.getPrefHeight() + buttons.getPrefHeight());

        primaryStage.setOnCloseRequest((WindowEvent e) -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Sei sicuro di voler uscire?");
            alert.showAndWait()
                    .filter(response -> response == ButtonType.CANCEL)
                    .ifPresent(response -> e.consume());
            Platform.exit();
        });

        primaryStage.setTitle("RispondiDomande");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
