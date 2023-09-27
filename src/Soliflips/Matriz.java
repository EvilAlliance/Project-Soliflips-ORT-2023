package Soliflips;

public class Matriz {

    private Celda[][] matriz;
    private int nivel;
    private Historia[] solucion;

    public Celda[][] getMatriz() {
        return this.matriz;
    }

    public boolean setMatriz(int row, int column) {
        this.matriz = new Celda[row][column];
        return true;
    }

    public int getNivel() {
        return this.nivel;
    }

    public boolean setNivel(int givenNivel) {
        boolean verifica = false;
        if (givenNivel > 0 && givenNivel <= 9) {
            this.setSolucion(givenNivel);
            this.nivel = givenNivel;
            verifica = true;
        }
        return verifica;
    }

    public Celda getCelda(int row, int column) {
        return this.matriz[row][column];
    }

    public boolean setCelda(int row, int column, char symbol, char colour) {
        boolean verifica = false;
        if (this.getMatriz() != null && this.validCell(row, column)) {
            this.matriz[row][column] = new Celda(colour, symbol);
            verifica = true;
        }
        return verifica;
    }

    public Historia[] getSolucion() {
        return solucion;
    }

    public void setSolucion(int givenLenght) {
        this.solucion = new Historia[givenLenght];
    }

    public Historia getHistoria(int givenIndex) {
        return solucion[givenIndex];
    }

    public boolean setHistoria(int row, int column, int index) {
        boolean verifica = false;

        if (this.validCell(row, column) && index >= 0 && index < solucion.length) {
            this.solucion[index] = new Historia(row, column);
            verifica = true;
        }

        return verifica;
    }

    public boolean validCell(int row, int column) {
        return row >= 0 && row < this.getMatriz().length && column >= 0 && column < this.getMatriz()[0].length;
    }

    public void cellAction(int row, int column) {
        if (this.validCell(row, column)) {
            int interactionX = this.getCelda(row, column).getSymbol().getInteractionX();
            int interactionY = this.getCelda(row, column).getSymbol().getInteractionY();

            int x = row;
            int y = column;

            int interaction = -1;
            int iteraction = 0;
            while (iteraction < 2) {
                interaction *= -1;
                while (this.validCell(x, y)) {
                    this.getCelda(x, y).swap();
                    x = x + (interaction * interactionX);
                    y = y + (interaction * interactionY);
                }
                x = row - interactionX;
                y = column - interactionY;
                iteraction++;
            }
        }
    }

    public boolean validMovement(int row, int column, int index) {
        boolean valid = false;
        Historia movimiento = this.getHistoria(index - 1);
        int anteriorRow = movimiento.getX();
        int anteriorColumn = movimiento.getY();
        if (this.validCell(row, column) && (row != anteriorRow || column != anteriorColumn)) {
            valid = true;
            Symbol symbol = this.getCelda(row, column).getSymbol();
            if (symbol.getSymbol() == this.getCelda(anteriorRow, anteriorColumn).getSymbol().getSymbol()) {
                int interactionX = symbol.getInteractionX();
                int interactionY = symbol.getInteractionY();
                if (interactionX != 0 && interactionY != 0) {
                    int x = anteriorRow;
                    int y = anteriorColumn;

                    int interaction = -1;
                    int iteraction = 0;
                    while (iteraction < 2) {
                        interaction *= -1;
                        while (this.validCell(x, y) && valid) {
                            valid = x != row || y != column;
                            x = x + (interaction * interactionX);
                            y = y + (interaction * interactionY);
                        }
                        x = anteriorRow - interactionX;
                        y = anteriorColumn - interactionY;
                        iteraction++;
                    }
                } else {
                    if (interactionX == 0) {
                        valid = row != anteriorRow;
                    } else {
                        System.out.println(column + " == " + anteriorColumn);
                        valid = column != anteriorColumn;
                    }
                }
            }
        } else if (row == anteriorRow && column == anteriorColumn) {
            valid = false;
        }

        return valid;
    }

    public Matriz() {
        this(5, 6, 3);
    }

    public Matriz(int row, int column, int nivel) {
        char[] symbols = {'\\', '|', '-', '/'};
        this.setMatriz(row, column);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                int random = (int) Math.floor(Math.random() * 4);
                if (!this.setCelda(i, j, symbols[random], 'R')) {
                    j--;
                }
            }
        }

        if (this.setNivel(nivel)) {
            for (int i = 0; i < nivel; i++) {
                int randomX = (int) Math.floor(Math.random() * row);
                int randomY = (int) Math.floor(Math.random() * column);
                if (i == 0 || this.validMovement(randomX, randomY, i)) {
                    this.cellAction(randomX, randomY);
                    this.setHistoria(randomX, randomY, i);
                } else {
                    i--;
                }
            }
        }
    }

    /**
     * Hola
     * @param 
     */
    public void print() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        String s = '\n' + "   ";
        for (int i = 0; i < this.getMatriz()[0].length; i++) {
            s += " " + (i + 1) + "  ";
        }
        s += '\n' + "  ";

        for (int i = 0; i < this.getMatriz().length; i++) {
            for (int j = 0; j < this.getMatriz()[0].length; j++) {
                s += "+---";
            }
            s += "+";
            s += '\n';
            s += "" + (i + 1) + " |";
            for (int j = 0; j < this.getMatriz()[0].length; j++) {
                s += " " + this.getCelda(i, j).toString() + " |";
            }
            s += '\n' + "  ";
        }
        for (int j = 0; j < this.getMatriz()[0].length; j++) {
            s += "+---";
        }
        s += "+";
        s += '\n';
        return s;
    }
}
