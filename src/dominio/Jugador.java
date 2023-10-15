    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import java.util.ArrayList;

/**
 *
 * @author Pedro Chialanza (302782) - Leandro Meneses (305998)
 */
public class Jugador extends Matriz {

    private ArrayList<Historia> historia;

    public ArrayList<Historia> getHistoria() {
        return this.historia;
    }

    public void setHistoria() {
        this.historia = new ArrayList<Historia>();
    }

    public Historia getHistoriaMovement(int givenIndex) {
        return historia.get(givenIndex);
    }

    public boolean setHistoriaMovement(int row, int column) {
        boolean verifica = false;

        if (this.validCell(row, column)) {
            this.historia.add(new Historia(row, column));
        }

        return verifica;
    }

    public boolean playerMovement(int row, int column) {
        boolean verifica = false;
        if (this.validCell(row, column)) {
            this.cellAction(row, column);
            this.setHistoriaMovement(row, column);
            this.completeMatriz();
            verifica = true;
        }
        return verifica;
    }

    public void undoMovement() {
        int indexLastMove = this.getHistoria().size() - 1;
        if (indexLastMove > -1) {
            Historia lastMove = this.getHistoriaMovement(indexLastMove);
            this.cellAction(lastMove.getX(), lastMove.getY());
            historia.remove(indexLastMove);
        }
    }

    public String stringPlayerMovement(int row, int column) {
        String[] before = this.toString().split("\\n");
        this.playerMovement(row, column);
        String[] after = this.toString().split("\\n");
        for (int i = 0; i < before.length; i++) {
            before[i] += "  ==>  " + after[i];
        }

        return String.join("\n", before);
    }
    
        public void printPlayerMovement(int row, int column) {
            System.out.println(this.stringPlayerMovement(row, column));
        }


    public Jugador(int row, int column, int nivel) {
        super(row, column, nivel);
        this.setHistoria();
    }

    public Jugador(int row, int column) {
        super(row, column);
        this.setHistoria();
    }

    public Jugador() {
        super();
        this.setHistoria();
    }
}
