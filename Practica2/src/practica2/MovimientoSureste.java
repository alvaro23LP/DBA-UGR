
package practica2;

import java.util.ArrayList;


/**
 *
 * @author alvaro2311
 */
public class MovimientoSureste implements Movimiento{
    
    private Agente agente;
    public boolean siguiendoDiagonal = false;

    public MovimientoSureste (Agente agente){
        this.agente = agente;
    }
    
    @Override
    public double calculaMovimiento(){
        double distanciaSureste = -1;
        
        // Dada la pos del agente, comprueba si puede moverse al sureste
        if (agente.see(agente.filAgente, agente.colAgente, DIRECCIONES.SURESTE)) 
            distanciaSureste = getUtility();
            //System.out.println("Distancia Sureste: " + distanciaSureste);
        return distanciaSureste;
    }
    
    @Override
    public int getFila(){
        return agente.filAgente+1;
    }
    
    @Override
    public int getColumna(){
        return agente.colAgente+1;
    }    

    public double getUtility() {
        int distancia_y = Math.abs(agente.filMeta - (agente.filAgente+1));
        int distancia_x = Math.abs(agente.colMeta - (agente.colAgente+1));
        double distancia = Math.sqrt(distancia_y*distancia_y + distancia_x*distancia_x);
        
        ArrayList<Integer> posicion = new ArrayList<Integer>();
        posicion.add(agente.filAgente+1);
        posicion.add(agente.colAgente+1);
        
        if ((agente.actualizarVistaAlrededor.get(6) == 0 || agente.actualizarVistaAlrededor.get(6) == -1) && agente.actualizarVistaAlrededor.get(5) == -1 && agente.actualizarVistaAlrededor.get(7) == -1 && agente.rodear2D != 2) {    
            siguiendoDiagonal = true;
            agente.rodear1D = 1;
        }
        else if ((agente.actualizarVistaAlrededor.get(2) == 0 || agente.actualizarVistaAlrededor.get(2) == -1) && agente.actualizarVistaAlrededor.get(1) == -1 && agente.actualizarVistaAlrededor.get(3) == -1 && agente.rodear1D != 1){
            siguiendoDiagonal = true;
            agente.rodear2D = 2;
        }
        else{
            if (siguiendoDiagonal){
                agente.dejarDeSeguirDiagonal = true;
                siguiendoDiagonal = false;
            }
        }

        if (siguiendoDiagonal)
            distancia -= 50;

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
