
package practica2;
import java.util.ArrayList;

/**
 *
 * @author alvaro2311
 */
public class MovimientoOeste implements Movimiento{
    
    private Agente agente;
    public boolean siguiendoPared = false;
    
    public MovimientoOeste (Agente agente){
        this.agente = agente;
    }
    
    @Override
    public double calculaMovimiento(){
        double distanciaOeste = -1;
        
        // Dada la pos del agente, comprueba si puede moverse al oeste
        if (agente.see(agente.filAgente, agente.colAgente, DIRECCIONES.OESTE)) 
            distanciaOeste = getUtility();
            //System.out.println("Distancia Oeste: " + distanciaOeste);
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
        int distancia_x = Math.abs(agente.colMeta - (agente.colAgente-1));
        double distancia = Math.sqrt(distancia_y*distancia_y + distancia_x*distancia_x);
        
        //Comprueba si tiene una pared y la meta esta al otro lado
        if (agente.actualizarVistaAlrededor.get(0) == -1 && agente.actualizarVistaAlrededor.get(1) == -1 && agente.actualizarVistaAlrededor.get(2) == -1 && agente.filMeta < agente.filAgente) {    
            siguiendoPared = true;
        }
        else if (agente.actualizarVistaAlrededor.get(6) == -1 && agente.actualizarVistaAlrededor.get(5) == -1 && agente.actualizarVistaAlrededor.get(4) == -1 && agente.filMeta > agente.filAgente){
            siguiendoPared = true;
        }
        else{
            if (siguiendoPared){
                agente.dejarDeSeguirPared = true;
                siguiendoPared = false;
            }
        }

        if (siguiendoPared)
            distancia -= 50;  //Le damos prefrencia a que siga la pared
        
        ArrayList<Integer> posicion = new ArrayList<>();
        posicion.add(agente.filAgente);
        posicion.add(agente.colAgente-1);

        // Comprueba si la posicion a la que se va a mover es una posicion ya recorrida
        for (ArrayList<Integer> pos : agente.caminoRecorrido) {
            if (pos.equals(posicion)) {
                distancia += 5;
            }
        }

        // Comprueba si la posicion a la que se va a mover es una posicion a la que no se debe volver a pasar
        if (agente.noVolverAPasar != null)
        for (ArrayList<Integer> pos : agente.noVolverAPasar) {
            if (pos.equals(posicion)) {
                distancia += 500;
            }
        }

        return (distancia);
    }
}
