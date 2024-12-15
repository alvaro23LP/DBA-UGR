package practica3;

import jade.core.Agent;


public class Elfo extends Agent {
    
    @Override
    protected void setup() {
        addBehaviour(new Msg_Elfo_Agente());
        addBehaviour(new Msg_Elfo_Santa());        
    }

    public String traducirABoomer (String mensaje){
        String primeraParteAEliminar = "Bro, ";
        String segundaParteAEliminar = ", en plan...";
        String mensajeLimpio = mensaje.replace(primeraParteAEliminar, "");
        mensajeLimpio = mensajeLimpio.replace(segundaParteAEliminar, "");
        
        return "Joulupukki " + mensajeLimpio + " Kiitos";
    }

    public String traducirAGenZ (String  mensaje) {
        String primeraParteAEliminar = "Hyvää joulua, ";
        String segundaParteAEliminar = ", Nähdään pian!";
        String mensajeLimpio = mensaje.replace(primeraParteAEliminar, "");
        mensajeLimpio = mensajeLimpio.replace(segundaParteAEliminar, "");

        return "Bro, " + mensajeLimpio + ", en plan...";
    }

    @Override
    public void takeDown() {
        System.out.println("Agente Elfo finalizado");
        System.out.println();
    }
}
