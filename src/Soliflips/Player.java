/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Soliflips;

import java.util.ArrayList;

/**
 *
 * @author chial
 */
public class Player extends Matriz {

    private ArrayList<Historia> historia;

    public ArrayList<Historia> getHistoria() {
        return this.historia;
    }

    public void setHitoria() {
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

    public Player(int row, int column, int nivel) {
        super(row, column, nivel);
        this.setHitoria();
    }

    public Player(int row, int column) {
        super(row, column);
        this.setHitoria();
    }

    public Player() {
        super();
        this.setHitoria();
    }
}
