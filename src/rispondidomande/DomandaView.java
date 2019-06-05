package rispondidomande;

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

public class DomandaView extends Stage {

    private BarraProgresso progress;
    private DomandaBox domandaBox;
    private ControlPanel buttons;
    private GridPane root;

    public DomandaView() {
        root = new GridPane();

        progress = new BarraProgresso();
        domandaBox = new DomandaBox();
        buttons = new ControlPanel(domandaBox, progress);

        // Aggiungo i constraint per un layout adattivo
        initConstraints();

        root.add(domandaBox, 0, 0);
        root.add(buttons, 0, 1);

        root.setPadding(new Insets(10, 10, 10, 10));

        Scene scene = new Scene(root, domandaBox.getPrefWidth(), domandaBox.getPrefHeight() + buttons.getPrefHeight());

        setTitle("RispondiDomande");
        setScene(scene);
        show();
    }

    private void initConstraints() {
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
    }

    public void renewQuestions() {
        if (Domanda.getDomandeRimaste() < Common.MAXDOMANDE && !Domanda.startup) {
            Common.MAXDOMANDE = Domanda.getDomandeRimaste();
        }


        if (Common.MAXDOMANDE == 0 && !Domanda.startup) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Hai terminato tutti i possibili quesiti.",
                    ButtonType.OK);
            alert.showAndWait();
            Platform.exit();
        }


        root.getChildren().clear();

        domandaBox = new DomandaBox();
        progress = new BarraProgresso();
        buttons = new ControlPanel(domandaBox, progress);

        domandaBox.setText("Bentornato! Hai ancora "
                + (Domanda.getDomandeRimaste() + Common.MAXDOMANDE) + " domande da affrontare.");

        root.add(domandaBox, 0, 0);
        root.add(buttons, 0, 1);
    }
}
