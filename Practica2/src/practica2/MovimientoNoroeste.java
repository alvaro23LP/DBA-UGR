
package practica2;

/**
 *
 * @author alvaro2311
 */
public class MovimientoNoroeste implements Movimiento{
    
    private Entorno entorno;
    private P2 agente;
    
    public MovimientoNoroeste (Entorno entorno, P2 agente){
        this.entorno = entorno;
        this.agente = agente;
    }
    
    @Override
    public double calculaMovimiento(){
        double distanciaNoroeste = -1;
        
        if (entorno.movimientoPosibleDiagonal(entorno.filAgente-1,entorno.colAgente-1, DIRECCIONES.NOROESTE)) 
            distanciaNoroeste = CalcularDistancia(entorno, entorno.filAgente-1,entorno.colAgente-1, agente.caminoRecorrido);
        
        return distanciaNoroeste;
    }
    
    @Override
    public int getFila(){
        return entorno.filAgente-1;
    }
    
    @Override
    public int getColumna(){
        return entorno.colAgente-1;
    }    
}
