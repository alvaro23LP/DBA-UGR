
package practica2;

import java.util.ArrayList;

/**
 *
 * @author alvaro2311
 */
public class MovimientoOeste implements Movimiento{
    
    private Agente agente;
    
    public MovimientoOeste (Agente agente){
        this.agente = agente;
    }
    
    @Override
    public double calculaMovimiento(){
        double distanciaOeste = -1;
        
        // Dada la pos del agente, comprueba si puede moverse al oeste
        if (agente.see(agente.filAgente, agente.colAgente, DIRECCIONES.OESTE)) 
            distanciaOeste = getUtility();
            System.out.println("Distancia Oeste: " + distanciaOeste);
            return distanciaOeste;
    }
    
    @Override
    public int getFila(){
        return agente.filAgente;
    }
    
    @Override
    public int getColumna(){
        return agente.colAgente-1;
    }


    public double getUtility() {
        int distancia_y = Math.abs(agente.filMeta - agente.filAgente);
        int distancia_x = Math.abs(agente.colMeta - agente.colAgente-1);
        double distancia = Math.sqrt(distancia_y*distancia_y + distancia_x*distancia_x);
        
        ArrayList<Integer> posicion = new ArrayList<Integer>();
        posicion.add(agente.filAgente);
        posicion.add(agente.colAgente);
        
        int castigo = 0;
        int indicePos = agente.caminoRecorrido.lastIndexOf(posicion);
        
        if (indicePos != -1)
            castigo = 99 - (agente.caminoRecorrido.size() - indicePos);
            
        return (distancia );
    }
}
