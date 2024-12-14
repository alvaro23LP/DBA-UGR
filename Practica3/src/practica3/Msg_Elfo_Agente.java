package practica3;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class Msg_Elfo_Agente extends Behaviour {
    int step = 0;
    boolean finish = false;
    String  CONVERSATION_ID = "Canal_Agente-Elfo";

    @Override
    public void action() {
        switch (step){ 
            case 0 -> {
                ACLMessage msg = myAgent.blockingReceive();
                System.out.println(msg);
                if (msg.getConversationId().equals(CONVERSATION_ID) && msg.getPerformative() == ACLMessage.REQUEST){
                    ACLMessage reply = msg.createReply(ACLMessage.INFORM);
                    reply.setContent(((Elfo) myAgent).traducirABoomer(msg.getContent()));
                    myAgent.send(reply);

                } else {
                    System.out.println("Error en el protocolo de comunicación - paso 0");
                    myAgent.doDelete();
                }
            }
        }
    }

    @Override
    public boolean done() {
        return finish;
    }
}

