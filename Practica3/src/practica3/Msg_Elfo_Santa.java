package practica3;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class Msg_Elfo_Santa extends Behaviour {
    int step = 0;
    boolean finish = false;

    @Override
    public void action() {
        switch (step){
            // Enviar el menaje a Santa traducido a GenZ (para que el agente lo entienda)
            case 0 -> {
                ACLMessage msg = myAgent.blockingReceive();
                if (msg.getConversationId().equals(CONVERTAION_IDS.Canal_Santa_Elfo.name()) && msg.getPerformative() == ACLMessage.REQUEST && msg.getSender().getLocalName().equals("Santa")){
                    System.out.println(msg);
                    ACLMessage reply = msg.createReply(ACLMessage.INFORM);
                    reply.setContent(((Elfo) myAgent).traducirAGenZ(msg.getContent()));
                    myAgent.send(reply);

                } else {
                    // System.out.println("Error en el protocolo de comunicación - paso 0");
                    // myAgent.doDelete();
                }
            }
        }
        
    }

    @Override
    public boolean done() {
        return finish;
    }
}
