
package practica2;

/**
 *
 * @author alvaro2311
 */
public class MovimientoSureste implements Movimiento{
    
    private Entorno entorno;
    private P2 agente;
    
    public MovimientoSureste (Entorno entorno, P2 agente){
        this.entorno = entorno;
        this.agente = agente;
    }
    
    @Override
    public double calculaMovimiento(){
        double distanciaSureste = -1;
        
        if (entorno.movimientoPosibleDiagonal(entorno.filAgente+1,entorno.colAgente+1, DIRECCIONES.SURESTE)) 
            distanciaSureste = CalcularDistancia(entorno, entorno.filAgente+1,entorno.colAgente+1, agente.caminoRecorrido);
        
        return distanciaSureste;
    }
    
    @Override
    public int getFila(){
        return entorno.filAgente+1;
    }
    
    @Override
    public int getColumna(){
        return entorno.colAgente+1;
    }    
}
