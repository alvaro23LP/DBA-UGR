package practica2;
import java.util.ArrayList;

/**
 *
 * @author alvaro2311
 */
public class Entorno {
    
    Mapa mapa;
    int filAgente, colAgente;
    int filMeta, colMeta;    
    
    public Entorno(Mapa mapaEntorno, int filaAgente, int columnaAgente,
                    int filaMeta, int columnaMeta) {
        
        mapa = mapaEntorno;
        filAgente = filaAgente;
        colAgente = columnaAgente;
        filMeta = filaMeta;
        colMeta = columnaMeta;
    }
    
    public void mostrarEnTorno() {
        // Imprimir la matriz
        System.out.println("Numero filas: " + mapa.filas);
        System.out.println("Numero columnas: " + mapa.columnas);
        for (int i = 0; i < mapa.filas; i++) {

            for (int j = 0; j < mapa.columnas; j++) {
                if (filAgente == i && colAgente == j){
                    System.out.print("A\t");
                }
                else if (filMeta == i && colMeta == j) {
                    System.out.print("X\t");
                }
                else {
                    System.out.print(mapa.mapa[i][j] + "\t");
                }
            }
            System.out.println();
        }
    }
    
    public ArrayList<Integer> actualizarEntornoAgente() {
        ArrayList<Integer> entorno = new ArrayList<>();
        
        if (filAgente > 0 && colAgente > 0) {
            entorno.add(mapa.mapa[filAgente-1][colAgente-1]);   //Noroeste
        } else {
            entorno.add(-1);
        }

        if (filAgente > 0) {
            entorno.add(mapa.mapa[filAgente-1][colAgente]);     //Norte
        } else {
            entorno.add(-1);
        }

        if (filAgente > 0 && colAgente < mapa.mapa.length - 1) {
            entorno.add(mapa.mapa[filAgente-1][colAgente+1]);   //Noreste
        } else {
            entorno.add(-1);
        }

        if (colAgente < mapa.mapa.length - 1) {
            entorno.add(mapa.mapa[filAgente][colAgente+1]);     //Este
        } else {
            entorno.add(-1);
        }

        if (filAgente < mapa.mapa.length - 1 && colAgente < mapa.mapa[0].length - 1) {
            entorno.add(mapa.mapa[filAgente+1][colAgente+1]);   //Sureste
        } else {
            entorno.add(-1);
        }

        if (filAgente < mapa.mapa[0].length - 1) {
            entorno.add(mapa.mapa[filAgente+1][colAgente]);     //Sur
        } else {
            entorno.add(-1);
        }

        if (filAgente < mapa.mapa[0].length - 1 && colAgente > 0) {
            entorno.add(mapa.mapa[filAgente+1][colAgente-1]);   //Suroeste
        } else {
            entorno.add(-1);
        }

        if (colAgente > 0) {
            entorno.add(mapa.mapa[filAgente][colAgente-1]);     //Oeste
        } else {
            entorno.add(-1);
        }

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


    public boolean movimientoPosible(int filaMovimiento, int columnaMovimiento) {
        boolean posible = false;
    
        if (0 > filaMovimiento || filaMovimiento >= mapa.filas)
            posible = false;
        else if (0 > columnaMovimiento || columnaMovimiento >= mapa.columnas)
            posible = false;
        else if (mapa.mapa[filaMovimiento][columnaMovimiento] == 0)
            posible = true;
        else if (mapa.mapa[filaMovimiento][columnaMovimiento] == -1)
            posible = false;
    
        return posible;
    }

    /* public boolean movimientoPosible(int filaMovimiento, int columnaMovimiento) {
        boolean posible = false;
        
        if (0 > filaMovimiento || filaMovimiento >= mapa.filas)
            posible = false;
        else if (0 > columnaMovimiento || columnaMovimiento >= mapa.columnas)
            posible = false;
        else if (mapa.mapa[filaMovimiento][columnaMovimiento] == 0)
            posible = true;
        
        return posible;    
    }
    
    public boolean movimientoPosibleDiagonal(int filaMovimiento, int columnaMovimiento, DIRECCIONES direccion) {
        //Comprobamos que la casilla a la que se mueve es una casilla válida
        boolean posible = movimientoPosible(filaMovimiento, columnaMovimiento);
        
        //Como es un movimiento en diagonal hay que hacer más comprobaciones
        if (posible) {
            switch (direccion) {
                case NORTE:
                    // Add logic for NORTE if needed
                    break;
                case SUR:
                    // Add logic for SUR if needed
                    break;
                case ESTE:
                    // Add logic for ESTE if needed
                    break;
                case OESTE:
                    // Add logic for OESTE if needed
                    break;
                case NORESTE:
                    posible = (mapa.mapa[filAgente-1][colAgente] == 0 && mapa.mapa[filAgente][colAgente+1] == 0);
                    break;
                case NOROESTE:
                    posible = (mapa.mapa[filAgente-1][colAgente] == 0 && mapa.mapa[filAgente][colAgente-1] == 0);
                    break;
                case SURESTE:
                    posible = (mapa.mapa[filAgente+1][colAgente] == 0 && mapa.mapa[filAgente][colAgente+1] == 0);
                    break;
                case SUROESTE:
                    posible = (mapa.mapa[filAgente+1][colAgente] == 0 && mapa.mapa[filAgente][colAgente-1] == 0);
                    break;
            }
        }
        
        return posible;
    }     */
}
