package Soliflips;

import java.util.Arrays;

public class Celda {

    private char symbol;
    private char colour;

    public char getSymbol() {
        return this.symbol;
    }

    public boolean setSymbol(char givenSymbol) {
        boolean verifica = false;
        char[] characters = {'/', '\\', '|', '-'};

        for (int i = 0; i < characters.length && !verifica; i++) {
            if (characters[i] == givenSymbol) {
                this.symbol = givenSymbol;
                verifica = true;
            }
        }

        return verifica;
    }

    public char getColour() {
        return this.colour;
    }

    public boolean setColour(char givenColour) {
        boolean verifica = false;

        if (givenColour == 'A' || givenColour == 'R') {
            this.colour = givenColour;
            verifica = true;
        }

        return verifica;
    }

    public void swap() {
        if (this.getColour() == 'R') {
            this.setColour('A');
        } else {
            this.setColour('R');
        }
    }

    public Celda() {
        this('R', '|');
    }

    public Celda(char givenColor, char givenSymbol) {
        this.setColour(givenColor);
        this.setSymbol(givenSymbol);
    }

    @Override
    public String toString() {
        String print = "";

        if (this.getColour() == 'A') {
            print += "\u001B[34m";
        } else {
            print += "\u001B[31m";
        }
        print += this.getSymbol() + "\u001B[0m";
        return print;
    }
}
