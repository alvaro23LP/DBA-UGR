
package practica2;

/**
 *
 * @author alvaro2311
 */
public class MovimientoSur implements Movimiento{
    
    private Entorno entorno;
    private Agente agente;
    
    public MovimientoSur (Entorno entorno, Agente agente){
        this.entorno = entorno;
        this.agente = agente;
    }
    
    @Override
    public double calculaMovimiento(){
        double distanciaSur = -1;
        
        if (entorno.movimientoPosible(entorno.filAgente+1,entorno.colAgente)) 
            distanciaSur = getUtility(entorno, entorno.filAgente+1,entorno.colAgente, agente.caminoRecorrido);
        
        return distanciaSur;
    }
    
    @Override
    public int getFila(){
        return entorno.filAgente+1;
    }
    
    @Override
    public int getColumna(){
        return entorno.colAgente;
    }
}
