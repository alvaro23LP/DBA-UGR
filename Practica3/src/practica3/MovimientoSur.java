
package practica3;

import java.util.ArrayList;

/**
 *
 * @author alvaro2311
 */
public class MovimientoSur implements Movimiento{
    
    private Agente agente;
    public boolean siguiendoPared = false;
    public boolean noVolverAPasar = false;
    
    public MovimientoSur (Agente agente){
        this.agente = agente;
    }
    
    @Override
    public double calculaValorMovimiento(){
        double valorSur = -1;
        
        // Dada la pos del agente, comprueba si puede moverse al sur
        if (agente.see(agente.filaAgente, agente.columnaAgente, DIRECCIONES.SUR)) 
            valorSur = getUtility();
        return valorSur;
    }

    
    @Override
    public int getFila(){
        return agente.filaAgente+1;
    }
    
    @Override
    public int getColumna(){
        return agente.columnaAgente;
    }

    public double getUtility() {
        int distancia_y = Math.abs(agente.filaMeta - (agente.filaAgente+1));
        int distancia_x = Math.abs(agente.columnaMeta - agente.columnaAgente);
        double distancia = Math.sqrt(distancia_y*distancia_y + distancia_x*distancia_x);
        
        if (agente.actualizarVistaAlrededor.get(5) == -1 && agente.actualizarVistaAlrededor.get(6) == -1 && agente.actualizarVistaAlrededor.get(7) == -1)
            noVolverAPasar = true;
        else if (agente.actualizarVistaAlrededor.get(3) == -1 && agente.actualizarVistaAlrededor.get(4) == -1 && agente.actualizarVistaAlrededor.get(5) == -1)
            noVolverAPasar = true;
        else
            noVolverAPasar = false;
        
        //Comprueba si tiene una pared y la meta esta al otro lado
        if (agente.actualizarVistaAlrededor.get(0) == -1 && agente.actualizarVistaAlrededor.get(7) == -1 && agente.actualizarVistaAlrededor.get(6) == -1 && agente.columnaMeta < agente.columnaAgente) {    
            siguiendoPared = true;
        }
        else if (agente.actualizarVistaAlrededor.get(2) == -1 && agente.actualizarVistaAlrededor.get(3) == -1 && agente.actualizarVistaAlrededor.get(4) == -1 && agente.columnaMeta > agente.columnaAgente){
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
        posicion.add(agente.filaAgente+1);
        posicion.add(agente.columnaAgente);
        
        if (noVolverAPasar)
            agente.noVolverAPasar.add(posicion);

        // Comprueba si la posicion a la que se va a mover es una posicion que ya ha recorrido
        for (ArrayList<Integer> pos : agente.caminoRecorrido) {
            if (pos.equals(posicion)) {
                distancia += 5;
            }
        }
            
        // Comprueba si la posicion a la que se va a mover es una posicion que no debe volver a pasar
        if (agente.noVolverAPasar != null)
        for (ArrayList<Integer> pos : agente.noVolverAPasar) {
            if (pos.equals(posicion)) {
                distancia += 500;
            }
        }

        return (distancia);
    }
}
