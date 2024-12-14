package practica3;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class Msg_Agente_Elfo extends Behaviour {
    int step = 0;
    boolean finish = false;
    String  CONVERSATION_ID = "Canal_Agente-Elfo";

    @Override
    public void action() {
        switch (step){
            case 0 -> {
                ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
                msg.addReceiver(new AID("Elfo", AID.ISLOCALNAME));
                msg.setConversationId(CONVERSATION_ID);
                msg.setContent(((Agente) myAgent).transformarAMensajeGenZ("Santa quiero ofrecerme voluntario para la misión"));
                myAgent.send(msg);
                
                step = 1;
            }
            // case 1 -> {
            //     ACLMessage msg = myAgent.blockingReceive();
            //     System.out.println(msg);
            //     if (msg.getConversationId().equals(CONVERSATION_ID) && msg.getPerformative() == ACLMessage.INFORM){
            //         ACLMessage msgASanta = new ACLMessage(ACLMessage.PROPOSE);
            //         msgASanta.addReceiver(new AID("Santa", AID.ISLOCALNAME));
            //         msgASanta.setConversationId();

            //     }
            // }
        }
    }

    @Override
    public boolean done() {
        return finish;
    }
}
