/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rispondidomande;

import javafx.scene.control.ProgressBar;

/**
 *
 * @author Matteo Franzil
 */
public class BarraProgresso extends ProgressBar {

    public BarraProgresso() {
        super(0.0f);
    }
       

    public void next(DomandaBox domandaBox) {
        if (domandaBox.getDomandacorrente() < DomandaBox.numerodomande - 1) {
            setProgress(getProgress() + (1.0 / DomandaBox.numerodomande));
            domandaBox.changeQuestion(true);
        }
    }

    public void previous(DomandaBox domandaBox) {
        if (domandaBox.getDomandacorrente() > 0) {
            setProgress(getProgress() - (1.0 / DomandaBox.numerodomande));
            domandaBox.changeQuestion(false);
        }
    }
    
}
