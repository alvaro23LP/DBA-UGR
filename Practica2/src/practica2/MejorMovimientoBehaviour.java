package practica2;

import jade.core.behaviours.Behaviour;
import java.lang.Thread;
import java.util.ArrayList;


public class MejorMovimientoBehaviour extends Behaviour{
    Entorno entorno;
    double mejorDistancia = 99999999;
    int filaMovimiento, colMovimiento;
    Agente agente;
    
    MovimientoNorte movNorte;
    MovimientoSur movSur;
    MovimientoEste movEste;
    MovimientoOeste movOeste;
    MovimientoNoreste movNoreste;
    MovimientoNoroeste movNoroeste;
    MovimientoSureste movSureste;
    MovimientoSuroeste movSuroeste;
    
    public MejorMovimientoBehaviour(Entorno entornoAgente, Agente agente) {
        this.entorno = entornoAgente;
        this.agente = agente;
        movNorte = new MovimientoNorte(entornoAgente, agente);
        movSur = new MovimientoSur(entornoAgente, agente);
        movEste = new MovimientoEste(entornoAgente, agente);
        movOeste = new MovimientoOeste(entornoAgente, agente);
        movNoreste = new MovimientoNoreste(entornoAgente, agente);
        movNoroeste = new MovimientoNoroeste(entornoAgente, agente);
        movSureste = new MovimientoSureste(entornoAgente, agente);
        movSuroeste = new MovimientoSuroeste(entornoAgente, agente);
    }
    
    @Override
    public void action() {
        mejorDistancia = 99999999;
       //Comprobamos que movimiento es mejor
       
       //Comprobamos movimiento norte
       double norte = movNorte.calculaMovimiento();
       if (norte != -1) {            
            if (norte < mejorDistancia) {
                mejorDistancia = norte;
                filaMovimiento = movNorte.getFila();
                colMovimiento = movNorte.getColumna();
            }            
       }
       
       //Comprobamos movimiento Este
       double este = movEste.calculaMovimiento();
       if(este != -1){
            if (este < mejorDistancia) {
                mejorDistancia = este;
                filaMovimiento = movEste.getFila();
                colMovimiento = movEste.getColumna();
            }
        }
       
       //Comprobamos movimiento Sur
       double sur = movSur.calculaMovimiento();
       if (sur != -1) {            
            if (sur < mejorDistancia) {
                mejorDistancia = sur;
                filaMovimiento = movSur.getFila();
                colMovimiento = movSur.getColumna();
            }            
       }
       
       //Comprobamos movimiento Oeste
       double oeste = movOeste.calculaMovimiento();
       if(oeste != -1){
            if (oeste < mejorDistancia) {
                mejorDistancia = oeste;
                filaMovimiento = movOeste.getFila();
                colMovimiento = movOeste.getColumna();
            }
       }
       
       //Comprobamos movimiento Noreste
       double norEste = movNoreste.calculaMovimiento();
       if(norEste != -1){
            if (norEste < mejorDistancia) {
                mejorDistancia = norEste;
                filaMovimiento = movNoreste.getFila();
                colMovimiento = movNoreste.getColumna();
            }
       }
       
       //Comprobamos movimiento Noroeste
       double norOeste = movNoroeste.calculaMovimiento();
       if(norOeste != -1){
            if (norOeste < mejorDistancia) {
                mejorDistancia = norOeste;
                filaMovimiento = movNoroeste.getFila();
                colMovimiento = movNoroeste.getColumna();
            }
       }
       
       //Comprobamos movimiento Sureste
       double surEste = movSureste.calculaMovimiento();
       if(surEste != -1){
            if (surEste < mejorDistancia) {
                mejorDistancia = surEste;
                filaMovimiento = movSureste.getFila();
                colMovimiento = movSureste.getColumna();
            }
       }
       
       //Comprobamos movimiento Suroeste
       double surOeste = movSuroeste.calculaMovimiento();
       if(surOeste != -1){
            if (surOeste < mejorDistancia) {
                mejorDistancia = surOeste;
                filaMovimiento = movSuroeste.getFila();
                colMovimiento = movSuroeste.getColumna();
            }
       }
       
       try {
           Thread.sleep(500);
       } catch (InterruptedException e) {
           System.out.print(e);
       }
       
       ArrayList<Integer> posicion = new ArrayList<Integer>();
       posicion.add(filaMovimiento);
       posicion.add(colMovimiento);
       
       agente.caminoRecorrido.add(posicion);       
    }
    
    @Override
    public boolean done() {
        if (agente.filAgente == agente.filMeta && agente.colAgente == agente.colMeta)
            return true;
        else
            return false;
    }
}
