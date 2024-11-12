
package practica2;

import java.util.ArrayList;

/**
 *
 * @author alvaro2311
 */
public class MovimientoSuroeste implements Movimiento{
    
    private Agente agente;
    
    public MovimientoSuroeste (Agente agente){
        this.agente = agente;
    }
    
    @Override
    public double calculaMovimiento(){
        double distanciaSuroeste = -1;
        
        // Dada la pos del agente, comprueba si puede moverse al suroeste
        if (agente.see(agente.filAgente,agente.colAgente, DIRECCIONES.SUROESTE)) 
            distanciaSuroeste = getUtility();
            System.out.println("Distancia Suroeste: " + distanciaSuroeste);
            return distanciaSuroeste;
    }
    
    @Override
    public int getFila(){
        return agente.filAgente+1;
    }
    
    @Override
    public int getColumna(){
        return agente.colAgente-1;
    }


    public double getUtility() {
        int distancia_y = Math.abs(agente.filMeta - agente.filAgente+1);
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
