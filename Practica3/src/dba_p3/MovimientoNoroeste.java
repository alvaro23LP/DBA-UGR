/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dba_p3;

/**
 *
 * @author Marcos
 */
public class MovimientoNoroeste implements Movimiento{
    
    private Entorno entorno;
    private AgenteP3 agente;
    
    public MovimientoNoroeste (Entorno entorno, AgenteP3 agente){
        this.entorno = entorno;
        this.agente = agente;
    }
    
    @Override
    public double calculaMovimiento(){
        double distanciaNoroeste = -1;
        
        if (entorno.movimientoPosibleDiagonal(entorno.getFilaAgente()-1,entorno.getColumnaAgente()-1, DIRECCIONES.NOROESTE)) 
            distanciaNoroeste = CalcularDistancia(entorno, entorno.getFilaAgente()-1,entorno.getColumnaAgente()-1, agente.getCaminoRecorrido());
        
        return distanciaNoroeste;
    }
    
    @Override
    public int getFila(){
        return entorno.getFilaAgente()-1;
    }
    
    @Override
    public int getColumna(){
        return entorno.getColumnaAgente()-1;
    }    
}
