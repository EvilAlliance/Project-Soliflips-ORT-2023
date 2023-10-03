/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Soliflips;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author chial
 */
public class FileMatriz {

    private String ruta;
    private Player matriz;

    public Player getMatriz() {
        return matriz;
    }

    public void setMatriz(Player mat) {
        this.matriz = mat;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public FileMatriz(String givenRuta) throws FileNotFoundException {
        this.setRuta(givenRuta);
        Scanner input = new Scanner(new File(this.getRuta()));

        String[] size = input.nextLine().split(" ");
        int row = Integer.parseInt(size[0]);
        int column = Integer.parseInt(size[1]);

        this.setMatriz(new Player(row, column));

        Player playerMat = this.getMatriz();
        
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
            
            playerMat.setSolucionHistoria(solRow-1, solColumn-1, level-i);
            i++;
        }
    }
}
