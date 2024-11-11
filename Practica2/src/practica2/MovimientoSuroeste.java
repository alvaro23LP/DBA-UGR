
package practica2;

/**
 *
 * @author alvaro2311
 */
public class MovimientoSuroeste implements Movimiento{
    
    private Entorno entorno;
    private Agente agente;
    
    public MovimientoSuroeste (Entorno entorno, Agente agente){
        this.entorno = entorno;
        this.agente = agente;
    }
    
    @Override
    public double calculaMovimiento(){
        double distanciaSuroeste = -1;
        
        // Dada la pos del agente, comprueba si puede moverse al suroeste
        if (agente.see(agente.filAgente,agente.colAgente, DIRECCIONES.SUROESTE)) 
            distanciaSuroeste = getUtility(entorno, agente.filAgente+1,agente.colAgente-1, agente.caminoRecorrido);

            return distanciaSuroeste;
    }
    
    @Override
    public int getFila(){
        return agente.filAgente+1;
    }
    
    @Override
    public int getColumna(){
        return agente.colAgente-1;
    }
}
