/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

/**
 *
 * @author Pedro Chialanza (302782) - Leandro Meneses (305998)
 */
public class Historia {
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    /**
     * @param x - Coordenada x en la matriz
     */
    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    /**
     * @param y - Coordenada y en la matriz
     */
    public void setY(int y) {
        this.y = y;
    }
    
    /**
     * @param givenX - Coordenada x para crear la matriz
     * @param givenY - Coordenada y para crear la matriz
     */
    public Historia(int givenX, int givenY){
        this.setX(givenX);
        this.setY(givenY);
    }
    
    @Override
    public String toString(){
        return "(" + (this.getX()+1) + ", " + (this.getY()+1) + ")";
    }
}
