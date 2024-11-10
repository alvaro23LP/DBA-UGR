
package practica2;
import jade.core.Agent;
import java.util.ArrayList;

/**
 *
 * @author alvaro2311
 */
public class Agente extends Agent{

    Mapa mapa;
    Entorno entorno;
    public ArrayList<ArrayList<Integer>> caminoRecorrido;
   

    protected void setup() {
        // Inicializar caminoRecorrido
        caminoRecorrido = new ArrayList<>();

        // Obtener los argumentos
        Object[] args = getArguments();
        if (args != null && args.length > 0) {
            entorno = (Entorno) args[0];
        }
        
        addBehaviour(new MejorMovimientoBehaviour(entorno, this));
        addBehaviour(new MovimientoBehaviour(entorno, this));
    }
    
    public void takeDown() {
        System.out.println(" ¡¡FIN!!  El agente ha llegado al objetivo en " + caminoRecorrido.size() + " pasos");
    }
}
