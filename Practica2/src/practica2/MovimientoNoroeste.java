
package practica2;
import java.util.ArrayList;

/**
 *
 * @author alvaro2311
 */
public class MovimientoNoroeste implements Movimiento{
    
    private Agente agente;
    
    public MovimientoNoroeste (Agente agente){
        this.agente = agente;
    }
    
    @Override
    public double calculaMovimiento(){
        double distanciaNoroeste = -1;
        
        // Dada la pos del agente, comprueba si puede moverse al noroeste
        if (agente.see(agente.filAgente, agente.colAgente, DIRECCIONES.NOROESTE)) 
            distanciaNoroeste = getUtility();
            System.out.println("Distancia Noroeste: " + distanciaNoroeste);
        return distanciaNoroeste;
    }
    
    @Override
    public int getFila(){
        return agente.filAgente-1;
    }
    
    @Override
    public int getColumna(){
        return agente.colAgente-1;
    }    

    public double getUtility() {
        int distancia_y = Math.abs(agente.filMeta - (agente.filAgente-1));
        int distancia_x = Math.abs(agente.colMeta - (agente.colAgente-1));
        double distancia = Math.sqrt(distancia_y*distancia_y + distancia_x*distancia_x);
        
        ArrayList<Integer> posicion = new ArrayList<Integer>();
        posicion.add(agente.filAgente-1);
        posicion.add(agente.colAgente-1);
        
        int castigo = 0;
        int indicePos = agente.caminoRecorrido.lastIndexOf(posicion);
        
        if (indicePos != -1)
            castigo = 99 - (agente.caminoRecorrido.size() - indicePos);
            
        return (distancia +castigo);
    } 
}
