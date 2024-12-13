
package practica3;
import java.util.ArrayList;

/**
 *
 * @author alvaro2311
 */
public class MovimientoNoroeste implements Movimiento{
    
    private Agente agente;
    public boolean siguiendoDiagonal = false;

    public MovimientoNoroeste (Agente agente){
        this.agente = agente;
    }
    
    @Override
    public double calculaValorMovimiento(){
        double valorNoroeste = -1;
        
        // Dada la pos del agente, comprueba si puede moverse al noroeste
        if (agente.see(agente.filaAgente, agente.columnaAgente, DIRECCIONES.NOROESTE)) 
            valorNoroeste = getUtility();
        return valorNoroeste;
    }
    
    @Override
    public int getFila(){
        return agente.filaAgente-1;
    }
    
    @Override
    public int getColumna(){
        return agente.columnaAgente-1;
    }    

    public double getUtility() {
        int distancia_y = Math.abs(agente.filaMeta - (agente.filaAgente-1));
        int distancia_x = Math.abs(agente.columnaMeta - (agente.columnaAgente-1));
        double distancia = Math.sqrt(distancia_y*distancia_y + distancia_x*distancia_x);
        
        ArrayList<Integer> posicion = new ArrayList<Integer>();
        posicion.add(agente.filaAgente-1);
        posicion.add(agente.columnaAgente-1);
        
        ////Comprueba si tiene una pared diagonal
        if ((agente.actualizarVistaAlrededor.get(6) == 0 || agente.actualizarVistaAlrededor.get(6) == -1) && agente.actualizarVistaAlrededor.get(5) == -1 && agente.actualizarVistaAlrededor.get(7) == -1 && agente.rodear2D != 2 && (agente.filaMeta > agente.filaAgente || agente.columnaMeta < agente.columnaAgente)){    
            siguiendoDiagonal = true;
            agente.rodear1D = 1;
        }
        else if ((agente.actualizarVistaAlrededor.get(2) == 0 || agente.actualizarVistaAlrededor.get(2) == -1) && agente.actualizarVistaAlrededor.get(1) == -1 && agente.actualizarVistaAlrededor.get(3) == -1 && agente.rodear1D != 1 && (agente.filaMeta < agente.filaAgente || agente.columnaMeta > agente.columnaAgente)){
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
            distancia -= 50; //Le damos preferencia a que siga la pared

        // Comprueba si la casilla a la que se quiere mover ya ha sido visitada
        for (ArrayList<Integer> pos : agente.caminoRecorrido) {
            if (pos.equals(posicion)) {
                distancia += 5;
            }
        }

        // //Comprueba si no puede volver a pasar por esa posicion
        if (agente.noVolverAPasar != null)
            for (ArrayList<Integer> pos : agente.noVolverAPasar) {
                if (pos.equals(posicion)) {
                    distancia += 500;
                }
            }

        return (distancia);
    } 
}
