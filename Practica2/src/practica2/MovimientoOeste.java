
package practica2;

/**
 *
 * @author alvaro2311
 */
public class MovimientoOeste implements Movimiento{
    
    private Entorno entorno;
    private Agente agente;
    
    public MovimientoOeste (Entorno entorno, Agente agente){
        this.entorno = entorno;
        this.agente = agente;
    }
    
    @Override
    public double calculaMovimiento(){
        double distanciaOeste = -1;
        
        // Dada la pos del agente, comprueba si puede moverse al oeste
        if (agente.see(agente.filAgente, agente.colAgente, DIRECCIONES.OESTE)) 
            distanciaOeste = getUtility(entorno, agente.filAgente, agente.colAgente-1, agente.caminoRecorrido);

            return distanciaOeste;
    }
    
    @Override
    public int getFila(){
        return agente.filAgente;
    }
    
    @Override
    public int getColumna(){
        return agente.colAgente-1;
    }
}
