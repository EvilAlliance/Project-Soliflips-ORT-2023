package Soliflips;

public class Celda {

    private Symbol symbol;
    private char colour;

    public Symbol getSymbol() {
        return this.symbol;
    }

    public boolean setSymbol(char givenSymbol) {
        this.symbol = new Symbol(givenSymbol);
        return Character.isLetter(this.symbol.getSymbol());
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
        print += this.getSymbol().getSymbol() + "\u001B[0m";
        return print;
    }
}
