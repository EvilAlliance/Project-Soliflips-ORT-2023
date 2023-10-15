    package dominio;

/**
 *
 * @author Pedro Chialanza (302782) - Leandro Meneses (305998)
 */
public class Matriz {

    private Celda[][] matriz;
    private int nivel;
    private Historia[] solucion;
    private Tiempo time;
    private boolean complete = false;
    private Simbolo[] simbolosDisponibles = {new Simbolo('/'), new Simbolo('|'), new Simbolo('\\'), new Simbolo('-')};

    public Celda[][] getMatriz() {
        return this.matriz;
    }

    /**
     * @param row Son las filas que va a tener la matriz
     * @param column Analogamente para las columnas
     * @return Un boolean que indica si las filas y las columnas deseadas entran en el rango pedido por el obligatorio
     */
    public boolean setMatriz(int row, int column) {
        boolean verifica = false;
        if (3 <= row && row <= 9 && 3 <= column && column <= 9) {
            this.matriz = new Celda[row][column];
            verifica = true;
        }
        return verifica;
    }

    public int getNivel() {
        return this.nivel;
    }

    /**
     * @param givenNivel Es un entero que representa la cantidad de pasos en los que se puede ganar, no garantiza que sea la cantidad minima o maxima de pasos necesarios para poder resolverlo
     * @return Un boolean que indica si el nivel entra en el rango pedido por el obligatorio
     */
    public boolean setNivel(int givenNivel) {
        boolean verifica = false;
        if (givenNivel > 0 && givenNivel < 9) {
            this.setSolucion(givenNivel);
            this.nivel = givenNivel;
            verifica = true;
        }
        return verifica;
    }

    public Celda getCelda(int row, int column) {
        return this.matriz[row][column];
    }

    /**
     * @param row Entero que indica la cantidad de filas
     * @param column Entero que indica la cantidad de columnas
     * @param symbol Caracter que representa al simbolo en la celda
     * @param colour Caracter que representa el color del simbolo
     * @return Un boolean que indica si la matriz no es nula y la celda es valida
     */
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

    /**
     * @param givenLength Entero que representa la cantidad de pasos en los que se tiene que resolver la matriz dada
     */
    public void setSolucion(int givenLength) {
        this.solucion = new Historia[givenLength];
    }

    /**
     * @param givenIndex Entero que representa el indice en el que se encuentra el par ordenado deseado
     * @return El par ordenado deseado
     */
    public Historia getSolucionHistoria(int givenIndex) {
        return solucion[givenIndex];
    }

    /**
     * @param row Entero que representa las filas que va a tener el historial
     * @param column Entero que representa las columnas que va a tener el historial
     * @param index Entero que representa el indice para el historial
     * @return Un boolean que indica si lo anterior es valido segun lo pedido en el obligatorio
     */
    public boolean setSolucionHistoria(int row, int column, int index) {
        boolean verifica = false;

        if (this.validCell(row, column) && index >= 0 && index < solucion.length) {
            this.solucion[index] = new Historia(row, column);
            verifica = true;
        }

        return verifica;
    }

    public void setTime() {
        this.time = new Tiempo();
    }

    public Tiempo getTime() {
        return time;
    }

    public Simbolo[] getSymbol() {
        return this.simbolosDisponibles;
    }

    public void setComplete(boolean givenBoolean) {
        this.complete = givenBoolean;
    }

    public boolean getComplete() {
        return this.complete;
    }

    /**
     * @param row Entero que representa la fila para poder intersecarla con una columna
     * @param column Entero que representa la columna para poder intersecarla con la fila
     * @return Un boolean que indica si la celda es valida segun si esta dentro de la matriz
     */
    public boolean validCell(int row, int column) {
        return row >= 0 && row < this.getMatriz().length && column >= 0 && column < this.getMatriz()[0].length;
    }

    /**
     * @param givenSymbol Caracter que representa el simbolo para poder hacer el cambio necesario
     */
    public Simbolo getInteraction(char givenSymbol) {
        Simbolo[] simboloDisponible = this.getSymbol();
        int index = -1;
        for (int i = 0; i < simboloDisponible.length && index == -1; i++) {
            if (givenSymbol == simboloDisponible[i].getSymbol()) {
                index = i;
            }
        }
        return simboloDisponible[index];
    }

    /**
     * @param row Entero que representa la fila seleccionada
     * @param column Entero que representa la columna seleccionada
     */
    public void cellAction(int row, int column) {
        if (this.validCell(row, column)) {
            char symbol = this.getCelda(row, column).getSymbol();
            Simbolo simboloInteraction = this.getInteraction(symbol);
            int interactionX = simboloInteraction.getInteractionX();
            int interactionY = simboloInteraction.getInteractionY();

            int x = row;
            int y = column;

            int interaction = -1;
            int iterator = 0;
            while (iterator < 2) {
                interaction *= -1;
                while (this.validCell(x, y)) {
                    this.getCelda(x, y).swap();
                    x = x + (interaction * interactionX);
                    y = y + (interaction * interactionY);
                }
                x = row - interactionX;
                y = column - interactionY;
                iterator++;
            }
        }
    }

    public boolean validMovement(int row, int column, int index) {
        boolean valid = false;
        Historia movimiento = this.getSolucionHistoria(index - 1);
        int anteriorRow = movimiento.getX();
        int anteriorColumn = movimiento.getY();
        if (this.validCell(row, column) && (row != anteriorRow || column != anteriorColumn)) {
            valid = true;
            char symbol = this.getCelda(row, column).getSymbol();
            if (symbol == this.getCelda(anteriorRow, anteriorColumn).getSymbol()) {
                Simbolo simboloInteraction = this.getInteraction(symbol);
                int interactionX = simboloInteraction.getInteractionX();
                int interactionY = simboloInteraction.getInteractionY();
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
                        valid = column != anteriorColumn;
                    }
                }
            }
        } else if (row == anteriorRow && column == anteriorColumn) {
            valid = false;
        }

        return valid;
    }

    public boolean completeMatriz() {
        boolean hasColor = false;
        Celda[][] mat = this.getMatriz();
        char color = this.getCelda(0, 0).getColour();
        for (int i = 0; i < mat.length && !hasColor; i++) {
            for (int j = 0; j < mat[0].length && !hasColor; j++) {
                if (mat[i][j].getColour() != color) {
                    hasColor = true;
                }
            }
        }

        if (!hasColor) {
            this.getTime().stop();
            this.setComplete(true);
        }

        return !hasColor;
    }

    public Matriz() {
        this(5, 6, 3);
    }

    public Matriz(int row, int column, int nivel) {
        this.setTime();
        this.setMatriz(row, column);
        Simbolo[] simboloDisponible = this.getSymbol();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                int random = (int) Math.floor(Math.random() * 4);
                if (!this.setCelda(i, j, simboloDisponible[random].getSymbol(), 'R')) {
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
                    this.setSolucionHistoria(randomX, randomY, i);
                } else {
                    i--;
                }
            }
        }
    }

    public Matriz(int row, int column) {
        this.setTime();
        this.setMatriz(row, column);
    }

    public void print() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        String s = "   ";
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
