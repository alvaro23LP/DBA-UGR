package practica2;

/**
 *
 * @author alvaro2311
 */

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Mapa {
    
    int filas;
    int columnas;
    int[][] mapa;
    
    public Mapa(){
        filas = 0;
        columnas = 0;
    }
    
    public void leermapa(String ruta){
        
        try {
            File archivo = new File(ruta);
            FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);

            //Leemos las filas y columnas
            filas = Integer.parseInt(br.readLine()); // Lee la primera línea como número de filas
            columnas = Integer.parseInt(br.readLine()); // Lee la segunda línea como número de columnas
            
            //Inicializamos el mapa
            mapa = new int[filas][columnas];
            
            for (int i = 0; i < filas; i++) {
                String[] numerosEnLinea = br.readLine().split("\t"); // Lee la línea de números separados por tabulaciones
                for (int j = 0; j < columnas; j++) {
                    mapa[i][j] = Integer.parseInt(numerosEnLinea[j]); // Almacena los números en la matriz
                }
            }

            br.close(); // Cierra el BufferedReader

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
