
package practica2;
import java.util.ArrayList;


/**
 *
 * @author alvaro2311
 */
public class MovimientoEste implements Movimiento{
    
    private Agente agente;
    public boolean siguiendoPared = false;
    
    public MovimientoEste ( Agente agente){
        this.agente = agente;
    }
    
    @Override
    public double calculaMovimiento(){
        double distanciaEste = -1;
        
        // Dada la pos del agente, comprueba si puede moverse al este
        if (agente.see(agente.filAgente, agente.colAgente, DIRECCIONES.ESTE))
            distanciaEste = getUtility();
            //System.out.println("Distancia Este: " + distanciaEste);
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

    public double getUtility() {
        int distancia_y = Math.abs(agente.filMeta - agente.filAgente);
        int distancia_x = Math.abs(agente.colMeta - (agente.colAgente+1));
        double distancia = Math.sqrt(distancia_y*distancia_y + distancia_x*distancia_x);
        
        if (agente.actualizarVistaAlrededor.get(0) == -1 && agente.actualizarVistaAlrededor.get(1) == -1 && agente.actualizarVistaAlrededor.get(2) == -1 && agente.filMeta < agente.filAgente) {    
            siguiendoPared = true;
            agente.rodear1R = 1;
        }
        else if (agente.actualizarVistaAlrededor.get(6) == -1 && agente.actualizarVistaAlrededor.get(5) == -1 && agente.actualizarVistaAlrededor.get(4) == -1 && agente.filMeta > agente.filAgente){
            siguiendoPared = true;
            agente.rodear2R = 2;
        }
        else{
            if (siguiendoPared){
                agente.dejarDeSeguirPared = true;
                siguiendoPared = false;
            }
        }

        if (siguiendoPared)
            distancia -= 50;
        
        ArrayList<Integer> posicion = new ArrayList<>();
        posicion.add(agente.filAgente);
        posicion.add(agente.colAgente+1);

        for (ArrayList<Integer> pos : agente.caminoRecorrido) {
            if (pos.equals(posicion)) {
                distancia += 5;
            }
        }

        if (agente.noVolverAPasar != null)
        for (ArrayList<Integer> pos : agente.noVolverAPasar) {
            if (pos.equals(posicion)) {
                distancia += 500;
            }
        }

        return (distancia);
    }
}
