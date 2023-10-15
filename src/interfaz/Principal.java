package interfaz;

import java.io.FileNotFoundException;

/**
 *
 * @author Pedro Chialanza (302782) - Leandro Meneses (305998)
 */
public class Principal {

    public static Interfaz in = new Interfaz();

    public static void main(String[] args) throws FileNotFoundException {
        //cada metodo ingresado, a partir de ahora
        //es una etapa del juego
        while (in.wishToPlay()) {
            while (in.pickYourName()) {
                while (in.mainMenu()) {
                    switch (in.getOption().toLowerCase()) {
                        case "a": {
                            in.setOrNotDefaultPlayer(".\\test\\datos.txt");
                            refactorWhile();
                            break;
                        }
                        case "b": {
                            in.setOrNotDefaultPlayer(".\\test\\default.txt");
                            refactorWhile();
                            break;
                        }
                        case "c": {
                            in.chooseSettings();
                            refactorWhile();
                            break;
                        }
                        case "-1": {
                            break;
                        }
                        default: {
                            System.out.println(in.getOption().toLowerCase());
                        }
                    }
                }
            }
        }
    }
    //tablero ya creado
    //adentro d este metodo va a ir el in.makeMove()

    public static void refactorWhile() {
        if (in.getPlayer() != null) {
            while (!in.getPlayer().getComplete() && !in.getForcedFinish()) {
                in.makeMove();
            }

            System.out.println("");
            System.out.println((in.getPlayer().getComplete() ? "Ha ganado!!! \nSu partida ha durado: " : "Se ha rendido \nSu partida ha durado: ") + in.getPlayer().getTime());
            System.out.println("");
        } else {
            System.out.println("Archivo no encontrado");
            System.out.println("");
        }
    }

}
