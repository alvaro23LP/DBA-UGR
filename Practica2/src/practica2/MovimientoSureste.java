
package practica2;

/**
 *
 * @author alvaro2311
 */
public class MovimientoSureste implements Movimiento{
    
    private Entorno entorno;
    private Agente agente;
    
    public MovimientoSureste (Entorno entorno, Agente agente){
        this.entorno = entorno;
        this.agente = agente;
    }
    
    @Override
    public double calculaMovimiento(){
        double distanciaSureste = -1;
        
        // Dada la pos del agente, comprueba si puede moverse al sureste
        if (agente.see(agente.filAgente, agente.colAgente, DIRECCIONES.SURESTE)) 
            distanciaSureste = getUtility(entorno, agente.filAgente+1,agente.colAgente+1, agente.caminoRecorrido);
        
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
}
