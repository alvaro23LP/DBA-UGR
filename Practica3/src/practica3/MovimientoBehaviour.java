
package practica3;

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
        filaMovimiento = (!agente.caminoRecorrido.isEmpty()) ? agente.caminoRecorrido.get(agente.caminoRecorrido.size()-1).get(0) : -1;
        colMovimiento = (!agente.caminoRecorrido.isEmpty()) ? agente.caminoRecorrido.get(agente.caminoRecorrido.size()-1).get(1) : -1;
        
        //Actualiza la posición del agente 
        entorno.filaAgente = filaMovimiento;
        entorno.columnaAgente = colMovimiento;
        agente.moverAgente(filaMovimiento, colMovimiento);
        
        entorno.imprimirMapaConsola();      
    }
    
    public boolean done() {
        if (agente.filaAgente == agente.filaMeta && agente.columnaAgente == agente.columnaMeta) {
            agente.doDelete();
            return true;
        }
        else
            return false;
    }
}
