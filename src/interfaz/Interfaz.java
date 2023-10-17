package interfaz;

import dominio.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Pedro Chialanza (302782) - Leandro Meneses (305998)
 */
public class Interfaz {

    private Scanner sc = new Scanner(System.in);
    private String name;
    private String myOption;
    private int level;
    private int rows;
    private int cols;
    private boolean forcedFinish = false;
    private boolean madeMove = false;
    private Jugador myPlayer;

    public boolean getMadeMove() {
        return madeMove;
    }

    public void setMadeMove(boolean madeMove) {
        this.madeMove = madeMove;
    }

    public boolean getForcedFinish() {
        return forcedFinish;
    }

    public void setForcedFinish(boolean forcedFinish) {
        this.forcedFinish = forcedFinish;
    }

    public void setOption(String givenOption) {
        this.myOption = givenOption;
    }

    public String getOption() {
        return this.myOption;
    }

    public void setOrNotDefaultPlayer(String myPath_FileMatriz) throws FileNotFoundException {
        this.myPlayer = new ArchivoMatriz(myPath_FileMatriz).getMatriz();
    }

    private void setPlayer(int matrixRow, int matrixCol, int matrixLevel) {
        this.setMadeMove(false);
        this.myPlayer = new Jugador(matrixRow, matrixCol, matrixLevel);
    }

    public Jugador getPlayer() {
        return this.myPlayer;
    }

    private void setRows(int givenRows) {
        this.rows = givenRows;
    }

    private void setCols(int givenCols) {
        this.cols = givenCols;
    }

    public int getRows() {
        return this.rows;
    }

    public int getCols() {
        return this.cols;
    }

    public void setName(String givenName) {
        this.name = givenName;
    }

    public String getName() {
        return this.name;
    }

    public void setLevel(int givenLevel) {
        this.level = givenLevel;
    }

    public int getLevel() {
        return this.level;
    }

    public boolean isNumber(String givenString) {
        boolean indeed = true;

        for (int i = 0; i < givenString.length() && indeed; i++) {
            char auxChar = givenString.charAt(i);

            if (!Character.isDigit(auxChar)) {
                indeed = false;
            }
        }

        return indeed && (givenString.length() > 0);
    }

    public boolean pickYourName() { //etapa 1, se fija el nombre ingresado
        boolean continuar = true;
        String nombre = "";

        while (nombre.equals("") && !this.getForcedFinish()) {
            System.out.println("Ingrese su nombre, si ingresa -1, saldra del juego: ");
            nombre = sc.nextLine();

            System.out.println("");
        }

        this.setName(nombre);

        if (nombre.equals("-1")) {
            continuar = false;
        }

        return continuar && !this.getForcedFinish();
    }

    public boolean wishToPlay() { //etapa 2, se fija si el usuario quiere jugar o no
        if (this.getForcedFinish()) {
            System.out.println("");
        }
        this.setForcedFinish(false);
        boolean continuar = true;
        String wish = "";

        while (!wish.equalsIgnoreCase("no") && !wish.equalsIgnoreCase("si")) {
            System.out.println("Bienvenido a Solifplis, un divertido juego en el que la pasaras bien!");
            System.out.println("Desea jugarlo?");
            wish = sc.nextLine();

            System.out.println("");
        }

        if (wish.equalsIgnoreCase("no")) {
            continuar = false;
        }

        return continuar;
    }

    public boolean mainMenu() {
        String answer = "";

        while (!answer.equalsIgnoreCase("a") && !answer.equalsIgnoreCase("b") && !answer.equalsIgnoreCase("c") && !answer.equals("-1") && !this.getForcedFinish()) {
            System.out.println("Bienvenido " + this.getName() + ", ingrese la opcion que desee (-1 para salir)");
            System.out.println("a) Tomar datos del archivo .txt");
            System.out.println("b) Usar el tablero predefinido");
            System.out.println("c) Usar un tablero al azar");

            answer = sc.nextLine();

            System.out.println("");
        }

        this.setOption(answer);

        if (answer.equals("-1")) {
            this.setForcedFinish(true);
        }
        return !this.getForcedFinish();
    }

    public void chooseLevel() { //se fija el nivel ingresado
        String nivelDeseado = "";
        int desiredLevel = -2;

        while (desiredLevel <= 0 || desiredLevel >= 9) {
            System.out.println("Entre 1 a 8, ingrese un nivel");
            nivelDeseado = sc.nextLine();

            if (this.isNumber(nivelDeseado)) {
                desiredLevel = Integer.parseInt(nivelDeseado);
            }

            System.out.println("");
        }

        this.setLevel(desiredLevel);

    }

    public void chooseSettings() { //etapa 3, elige fila, columna nivel y confirma eleccion
        boolean sureChoice = true;

        while (sureChoice) {
            this.chooseLevel();
            this.chooseRow();
            this.chooseCol();
            sureChoice = this.verifyChoiceMade();
        }

        this.setPlayer(this.getRows(), this.getCols(), this.getLevel());
    }

