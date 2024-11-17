package practica2;
import java.util.ArrayList;

/**
 *
 * @author alvaro2311
 */
public class Entorno {
    Mapa mapa;
    int filaAgente, columnaAgente,filaMeta, columnaMeta;    
    
    // Constructor
    public Entorno(Mapa mapaEntorno, int filaAgente, int columnaAgente, int filaMeta, int columnaMeta) {
        this.mapa = mapaEntorno;
        this.filaAgente = filaAgente;
        this.columnaAgente = columnaAgente;
        this.filaMeta = filaMeta;
        this.columnaMeta = columnaMeta;
    }
    
    // Función para imprimir el mapa en consola
    public void imprimirMapaConsola() {
        System.out.println("Numero filas: " + mapa.filas + "  Numero columnas: " + mapa.columnas);
        System.out.println("Posicion agente: (" + filaAgente + "," + columnaAgente + ")");
        for (int i = 0; i < mapa.filas; i++) {
            for (int j = 0; j < mapa.columnas; j++) {
                if (filaAgente == i && columnaAgente == j){
                    System.out.print("A\t");
                } else if (filaMeta == i && columnaMeta == j) {
                    System.out.print("X\t");
                } else {
                    System.out.print(mapa.mapa[i][j] + "\t");
                }
            }
            System.out.println();
        }
    }
    
    // Función para evolver todos los sensores del agente
    public ArrayList<Integer> actualizarEntornoAgente() {
        ArrayList<Integer> entorno = new ArrayList<>();
        
        entorno.add(sensorNoroesteAgente(filaAgente, columnaAgente));
        entorno.add(sensorNorteAgente(filaAgente, columnaAgente));
        entorno.add(sensorNoresteAgente(filaAgente, columnaAgente));
        entorno.add(sensorEsteAgente(filaAgente, columnaAgente));
        entorno.add(sensorSuresteAgente(filaAgente, columnaAgente));
        entorno.add(sensorSurAgente(filaAgente, columnaAgente));
        entorno.add(sensorSuroesteAgente(filaAgente, columnaAgente));
        entorno.add(sensorOesteAgente(filaAgente, columnaAgente));

        return entorno;
    }

    // Función para devolver el sensor norte del agente
    public int sensorNorteAgente(int x, int y) {
        if (x > 0) {
            return mapa.mapa[x-1][y];
        } else {
            return -1; 
        }
    }

    // Función para devolver el sensor noreste del agente
    public int sensorNoresteAgente(int x, int y) {
        if (y < mapa.mapa.length - 1 && x > 0) {
            return mapa.mapa[x-1][y+1];
        } else {
            return -1; 
        }
    }

    // Función para devolver el sensor este del agente
    public int sensorEsteAgente(int x, int y) {
        if (y < mapa.mapa.length - 1) {
            return mapa.mapa[x][y+1];
        } else {
            return -1; 
        }
    }

    // Función para devolver el sensor sureste del agente
    public int sensorSuresteAgente(int x, int y) {
        if (x < mapa.mapa.length - 1 && y < mapa.mapa[0].length - 1) {
            return mapa.mapa[x+1][y+1];
        } else {
            return -1;
        }
    }

    // Función para devolver el sensor sur del agente
    public int sensorSurAgente(int x, int y) {
        if (x < mapa.mapa[0].length - 1) {
            return mapa.mapa[x+1][y];
        } else {
            return -1; 
        }
    }

    // Función para devolver el sensor suroeste del agente
    public int sensorSuroesteAgente(int x, int y) {
        if (y > 0 && x < mapa.mapa[0].length - 1) {
            return mapa.mapa[x+1][y-1];
        } else {
            return -1; 
        }
    }

    // Función para devolver el sensor oeste del agente
    public int sensorOesteAgente(int x, int y) {
        if (y > 0) {
            return mapa.mapa[x][y-1];
        } else {
            return -1; 
        }
    }

    // Función para devolver el sensor noroeste del agente
    public int sensorNoroesteAgente(int x, int y) {
        if (x > 0 && y > 0) {
            return mapa.mapa[x-1][y-1];
        } else {
            return -1;
        }
    }
}
