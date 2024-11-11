
package practica2;

import java.util.ArrayList;

/**
 *
 * @author alvaro2311
 */
public class MovimientoNorte implements Movimiento {
    
    private Entorno entorno;
    private Agente agente;
    
    public MovimientoNorte (Entorno entorno, Agente agente){
        this.entorno = entorno;
        this.agente = agente;
    }
    
    @Override
    public double calculaMovimiento(){
        double distanciaNorte = -1;
        
        // Dada la pos del agente, comprueba si puede moverse al norte
        if (agente.see(agente.filAgente, agente.colAgente, DIRECCIONES.NORTE)) 
            distanciaNorte = getUtility(entorno, agente.filAgente-1,agente.colAgente, agente.caminoRecorrido);

        return distanciaNorte;
    }
    
    @Override
    public int getFila(){
        return agente.filAgente-1;
    }
    
    @Override
    public int getColumna(){
        return agente.colAgente;
    }
}
