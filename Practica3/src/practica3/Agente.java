
package practica3;
import jade.core.Agent;
import practica3.GUI.MapaPanel;

import java.util.ArrayList;

/**
 *
 * @author alvaro2311
 */
public class Agente extends Agent{
    MapaPanel mapaPanel;

    Entorno entorno;
    int filaAgente, columnaAgente, filaMeta, columnaMeta;

    public ArrayList<ArrayList<Integer>> caminoRecorrido;
    public ArrayList<ArrayList<Integer>> noVolverAPasar;
    public ArrayList<Integer> pos_inicial;
    public ArrayList<Integer> actualizarVistaAlrededor;
    public boolean dejarDeSeguirDiagonal = false;
    public boolean dejarDeSeguirPared = false;
    public int rodear1D = 0;
    public int rodear2D = 0;

    public String codigoCanalRudolph;
    public boolean finBusqueda;
   
    protected void setup() {
        caminoRecorrido = new ArrayList<>();
        noVolverAPasar = new ArrayList<>();
        actualizarVistaAlrededor = new ArrayList<>();
        pos_inicial = new ArrayList<>();
        finBusqueda = false;
        codigoCanalRudolph = "";

        // Obtener los argumentos
        Object[] args = getArguments();
        if (args != null && args.length > 0) {
            entorno = (Entorno) args[0];
            mapaPanel = (MapaPanel) args[1];
        }

        // Inicializar posicion del agente
        this.filaAgente = entorno.filaAgente;
        this.columnaAgente = entorno.columnaAgente;
        
        pos_inicial.add(this.filaAgente);
        pos_inicial.add(this.columnaAgente);
        caminoRecorrido.add(pos_inicial);

        // Inicializar posicion de la meta
        this.filaMeta = entorno.filaMeta;
        this.columnaMeta = entorno.columnaMeta;
        
        // Comportamiento
        addBehaviour(new MejorMovimientoBehaviour(entorno, this));
        addBehaviour(new MovimientoBehaviour(entorno, this));
        addBehaviour(new Msg_Agente_Santa());
        addBehaviour(new Msg_Agente_Rudolph());
        addBehaviour(new Msg_Agente_Elfo());
    }
    
    public void moverAgente(int nuevaFila, int nuevaColumna) {
        this.filaAgente = nuevaFila;
        this.columnaAgente = nuevaColumna;
        mapaPanel.actualizarPosicionAgente(nuevaFila, nuevaColumna);
    }

    // Funcion final
    public void takeDown() {
        System.out.println(" ¡¡FIN!!  El agente ha llegado al objetivo en " + (caminoRecorrido.size()-1) + " pasos");
        System.out.println("Agente finalizado");
        // System.out.print("Camino recorrido: ");
        // for (ArrayList<Integer> posicion : caminoRecorrido) {
        //     System.out.print("(" + posicion.get(0) + "," + posicion.get(1) + ") ");
        // }
        System.out.println(); 
    }

    // Funcion para comprobar si el movimiento es posible
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
