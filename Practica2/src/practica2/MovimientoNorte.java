
package practica2;

import java.util.ArrayList;

/**
 *
 * @author alvaro2311
 */
public class MovimientoNorte implements Movimiento {
    
    private Agente agente;
    public boolean siguiendoPared = false;
    
    public MovimientoNorte (Agente agente){
        this.agente = agente;
    }
    
    @Override
    public double calculaValorMovimiento(){
        double distanciaNorte = -1;
        
        // Dada la pos del agente, comprueba si puede moverse al norte
        if (agente.see(agente.filAgente, agente.colAgente, DIRECCIONES.NORTE)) 
            distanciaNorte = getUtility();
            //System.out.println("Distancia Norte: " + distanciaNorte);

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

    public double getUtility() {
        int distancia_y = Math.abs(agente.filMeta - (agente.filAgente-1));
        int distancia_x = Math.abs(agente.colMeta - agente.colAgente);
        double distancia = Math.sqrt(distancia_y*distancia_y + distancia_x*distancia_x);
        
        //Comprueba si tiene una pared y la meta esta al otro lado
        if (agente.actualizarVistaAlrededor.get(0) == -1 && agente.actualizarVistaAlrededor.get(7) == -1 && agente.actualizarVistaAlrededor.get(6) == -1 && agente.colMeta < agente.colAgente) {    
            siguiendoPared = true;
        }
        else if (agente.actualizarVistaAlrededor.get(2) == -1 && agente.actualizarVistaAlrededor.get(3) == -1 && agente.actualizarVistaAlrededor.get(4) == -1 && agente.colMeta > agente.colAgente){
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
        posicion.add(agente.filAgente-1);
        posicion.add(agente.colAgente);


        //Comprueba si ya ha pasado por esa posicion para evitar ciclos
        for (ArrayList<Integer> pos : agente.caminoRecorrido) {
            if (pos.equals(posicion)) {
                distancia += 5;
            }
        }

        //Comprueba si no puede volver a pasar por esa posicion
        if (agente.noVolverAPasar != null)
        for (ArrayList<Integer> pos : agente.noVolverAPasar) {
            if (pos.equals(posicion)) {
                distancia += 500;
            }
        }
        
        return (distancia);
    }
}
