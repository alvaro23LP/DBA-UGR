
package practica2;
import jade.core.Agent;
import java.util.ArrayList;

/**
 *
 * @author alvaro2311
 */
public class Agente extends Agent{

    Entorno entorno;
    public ArrayList<ArrayList<Integer>> caminoRecorrido;
    public ArrayList<ArrayList<Integer>> noVolverAPasar;
    public ArrayList<Integer> actualizarVistaAlrededor;
    public boolean dejarDeSeguirDiagonal = false;
    public boolean dejarDeSeguirPared = false;
    public int rodear1D = 0;
    public int rodear2D = 0;
    public int rodear1R = 0;
    public int rodear2R = 0;
    int filAgente, colAgente, filMeta, colMeta;
   
    protected void setup() {
        // Inicializar caminoRecorrido
        caminoRecorrido = new ArrayList<>();
        noVolverAPasar = new ArrayList<>();
        actualizarVistaAlrededor = new ArrayList<>();

        // Obtener los argumentos
        Object[] args = getArguments();
        if (args != null && args.length > 0) {
            entorno = (Entorno) args[0];
        }

        // Inicializar posicion del agente
        this.filAgente = entorno.filAgente;
        this.colAgente = entorno.colAgente;

        // Inicializar posicion de la meta
        this.filMeta = entorno.filMeta;
        this.colMeta = entorno.colMeta;
        
        // Comportamientos
        addBehaviour(new MejorMovimientoBehaviour(entorno, this));
        addBehaviour(new MovimientoBehaviour(entorno, this));
    }
    
    //Funcion final
    public void takeDown() {
        System.out.println(" ¡¡FIN!!  El agente ha llegado al objetivo en " + caminoRecorrido.size() + " pasos");
        System.out.print("Camino recorrido: ");
        for (ArrayList<Integer> posicion : caminoRecorrido) {
            System.out.print("(" + posicion.get(0) + "," + posicion.get(1) + ") ");
        }
        System.out.println(); // Para finalizar la línea
    }

    //Funcion para comprobar si el movimiento es posible
    public boolean see(int filaMovimiento, int columnaMovimiento, DIRECCIONES direccion) {
        boolean posible = false;
    
        switch (direccion) {
            case NORTE:
                posible = (entorno.sensorNorteAgente(filaMovimiento, columnaMovimiento) == 0);
                break;
            case NORESTE:
                posible = (entorno.sensorNoresteAgente(filaMovimiento, columnaMovimiento) == 0
                            && (entorno.sensorNorteAgente(filaMovimiento, columnaMovimiento) == 0 
                            || entorno.sensorEsteAgente(filaMovimiento, columnaMovimiento) == 0));
                break;
            case ESTE:
                posible = (entorno.sensorEsteAgente(filaMovimiento, columnaMovimiento) == 0);
                break;
            case SURESTE:
                posible = (entorno.sensorSuresteAgente(filaMovimiento, columnaMovimiento) == 0
                            && (entorno.sensorSurAgente(filaMovimiento, columnaMovimiento) == 0 
                            || entorno.sensorEsteAgente(filaMovimiento, columnaMovimiento) == 0));
                break;
            case SUR:
                posible = (entorno.sensorSurAgente(filaMovimiento, columnaMovimiento) == 0);
                break;
            case SUROESTE:
                posible = (entorno.sensorSuroesteAgente(filaMovimiento, columnaMovimiento) == 0
                            && (entorno.sensorSurAgente(filaMovimiento, columnaMovimiento) == 0 
                            || entorno.sensorOesteAgente(filaMovimiento, columnaMovimiento) == 0));
                break;
            case OESTE:
                posible = (entorno.sensorOesteAgente(filaMovimiento, columnaMovimiento) == 0);
                break;
            case NOROESTE:
                posible = (entorno.sensorNoroesteAgente(filaMovimiento, columnaMovimiento) == 0
                            && (entorno.sensorNorteAgente(filaMovimiento, columnaMovimiento) == 0 
                            || entorno.sensorOesteAgente(filaMovimiento, columnaMovimiento) == 0));
                break;
        }
    
        return posible;
    }

}
