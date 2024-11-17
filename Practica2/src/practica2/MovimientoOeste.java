
package practica2;
import java.util.ArrayList;

/**
 *
 * @author alvaro2311
 */
public class MovimientoOeste implements Movimiento{
    
    private Agente agente;
    public boolean siguiendoPared = false;
    public boolean noVolverAPasar = false;
    
    public MovimientoOeste (Agente agente){
        this.agente = agente;
    }
    
    @Override
    public double calculaValorMovimiento(){
        double valorOeste = -1;
        
        // Dada la pos del agente, comprueba si puede moverse al oeste
        if (agente.see(agente.filaAgente, agente.columnaAgente, DIRECCIONES.OESTE)) 
            valorOeste = getUtility();
        return valorOeste;
    }
    
    @Override
    public int getFila(){
        return agente.filaAgente;
    }
    
    @Override
    public int getColumna(){
        return agente.columnaAgente-1;
    }


    public double getUtility() {
        int distancia_y = Math.abs(agente.filaMeta - agente.filaAgente);
        int distancia_x = Math.abs(agente.columnaMeta - (agente.columnaAgente-1));
        double distancia = Math.sqrt(distancia_y*distancia_y + distancia_x*distancia_x);
        
        if (agente.actualizarVistaAlrededor.get(1) == -1 && agente.actualizarVistaAlrededor.get(0) == -1 && agente.actualizarVistaAlrededor.get(7) == -1)  
            noVolverAPasar = true;
        else if (agente.actualizarVistaAlrededor.get(5) == -1 && agente.actualizarVistaAlrededor.get(6) == -1 && agente.actualizarVistaAlrededor.get(7) == -1)
            noVolverAPasar = true;
        else
            noVolverAPasar = false;
        
        //Comprueba si tiene una pared y la meta esta al otro lado
        if (agente.actualizarVistaAlrededor.get(0) == -1 && agente.actualizarVistaAlrededor.get(1) == -1 && agente.actualizarVistaAlrededor.get(2) == -1 && agente.filaMeta < agente.filaAgente) {    
            siguiendoPared = true;
        }
        else if (agente.actualizarVistaAlrededor.get(6) == -1 && agente.actualizarVistaAlrededor.get(5) == -1 && agente.actualizarVistaAlrededor.get(4) == -1 && agente.filaMeta > agente.filaAgente){
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
        posicion.add(agente.filaAgente);
        posicion.add(agente.columnaAgente-1);
        
        if (noVolverAPasar)
            agente.noVolverAPasar.add(posicion);

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
