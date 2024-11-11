
package practica2;

/**
 *
 * @author alvaro2311
 */
public class MovimientoNoreste implements Movimiento{
    
    private Entorno entorno;
    private Agente agente;
    
    public MovimientoNoreste(Entorno entorno, Agente agente){
        this.entorno = entorno;
        this.agente = agente;
    }
    
    @Override
    public double calculaMovimiento(){
        double distanciaNoreste = -1;
        
        // Dada la pos del agente, comprueba si puede moverse al noreste
        if (agente.see(agente.filAgente, agente.colAgente, DIRECCIONES.NORESTE))
            distanciaNoreste = getUtility(entorno, agente.filAgente-1,agente.colAgente+1, agente.caminoRecorrido);

        return distanciaNoreste;
    }
    
    @Override
    public int getFila(){
        return agente.filAgente-1;
    }
    
    @Override
    public int getColumna(){
        return agente.colAgente+1;
    }    
}
