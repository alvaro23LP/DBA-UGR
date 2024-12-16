package practica3;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class Msg_Santa_Elfo extends Behaviour {
    int step = 0;
    boolean finish = false;

    @Override
    public void action() {
        if(((Santa) myAgent).conversandoConElfo){
            switch (step){
                // Enviar mensaje al elfo para que lo traduzca (respuesta a la propuesta)
                case 0 -> {
                    ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
                    msg.addReceiver(new AID("Elfo", AID.ISLOCALNAME));
                    msg.setConversationId(CONVERTAION_IDS.Canal_Santa_Elfo.name());
                    msg.setContent(((Santa) myAgent).mensajeParaTraducirParaAgente+"$1"); // El 1 indica que el elfo debe cambiar de canal
                    myAgent.send(msg);

                    step = 1;
                }
                // Recibir mensaje del elfo traducido y almacenarlo. Cambiar de conversación a Agente
                case 1 -> {
                    ACLMessage msg = myAgent.blockingReceive();
                    System.out.println(msg);
                    if (msg.getConversationId().equals(CONVERTAION_IDS.Canal_Santa_Elfo.name()) && msg.getPerformative() == ACLMessage.INFORM){
                        
                        ((Santa) myAgent).mensajeTraducidoParaAgente = msg.getContent();
                        
                        step = 0;
                        
                        ((Santa) myAgent).conversandoConElfo = false;
                        ((Santa) myAgent).conversandoConAgente = true;

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
