
package practica2;

/**
 *
 * @author alvaro2311
 */
public class MovimientoOeste implements Movimiento{
    
    private Entorno entorno;
    private P2 agente;
    
    public MovimientoOeste (Entorno entorno, P2 agente){
        this.entorno = entorno;
        this.agente = agente;
    }
    
    @Override
    public double calculaMovimiento(){
        double distanciaOeste = -1;
        
        if (entorno.movimientoPosible(entorno.filAgente,entorno.colAgente-1)) 
            distanciaOeste = CalcularDistancia(entorno, entorno.filAgente,entorno.colAgente-1, agente.caminoRecorrido);
        
        return distanciaOeste;
    }
    
    @Override
    public int getFila(){
        return entorno.filAgente;
    }
    
    @Override
    public int getColumna(){
        return entorno.colAgente-1;
    }
}
