package practica3;

import jade.core.Agent;

public class Santa extends Agent {
    public int numeroRenos;
    public int filSanta;
    public int colSanta;
    public String codigoCanal;

    
    @Override
    protected void setup() {
        numeroRenos = 0;
        filSanta = 10;
        colSanta = 10;
        codigoCanal = "CanalSecreto-53246";
        
        addBehaviour(new Msg_Santa_Agente());
        addBehaviour(new Msg_Santa_Elfo());
    }
    
  
    @Override
    public void takeDown() {
        System.out.println("Agente Santa finalizado");
        System.out.println();
    }
}
