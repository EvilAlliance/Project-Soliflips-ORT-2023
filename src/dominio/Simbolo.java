/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

/**
 *
 * @author Pedro Chialanza (302782) - Leandro Meneses (305998)
 */
public class Simbolo {

    private char symbol;
    private int interactionX;
    private int interactionY;

    public char getSymbol() {
        return symbol;
    }

    /**
     * @param givenSymbol Es un caracter que representa una de las piezas de la matriz para poder jugar
     * @return boolean que verifica si el simbolo es valido
     */
    private boolean setSymbol(char givenSymbol) {
        boolean verifica = false;
        if (givenSymbol == '\\'|| givenSymbol == '|' || givenSymbol == '-' || givenSymbol == '/') {
            this.symbol = givenSymbol;
            verifica = true;
        }

        return verifica;
    }

    public int getInteractionX() {
        return interactionX;
    }

    /**
     * @param givenInteractionX - Indica para donde se moveria, para la derecha o hacia la izquierda
     * @return boolean que verifica si el simbolo es valido
     */
    private boolean setInteractionX(int givenInteractionX) {
        boolean verifica = false;

        if (givenInteractionX < 2 && givenInteractionX > -2) {
            this.interactionX = givenInteractionX;
            verifica = true;
        }

        return verifica;
    }

    public int getInteractionY() {
        return interactionY;
    }

    /**
     * @param givenInteractionY - Indica para donde se moveria, para arriba o hacia abajo
     */
    private boolean setInteractionY(int givenInteractionY) {
        boolean verifica = false;

        if (givenInteractionY < 2 && givenInteractionY > -2) {
            this.interactionY = givenInteractionY;
            verifica = true;
        }

        return verifica;
    }

    public Simbolo(char givenSymbol) {
        if (this.setSymbol(givenSymbol)) {
            switch (givenSymbol) {
                case '\\': {
                    this.setInteractionX(1);
                    this.setInteractionY(1);
                    break;
                }
                case '/': {
                    this.setInteractionX(1);
                    this.setInteractionY(-1);
                    break;
                }
                case '|': {
                    this.setInteractionX(1);
                    this.setInteractionY(0);
                    break;
                }
                case '-': {
                    this.setInteractionX(0);
                    this.setInteractionY(1);
                    break;
                }
            }
        }
    }

    @Override
    public String toString() {
        return "" + this.getSymbol();
    }
}
