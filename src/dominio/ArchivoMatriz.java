/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Pedro Chialanza (302782) - Leandro Meneses (305998)
 */
public class ArchivoMatriz {

    private String ruta;
    private Jugador matriz;

    public Jugador getMatriz() {
        return matriz;
    }

    /**
     * @param mat - Matriz que representa al jugador
     */
    public void setMatriz(Jugador mat) {
        this.matriz = mat;
    }

    public String getRuta() {
        return ruta;
    }

    /**
     * @param ruta - String que indica la ruta donde se va a guardar el archivo
     * .txt
     */
    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    /**
     * @param givenRuta - Es la ruta en la cual el archivo se va a guardar
     * @throws FileNotFoundException - Tira un error si no encuentra el archivo
     */
    public ArchivoMatriz(String givenRuta) throws FileNotFoundException {
        this.setRuta(givenRuta);
        File Filemat = new File(this.getRuta());
        if (Filemat.exists()) {
            Scanner input = new Scanner(Filemat);

            String[] size = input.nextLine().split(" ");
            int row = Integer.parseInt(size[0]);
            int column = Integer.parseInt(size[1]);

            this.setMatriz(new Jugador(row, column));

            Jugador playerMat = this.getMatriz();

            for (int i = 0; i < row; i++) {
                String line = input.nextLine();
                for (int j = 0; j < column; j++) {
                    int updateJ = j * 3;
                    playerMat.setCelda(i, j, line.charAt(updateJ), line.charAt(updateJ + 1));
                }
            }

            int level = Integer.parseInt(input.nextLine());
            playerMat.setNivel(level);

            int i = 1;
            while (input.hasNextLine()) {
                String[] line = input.nextLine().split(" ");
                int solRow = Integer.parseInt(line[0]);
                int solColumn = Integer.parseInt(line[1]);

                playerMat.setSolucionHistoria(solRow - 1, solColumn - 1, level - i);
                i++;
            }
        }

    }
}
