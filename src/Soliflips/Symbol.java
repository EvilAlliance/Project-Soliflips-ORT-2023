/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Soliflips;

/**
 *
 * @author chial
 */
public class Symbol {

    private char symbol;
    private int interactionX;
    private int interactionY;

    public char getSymbol() {
        return symbol;
    }

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

    private boolean setInteractionY(int givenIteractionY) {
        boolean verifica = false;

        if (givenIteractionY < 2 && givenIteractionY > -2) {
            this.interactionY = givenIteractionY;
            verifica = true;
        }

        return verifica;
    }

    public Symbol(char givenSymbol) {
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
