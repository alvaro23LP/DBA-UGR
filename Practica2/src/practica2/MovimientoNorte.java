
package practica2;

import java.util.ArrayList;

/**
 *
 * @author alvaro2311
 */
public class MovimientoNorte implements Movimiento {
    
    private Entorno entorno;
    private P2 agente;
    
    public MovimientoNorte (Entorno entorno, P2 agente){
        this.entorno = entorno;
        this.agente = agente;
    }
    
    @Override
    public double calculaMovimiento(){
        double distanciaNorte = -1;
        
        if (entorno.movimientoPosible(entorno.filAgente-1,entorno.colAgente)) 
            distanciaNorte = CalcularDistancia(entorno, entorno.filAgente-1,entorno.colAgente, agente.caminoRecorrido);
        
        return distanciaNorte;
    }
    
    @Override
    public int getFila(){
        return entorno.filAgente-1;
    }
    
    @Override
    public int getColumna(){
        return entorno.colAgente;
    }
}
