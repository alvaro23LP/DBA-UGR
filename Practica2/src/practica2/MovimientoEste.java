
package practica2;

import java.util.ArrayList;

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
        
        // Dada la pos del agente, comprueba si puede moverse al este
        if (agente.see(agente.filAgente, agente.colAgente, DIRECCIONES.ESTE))
            distanciaEste = getUtility(entorno, agente.filAgente,agente.colAgente+1, agente.caminoRecorrido);

        return distanciaEste;
    }
    
    @Override
    public int getFila(){
        return agente.filAgente;
    }
    
    @Override
    public int getColumna(){
        return agente.colAgente+1;
    }    

/*     public double getUtility() {
        int distancia_y = Math.abs(agente.filMeta - agente.filAgente);
        int distancia_x = Math.abs(agente.colMeta - agente.colAgente);
        double distancia = Math.sqrt(distancia_y*distancia_y + distancia_x*distancia_x);
        
        ArrayList<Integer> posicion = new ArrayList<Integer>();
        posicion.add(agente.filAgente);
        posicion.add(agente.colAgente);
        
        int castigo = 0;
        int indicePos = agente.caminoRecorrido.lastIndexOf(posicion);
        
        if (indicePos != -1)
            castigo = 99 - (agente.caminoRecorrido.size() - indicePos);
            
        return (distancia + castigo);
    } */
}
