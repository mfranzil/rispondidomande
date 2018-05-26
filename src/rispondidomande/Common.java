/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rispondidomande;

/**
 *
 * @author Matteo Franzil
 */
public class Common {

    /**
     * Il numero di risposte massime supportate dal sistema.
     */
    public static final int MAXRISPOSTE = 6;

    /**
     * Le domande totali disponibili su disco su /src/domande.
     */
    public static final int DOMANDETOTALI = 36;

    /**
     * Le domande da fare a ogni istanza del programma.
     */
    public static final int MAXDOMANDE = 12;

    /**
     * La PATH su disco per trovare le domande.
     */
    public static final String PATH = "domande/";
    /**
     * Metodo che trasforma un intero nella lettera minuscola corrispondente
     * @param input L'intero da trasformare
     * @param uppercase Boolean che rappresenta la scelta tra uppercase o meno
     * @return La lettera richiesta, in forma di String.
     */
    public static String intToLetter(int input, boolean uppercase) {
        String res;
        switch (input) {
            case 1:
                res = "a";
                break;
            case 2:
                res = "b";
                break;
            case 3:
                res = "c";
                break;
            case 4:
                res = "d";
                break;
            case 5:
                res = "e";
                break;
            case 6:
                res = "f";
                break;
            default:
                res = String.valueOf(input);
                break;
        }
        return uppercase ? res.toUpperCase() : res;
    }
    
}
