
package practica2;

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
        if (entorno.movimientoPosible(filaMovimiento, colMovimiento)) {
            entorno.filAgente = filaMovimiento;
            entorno.colAgente = colMovimiento;
            agente.filAgente = filaMovimiento;
            agente.colAgente = colMovimiento;
        }
        
        entorno.mostrarEnTorno();      
    }
    
    public boolean done() {
        if (agente.filAgente == agente.filMeta && agente.colAgente == agente.colMeta) {
            agente.doDelete();
            return true;
        }
        else
            return false;
    }
}
