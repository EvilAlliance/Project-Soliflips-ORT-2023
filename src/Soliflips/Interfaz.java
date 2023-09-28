package Soliflips;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Interfaz {

    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static void main(String[] args) throws InterruptedException, AWTException {
        Matriz mat2 = new Matriz(9, 9, 9);
        mat2.print();
        System.out.println(Arrays.toString(mat2.getSolucion()));

        Historia[] h = mat2.getSolucion();

        for (int i = h.length - 1; i > -1; i--) {
            mat2.playerMovement(h[i].getX(), h[i].getY());
        }

        mat2.print();

        mat2.playerMovement(0, 0);
        mat2.print();

        mat2.playerMovement(8, 8);
        mat2.print();

        System.out.println(Arrays.toString(mat2.getHistoria().toArray()));

        System.out.println("Clear");
        clear();
        Thread.sleep(500);
        System.out.println("Cleared");

        mat2.undoMovement();
        mat2.print();

        mat2.undoMovement();
        mat2.print();

        Tiempo x = new Tiempo();

        x.stop();

        System.out.println(x);
    }

    public static void clear() throws AWTException, InterruptedException {
        Robot r = new Robot();
        r.keyPress(KeyEvent.VK_CONTROL);
        r.keyPress(KeyEvent.VK_L);
        r.keyRelease(KeyEvent.VK_L);
        r.keyRelease(KeyEvent.VK_CONTROL);
    }
}
