/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rispondidomande;

/**
 * @author Matteo Franzil
 */
public class Common {

    /**
     * Le domande totali disponibili su disco su /src/domande.
     */
    public static final int DOMANDETOTALI = 138;

    /**
     * Le domande da fare a ogni istanza del programma.
     */
    public static int MAXDOMANDE = 12;

    /**
     * La PATH su disco per trovare le domande.
     */
    public static final String PATH = "domande/";

    /**
     * Metodo che trasforma un intero nella lettera minuscola corrispondente
     *
     * @param input     L'intero da trasformare
     * @param uppercase Boolean che rappresenta la scelta tra uppercase o meno
     * @return La lettera richiesta, in forma di String.
     */
    public static String intToLetter(int input, boolean uppercase) {
        String res;
        if (input <= 26 && input >= 1) {
            res = String.valueOf((char) ('A' - 1 + input));
        } else {
            res = "-";
        }
        return uppercase ? res.toUpperCase() : res;
    }

    public static int letterToInt(String input) {
        int res;
        input = input.toLowerCase();
        switch (input) {
            case "a":
                res = 1;
                break;
            case "b":
                res = 2;
                break;
            case "c":
                res = 3;
                break;
            case "d":
                res = 4;
                break;
            case "e":
                res = 5;
                break;
            case "f":
                res = 6;
                break;
            default:
                res = 0;
                break;
        }
        return res;
    }

}
