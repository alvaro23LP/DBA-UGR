
package practica2;

/**
 *
 * @author alvaro2311
 */
public class MovimientoNoreste implements Movimiento{
    
    private Entorno entorno;
    private P2 agente;
    
    public MovimientoNoreste(Entorno entorno, P2 agente){
        this.entorno = entorno;
        this.agente = agente;
    }
    
    @Override
    public double calculaMovimiento(){
        double distanciaNoreste = -1;
        
        if (entorno.movimientoPosibleDiagonal(entorno.filAgente-1,entorno.colAgente+1, DIRECCIONES.NORESTE))
            distanciaNoreste = CalcularDistancia(entorno, entorno.filAgente-1,entorno.colAgente+1, agente.caminoRecorrido);
        
        return distanciaNoreste;
    }
    
    @Override
    public int getFila(){
        return entorno.filAgente-1;
    }
    
    @Override
    public int getColumna(){
        return entorno.colAgente+1;
    }    
}
