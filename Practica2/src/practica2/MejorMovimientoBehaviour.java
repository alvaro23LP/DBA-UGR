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
        movNorte = new MovimientoNorte(agente);
        movSur = new MovimientoSur(agente);
        movEste = new MovimientoEste(agente);
        movOeste = new MovimientoOeste(agente);
        movNoreste = new MovimientoNoreste(agente);
        movNoroeste = new MovimientoNoroeste(agente);
        movSureste = new MovimientoSureste(agente);
        movSuroeste = new MovimientoSuroeste(agente);

    }
    
    @Override
    public void action() {
        mejorDistancia = 99999999;

        if (agente.actualizarVistaAlrededor != null)
            agente.actualizarVistaAlrededor.clear();

        agente.actualizarVistaAlrededor = entorno.actualizarEntornoAgente();

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
       
       //Forzamos a que pase la diagonal
       if (agente.dejarDeSeguirDiagonal) {
        if (agente.caminoRecorrido.size() > 2) {
            agente.dejarDeSeguirDiagonal = false;
        
            ArrayList<Integer> ultima_pos = agente.caminoRecorrido.get(agente.caminoRecorrido.size() - 2);
            int x = ultima_pos.get(0);
            int y = ultima_pos.get(1);
        
        
        //Suroeste
        if (agente.filAgente-1 == x && agente.colAgente+1 == y && agente.see(agente.filAgente, agente.colAgente, DIRECCIONES.SUROESTE)){
            //Suroeste muro arriba
            if (agente.actualizarVistaAlrededor.get(1) == -1) {
                filaMovimiento = agente.filAgente-1;
                colMovimiento = agente.colAgente-1;
            }
            //Suroeste muro derecha
            else{
                filaMovimiento = agente.filAgente+1;
                colMovimiento = agente.colAgente+1;
            }
        
        agente.dejarDeSeguirDiagonal = false;
        
        }

        //Sureste
        if (agente.filAgente-1 == x && agente.colAgente-1 == y && agente.see(agente.filAgente, agente.colAgente, DIRECCIONES.SURESTE)){
            //Sureste muro arriba
            if (agente.actualizarVistaAlrededor.get(1) == -1) {
                filaMovimiento = agente.filAgente-1;
                colMovimiento = agente.colAgente+1;
            }
            //Sureste muro abajo
            else{
                filaMovimiento = agente.filAgente+1;
                colMovimiento = agente.colAgente-1;
            }

        agente.dejarDeSeguirDiagonal = false;
        }

        //Noroeste
        if (agente.filAgente+1 == x && agente.colAgente+1 == y && agente.see(agente.filAgente, agente.colAgente, DIRECCIONES.NOROESTE)){
            //Noroeste muro derecha
            if (agente.actualizarVistaAlrededor.get(3) == -1) {
                filaMovimiento = agente.filAgente-1;
                colMovimiento = agente.colAgente+1;
            }
            //Noroeste muro abajo
            else{
                filaMovimiento = agente.filAgente+1;
                colMovimiento = agente.colAgente-1;
            }

        agente.dejarDeSeguirDiagonal = false;
        }

        //Noreste
        if (agente.filAgente+1 == x && agente.colAgente-1 == y && agente.see(agente.filAgente, agente.colAgente, DIRECCIONES.NORESTE)){
            //Noreste muro arriba
            if (agente.actualizarVistaAlrededor.get(7) == -1) {
                filaMovimiento = agente.filAgente-1;
                colMovimiento = agente.colAgente-1;
            }
            //Noreste muro abajo
            else{
                filaMovimiento = agente.filAgente+1;
                colMovimiento = agente.colAgente+1;
            }

        agente.dejarDeSeguirDiagonal = false;
        }
    }
    }

    //forzamos a que pase el muro
    if (agente.dejarDeSeguirPared){
        ArrayList<Integer> ultima_pos = agente.caminoRecorrido.get(agente.caminoRecorrido.size() - 2);
        int x = ultima_pos.get(0);
        int y = ultima_pos.get(1);
        
        //Norte
        if (agente.filAgente+1 == x && agente.colAgente == y && agente.see(agente.filAgente, agente.colAgente, DIRECCIONES.NORTE)){
            //Suroeste muro arriba
            if (agente.actualizarVistaAlrededor.get(7) == -1) {
                filaMovimiento = agente.filAgente-1;
                colMovimiento = agente.colAgente-1;
            }
            else{
                filaMovimiento = agente.filAgente-1;
                colMovimiento = agente.colAgente+1;
            }
            //Suroeste muro derecha
            
        
        agente.dejarDeSeguirPared = false;
        }

        //Oeste
        if (agente.filAgente == x && agente.colAgente+1 == y && agente.see(agente.filAgente, agente.colAgente, DIRECCIONES.OESTE)){
            //Sureste muro abajo
            if (agente.actualizarVistaAlrededor.get(5) == -1) {
                filaMovimiento = agente.filAgente+1;
                colMovimiento = agente.colAgente-1;
            }
            agente.dejarDeSeguirPared = false;
        }

        //Este
        if (agente.filAgente == x && agente.colAgente-1 == y && agente.see(agente.filAgente, agente.colAgente, DIRECCIONES.ESTE)){
            //Noroeste muro abajo
            if (agente.actualizarVistaAlrededor.get(5) == -1) {
                filaMovimiento = agente.filAgente+1;
                colMovimiento = agente.colAgente+1;
            }
            else{
                filaMovimiento = agente.filAgente-1;
                colMovimiento = agente.colAgente+1;
            }
            agente.dejarDeSeguirPared = false;
        }

        //Sur
        if (agente.filAgente-1 == x && agente.colAgente == y && agente.see(agente.filAgente, agente.colAgente, DIRECCIONES.SUR)){
            //Noreste muro arriba
            if (agente.actualizarVistaAlrededor.get(3) == -1) {
                filaMovimiento = agente.filAgente+1;
                colMovimiento = agente.colAgente+1;
            }
            
            agente.dejarDeSeguirPared = false;
        }
    }

       ArrayList<Integer> posicion = new ArrayList<Integer>();
       posicion.add(filaMovimiento);
       posicion.add(colMovimiento);

        for (ArrayList<Integer> pos : agente.caminoRecorrido) {
            if (pos.equals(posicion)) {
                agente.noVolverAPasar.add(agente.caminoRecorrido.get(agente.caminoRecorrido.size()-1));  
            }
        }
    
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
