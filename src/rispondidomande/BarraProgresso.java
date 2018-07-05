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
 * @author Matteo Franzil
 */
public class BarraProgresso extends StackPane {
    private Text text;
    private ProgressBar bar;

    /**
     * Wrapper per una ProgressBar con il progresso espresso in frazione stampato sopra. La ProgressBar è
     * automaticamente inizializzata a 0.
     */
    public BarraProgresso() {
        bar = new ProgressBar(0.0f);
        text = new Text("");
        text.setFont(new Font(11));

        getChildren().addAll(bar, text);
    }

    /**
     * @param domandaBox
     * @return
     */
    public int next(DomandaBox domandaBox) {
        int res = 0;
        if (domandaBox.getDomandacorrente() < Common.MAXDOMANDE - 1) {
            bar.setProgress(bar.getProgress() + (1.0 / Common.MAXDOMANDE));
            res = domandaBox.changeQuestion(true);
            updateText(domandaBox);
        }
        return res;
    }

    /**
     * @param domandaBox
     * @return
     */
    public int previous(DomandaBox domandaBox) {
        int res = 0;
        if (domandaBox.getDomandacorrente() > 0) {
            bar.setProgress(bar.getProgress() - (1.0 / Common.MAXDOMANDE));
            res = domandaBox.changeQuestion(false);
            updateText(domandaBox);
        }
        return res;
    }

    private void updateText(DomandaBox domandaBox) {
        text.setText((int) Math.round(bar.getProgress() * Common.MAXDOMANDE) + "/" + Common.MAXDOMANDE);
    }

}
