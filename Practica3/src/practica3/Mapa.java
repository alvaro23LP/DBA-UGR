package practica3;

/**
 *
 * @author alvaro2311
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Mapa {
    
    int filas;
    int columnas;
    public int[][] mapa;
    
    public Mapa(){
        filas = 0;
        columnas = 0;
    }
    
    public void readMap(String ruta){
        
        try {
            File rutaArchivo = new File(ruta);
            FileReader fr = new FileReader(rutaArchivo);
            BufferedReader br = new BufferedReader(fr);

            //Leemos las filas y columnas
            filas = Integer.parseInt(br.readLine()); 
            columnas = Integer.parseInt(br.readLine()); 
            
            //Inicializamos el mapa
            mapa = new int[filas][columnas];
            
            for (int i = 0; i < filas; i++) {
                String[] filaNumeros = br.readLine().split("\t"); 
                for (int j = 0; j < columnas; j++) {
                    mapa[i][j] = Integer.parseInt(filaNumeros[j]); // Almacena los números en la matriz
                }
            }

            br.close(); 

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
