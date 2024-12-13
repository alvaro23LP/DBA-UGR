
package practica3;

import java.util.ArrayList;

/**
 *
 * @author alvaro2311
 */
public class MovimientoNoreste implements Movimiento{
    
    private Agente agente;
    public boolean siguiendoDiagonal = false;
    
    public MovimientoNoreste(Agente agente){
        this.agente = agente;
    }
    
    @Override
    public double calculaValorMovimiento(){
        double valorNoreste = -1;
        
        // Dada la pos del agente, comprueba si puede moverse al noreste
        if (agente.see(agente.filaAgente, agente.columnaAgente, DIRECCIONES.NORESTE))
            valorNoreste = getUtility();
        return valorNoreste;
    }
    
    @Override
    public int getFila(){
        return agente.filaAgente-1;
    }
    
    @Override
    public int getColumna(){
        return agente.columnaAgente+1;
    }    

    public double getUtility() {
        int distancia_y = Math.abs(agente.filaMeta - (agente.filaAgente-1));
        int distancia_x = Math.abs(agente.columnaMeta - (agente.columnaAgente+1));
        double distancia = Math.sqrt(distancia_y*distancia_y + distancia_x*distancia_x);
        
        ArrayList<Integer> posicion = new ArrayList<Integer>();
        posicion.add(agente.filaAgente-1);
        posicion.add(agente.columnaAgente+1);
        
        ////Comprueba si tiene una pared diagonal
        if ((agente.actualizarVistaAlrededor.get(0) == 0 || agente.actualizarVistaAlrededor.get(0) == -1) && agente.actualizarVistaAlrededor.get(1) == -1 && agente.actualizarVistaAlrededor.get(7) == -1 && agente.rodear2D != 2 && (agente.filaMeta < agente.filaAgente || agente.columnaMeta < agente.columnaAgente)){
            siguiendoDiagonal = true;
            agente.rodear1D = 1;
        }
        else if ((agente.actualizarVistaAlrededor.get(4) == 0 || agente.actualizarVistaAlrededor.get(4) == -1) && agente.actualizarVistaAlrededor.get(3) == -1 && agente.actualizarVistaAlrededor.get(5) == -1 && agente.rodear1D != 1 && (agente.filaMeta > agente.filaAgente || agente.columnaMeta > agente.columnaAgente)){
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
            distancia -= 50;  //Le damos prefrencia a que siga la pared diagonal

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
