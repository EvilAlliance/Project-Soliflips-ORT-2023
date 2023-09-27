/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Soliflips;

/**
 *
 * @author chial
 */
public class Historia {
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public Historia(int givenX, int givenY){
        this.setX(givenX);
        this.setY(givenY);
    }
    
    @Override
    public String toString(){
        return "(" + this.getX() + ", " + this.getY() + ")";
    }
}
