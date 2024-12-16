package practica3;

import jade.core.Agent;

public class Santa extends Agent {
    public int numeroRenos;
    public int filSanta;
    public int colSanta;
    public String codigoCanal;
    public boolean conversandoConAgente, conversandoConElfo;
    public String mensajeTraducidoParaAgente, mensajeParaTraducirParaAgente;

    
    @Override
    protected void setup() {
        numeroRenos = 0;
        filSanta = P3.coordenadasSanta[0][0];
        colSanta = P3.coordenadasSanta[0][1];
        conversandoConAgente = true;
        conversandoConElfo = false;
        codigoCanal = "CanalSecreto-53246";
        mensajeTraducidoParaAgente = "";
        mensajeParaTraducirParaAgente = "";
        
        
        addBehaviour(new Msg_Santa_Agente());
        addBehaviour(new Msg_Santa_Elfo());
    }

    public String transformarAMensajeBoomer (String  mensaje) {
        return "Hyvää joulua, " + mensaje + ", Nähdään pian!";
    }
    
  
    @Override
    public void takeDown() {
        System.out.println("Agente Santa finalizado");
        System.out.println();
    }
}
