/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rispondidomande;


import javafx.scene.control.ProgressBar;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author Matteo Franzil
 */
public class BarraProgresso extends StackPane {
    Text text;
    ProgressBar bar;
    public BarraProgresso() {
        bar = new ProgressBar(0.0f);
        text = new Text("1/30");
        text.setFont(new Font(11));

        getChildren().addAll(bar, text);
    }    
    
    public void next(DomandaBox domandaBox) {
        if (domandaBox.getDomandacorrente() < DomandaBox.numerodomande - 1) {
            bar.setProgress(bar.getProgress() + (1.0 / DomandaBox.numerodomande));
            domandaBox.changeQuestion(true);
            updateText(domandaBox);
        }
    }
    
    public void previous(DomandaBox domandaBox) {
        if (domandaBox.getDomandacorrente() > 0) {
            bar.setProgress(bar.getProgress() - (1.0 / DomandaBox.numerodomande));
            domandaBox.changeQuestion(false);
            updateText(domandaBox);
        }
    }
    
    private void updateText(DomandaBox domandaBox) {
        text.setText((int)Math.floor(bar.getProgress()*DomandaBox.numerodomande) + "/" + DomandaBox.numerodomande);
    }
    
}
