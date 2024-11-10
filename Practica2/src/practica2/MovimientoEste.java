
package practica2;

/**
 *
 * @author alvaro2311
 */
public class MovimientoEste implements Movimiento{
    
    private Entorno entorno;
    private Agente agente;
    
    public MovimientoEste (Entorno entorno, Agente agente){
        this.entorno = entorno;
        this.agente = agente;
    }
    
    @Override
    public double calculaMovimiento(){
        double distanciaEste = -1;
        
        if (entorno.movimientoPosible(entorno.filAgente,entorno.colAgente+1))
            distanciaEste = getUtility(entorno, entorno.filAgente,entorno.colAgente+1, agente.caminoRecorrido);
        
        return distanciaEste;
    }
    
    @Override
    public int getFila(){
        return entorno.filAgente;
    }
    
    @Override
    public int getColumna(){
        return entorno.colAgente+1;
    }    
}
