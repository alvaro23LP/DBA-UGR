package practica3;

import jade.core.Agent;


public class Elfo extends Agent {
    
    @Override
    protected void setup() {
        addBehaviour(new Msg_Elfo_Agente());
        addBehaviour(new Msg_Elfo_Santa());        
    }

    @Override
    public void takeDown() {
        System.out.println("Agente Elfo finalizado");
        System.out.println();
    }
}
