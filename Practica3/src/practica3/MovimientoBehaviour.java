
package practica3;

import java.util.ArrayList;

import jade.core.behaviours.Behaviour;

/**
 *
 * @author alvaro2311
 */
public class MovimientoBehaviour extends Behaviour {
    
    Entorno entorno;
    int filaMovimiento;
    int colMovimiento;
    Agente agente;
    
    public MovimientoBehaviour(Entorno entornoAgente, Agente agente) {
        entorno = entornoAgente;
        this.agente = agente;
    }
    
    public void action() {
        if (!agente.buscandoRenos && !agente.buscandoSanta || agente.stopMovimiento) {
            return;
        }

        // Array para ver si ya hemos pasado por esa posicion
        ArrayList<Integer> posicion = new ArrayList<Integer>();
        posicion.add(agente.filMovimiento);
        posicion.add(agente.colMovimiento);

        // Comprobamos si la posicion ya ha sido visitada para meter a la casilla que
        // nos lleva a ella en noVolverAPasar
        for (ArrayList<Integer> pos : agente.caminoRecorrido) {
            if (pos.equals(posicion)) {
                agente.noVolverAPasar.add(agente.caminoRecorrido.get(agente.caminoRecorrido.size() - 1));
            }
        }

        // Añadimos la posicion a caminoRecorrido
        agente.caminoRecorrido.add(posicion);

        filaMovimiento = (!agente.caminoRecorrido.isEmpty()) ? agente.caminoRecorrido.get(agente.caminoRecorrido.size()-1).get(0) : -1;
        colMovimiento = (!agente.caminoRecorrido.isEmpty()) ? agente.caminoRecorrido.get(agente.caminoRecorrido.size()-1).get(1) : -1;
        
        //Actualiza la posición del agente 
        entorno.filaAgente = filaMovimiento;
        entorno.columnaAgente = colMovimiento;
        agente.moverAgente(filaMovimiento, colMovimiento);
        agente.contadorDePasos++;
        System.out.println("Movimiento: " + agente.contadorDePasos);
        
        //entorno.imprimirMapaConsola();      
    }
    
    public boolean done() {
        if (agente.filaAgente == agente.filaMeta && agente.columnaAgente == agente.columnaMeta) {
            agente.filaMeta = -1;
            agente.columnaMeta = -1;
            agente.conversandoConElfo = true;
            agente.stopMovimiento = true;
            agente.caminoRecorrido.clear();
            if (agente.buscandoSanta){
                agente.doDelete();
                return true;
            }else{
                return false;
            }
        }
        else
            return false;
    }
}
