package Soliflips;

import java.util.Arrays;

public class Interfaz {

    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static void main(String[] args) {

        Matriz mat2 = new Matriz(9, 9, 9);
        mat2.print();
        System.out.println(Arrays.toString(mat2.getSolucion()));

        Historia[] h = mat2.getSolucion();

        for (int i = h.length - 1; i > -1; i--) {
            mat2.cellAction(h[i].getX(), h[i].getY());
        }

        mat2.print();

        mat2.playerMovement(0, 0);
        mat2.print();

        mat2.playerMovement(8, 8);
        mat2.print();
        
        System.out.println(Arrays.toString(mat2.getHistoria().toArray()));


        mat2.unDoMovement();
        mat2.print();

        mat2.unDoMovement();
        mat2.print();
    }
}