    public void chooseRow() { //se fija la fila ingresada
        String tempRow = "";
        int row = -1;
        boolean esNumero = false;

        while (row <= 2 || row >= 10) {
            System.out.println("Entre 3 a 9, elija la cantidad de filas para jugar:");
            tempRow = sc.nextLine();

            esNumero = this.isNumber(tempRow);

            if (esNumero) {
                row = Integer.parseInt(tempRow);
            }

            System.out.println("");
        }

        this.setRows(row);
    }

    public void chooseCol() { //se fija la columna ingresada
        String tempCol = "";
        int col = -1;
        boolean esNumero = false;

        while (col <= 2 || col >= 10) {
            System.out.println("Entre 3 a 9, elija la cantidad de columnas para jugar:");
            tempCol = sc.nextLine();

            esNumero = this.isNumber(tempCol);

            if (esNumero) {
                col = Integer.parseInt(tempCol);
            }

            System.out.println("");
        }

        this.setCols(col);
    }

    /**
     * @return boolean que verifica si el jugador esta de acuerdo, si es true no
     * lo esta, si es false lo esta
     */
    public boolean verifyChoiceMade() { //confirma la eleccion
        boolean isNotSure = false;
        String choice = "";

        System.out.println("Fila/s: " + this.getRows() + "\nColumna/s: " + this.getCols() + "\nNivel: " + this.getLevel());

        while (!choice.equalsIgnoreCase("no") && !choice.equalsIgnoreCase("si")) {
            System.out.println("");
            System.out.println(this.getName() + ", esta seguro de su eleccion?");
            choice = sc.nextLine();

            if (choice.equalsIgnoreCase("no")) {
                isNotSure = true;
            }
            System.out.println("");
        }

        return isNotSure;
    }

    public boolean createMatrix() { //crea la matriz
        this.setPlayer(this.getRows(), this.getCols(), this.getLevel());

        return this.getPlayer() == null;
    }

    public void showHistoria() {
        ArrayList<Historia> historia = this.getPlayer().getHistoria();

        if (!historia.isEmpty()) {
            String out = "";

            for (int c = 0; c < historia.size(); c++) {
                out += historia.get(c);

                if (c != historia.size() - 1) {
                    out += ", ";
                }
            }

            System.out.println(out);
        } else {
            System.out.println("No se ha hecho ningun movimiento");
        }

        System.out.println("");

    }

    public void makeMove() {
        if (!this.getMadeMove()) {
            this.getPlayer().print();
        }
        
        this.setMadeMove(false);
    
        String moveMade = "";

        while (!(moveMade.length() == 1 || (moveMade.length() == 3 && this.isMove(moveMade)) || moveMade.equals("-1 -1"))) {
            System.out.println("Declare su movimiento, primero las filas y seguidas de un espacio, las columnas");
            moveMade = sc.nextLine();

            System.out.println("");
        }

        //ver si es H, S, X o numero
        switch (moveMade.length()) {
            case 3: {
                this.setMadeMove(true);
                String[] movesMake = moveMade.split(" ");
                int[] moves2Int = new int[2];

                for (int i = 0; i < movesMake.length; i++) {
                    moves2Int[i] = Integer.parseInt(movesMake[i]) - 1;
                }

                this.getPlayer().printPlayerMovement(moves2Int[0], moves2Int[1]);

                break;
            }
            case 1: {
                switch (moveMade) {
                    case "H": {
                        this.showHistoria();
                        break;
                    }
                    case "S": {
                        this.showSolucion();
                        break;
                    }
                    case "X": {
                        this.getPlayer().getTime().stop();
                        this.setForcedFinish(true);
                        break;
                    }
                }
                break;
            }
            case 5: {
                this.getPlayer().undoMovement();
                break;
            }
            default: {
                System.out.println("???");
                break;
            }
        }

    }

    public boolean isMove(String givenMove) {
        boolean soTrueSoReal = false;

        if (givenMove.length() == 3) {
            String[] moves = givenMove.split(" ");

            if (moves.length == 2) {
                int[] movesToInt = new int[2];

                for (int c = 0; c < moves.length; c++) {
                    if (this.isNumber(moves[c])) {
                        movesToInt[c] = Integer.parseInt(moves[c]);
                    }
                }

                if (movesToInt[0] != 0 && movesToInt[1] != 0 && this.getPlayer().validCell(movesToInt[0] - 1, movesToInt[1] - 1)) {
                    soTrueSoReal = true;
                }
            }
        }

        return soTrueSoReal;
    }

    public void showSolucion() {
        Historia[] solucion = this.getPlayer().getSolucion();

        String out = "";

        for (int c = solucion.length - 1; c >= 0; c--) {
            out += solucion[c];

            if (c != 0) {
                out += ", ";
            }
        }

        System.out.println(out);
        System.out.println("");
    }
}
