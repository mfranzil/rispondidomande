/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rispondidomande;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
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
        BarraProgresso progress = new BarraProgresso();
        DomandaBox domandaBox = new DomandaBox(progress);
        ControlPanel buttons = new ControlPanel(domandaBox, progress);
        
        GridPane.setConstraints(domandaBox, 0, 0);
        GridPane.setConstraints(buttons, 0, 1);
        
        root.setPadding(new Insets(10, 10, 10, 10));
        root.getChildren().addAll(buttons, domandaBox);

        Scene scene = new Scene(root, domandaBox.getWidth(), 300);

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
