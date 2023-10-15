package dominio;

/**
 *
 * @author Pedro Chialanza (302782) - Leandro Meneses (305998)
 */
public class Celda {

    
    private char symbol;
    private char colour;

    public char getSymbol() {
        return this.symbol;
    }
    
    /**
     * @param givenSymbol - Caracter que indica el simbolo el cual la celda va a contener
     * @return boolean que verifica si el simbolo es correcto
     */
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

    /**
     * @param givenColour - Indica el color por el cual el simbolo se va a pintar
     * @return boolean que verifica si es valido el color
     */
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

    /**
     * @param givenColor - Es el color el cual va a tener la celda, va a variar entre Rojo (R) y Azul (A)
     * @param givenSymbol - Es el simbolo el cual va a tener la celda, puede ser /, -, |, \
     */
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
