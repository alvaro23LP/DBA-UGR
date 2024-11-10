
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
        
        if (entorno.movimientoPosibleDiagonal(entorno.filAgente+1,entorno.colAgente-1, DIRECCIONES.SUROESTE)) 
            distanciaSuroeste = getUtility(entorno, entorno.filAgente+1,entorno.colAgente-1, agente.caminoRecorrido);
        
        return distanciaSuroeste;
    }
    
    @Override
    public int getFila(){
        return entorno.filAgente+1;
    }
    
    @Override
    public int getColumna(){
        return entorno.colAgente-1;
    }
}
