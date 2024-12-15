package practica3;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class Msg_Agente_Elfo extends Behaviour {
    int step = 0;
    boolean finish = false;

    @Override
    public void action() {
        if (((Agente) myAgent).conversandoConElfo){
            
            switch (step){
                // Enviar mensaje al elfo para que lo traduzca (ofrecerse para la misión)
                case 0 -> {
                    ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
                    msg.addReceiver(new AID("Elfo", AID.ISLOCALNAME));
                    msg.setConversationId(CONVERTAION_IDS.Canal_Agente_Elfo.name());
                    ((Agente) myAgent).mensajeParaTraducirParaSanta = ((Agente) myAgent).transformarAMensajeGenZ("Santa quiero ofrecerme voluntario para la misión");
                    msg.setContent(((Agente) myAgent).mensajeParaTraducirParaSanta);
                    myAgent.send(msg);
                    
                    step = 1;
                }
                // Recibir mensaje del elfo traducido y almacenarlo. Cambiar de conversación a Santa
                case 1 -> {
                    ACLMessage msg = myAgent.blockingReceive();
                    System.out.println(msg);
                    if (msg.getConversationId().equals(CONVERTAION_IDS.Canal_Agente_Elfo.name()) && msg.getPerformative() == ACLMessage.INFORM){
                        
                        ((Agente) myAgent).mensajeTraducidoParaSanta = msg.getContent();
                        
                        step = 2;
                        
                        ((Agente) myAgent).conversandoConElfo = false;
                        ((Agente) myAgent).conversandoConSanta = true;

                    } else {
                        System.out.println("Error en el protocolo de comunicación - paso 1");
                        myAgent.doDelete();
                    }
                }
            }
        }
    }

    @Override
    public boolean done() {
        return finish;
    }
}
