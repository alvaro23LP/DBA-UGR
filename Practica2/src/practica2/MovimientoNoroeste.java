
package practica2;

/**
 *
 * @author alvaro2311
 */
public class MovimientoNoroeste implements Movimiento{
    
    private Entorno entorno;
    private Agente agente;
    
    public MovimientoNoroeste (Entorno entorno, Agente agente){
        this.entorno = entorno;
        this.agente = agente;
    }
    
    @Override
    public double calculaMovimiento(){
        double distanciaNoroeste = -1;
        
        // Dada la pos del agente, comprueba si puede moverse al noroeste
        if (agente.see(agente.filAgente, agente.colAgente, DIRECCIONES.NOROESTE)) 
            distanciaNoroeste = getUtility(entorno, agente.filAgente-1,agente.colAgente-1, agente.caminoRecorrido);
        
        return distanciaNoroeste;
    }

/*     @Override
    public double calculaMovimiento(){
        double distanciaNoroeste = -1;
        
        if (entorno.movimientoPosibleDiagonal(entorno.filAgente-1,entorno.colAgente-1, DIRECCIONES.NOROESTE)) 
            distanciaNoroeste = getUtility(entorno, entorno.filAgente-1,entorno.colAgente-1, agente.caminoRecorrido);
        
        return distanciaNoroeste;
    } */
    
    @Override
    public int getFila(){
        return agente.filAgente-1;
    }
    
    @Override
    public int getColumna(){
        return agente.colAgente-1;
    }    
}
