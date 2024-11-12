
package practica2;

import java.util.ArrayList;

/**
 *
 * @author alvaro2311
 */
public class MovimientoSur implements Movimiento{
    
    private Agente agente;
    
    public MovimientoSur (Agente agente){
        this.agente = agente;
    }
    
    @Override
    public double calculaMovimiento(){
        double distanciaSur = -1;
        
        // Dada la pos del agente, comprueba si puede moverse al sur
        if (agente.see(agente.filAgente, agente.colAgente, DIRECCIONES.SUR)) 
            distanciaSur = getUtility();
            System.out.println("Distancia Sur: " + distanciaSur);
        return distanciaSur;
    }

    
    @Override
    public int getFila(){
        return agente.filAgente+1;
    }
    
    @Override
    public int getColumna(){
        return agente.colAgente;
    }

    public double getUtility() {
        int distancia_y = Math.abs(agente.filMeta - (agente.filAgente+1));
        int distancia_x = Math.abs(agente.colMeta - agente.colAgente);
        double distancia = Math.sqrt(distancia_y*distancia_y + distancia_x*distancia_x);
        
        ArrayList<Integer> posicion = new ArrayList<Integer>();
        posicion.add(agente.filAgente+1);
        posicion.add(agente.colAgente);
        
        int castigo = 0;
        int indicePos = agente.caminoRecorrido.lastIndexOf(posicion);
        
        if (indicePos != -1)
            castigo = 99 - (agente.caminoRecorrido.size() - indicePos);
            
        return (distancia);
    }
}
