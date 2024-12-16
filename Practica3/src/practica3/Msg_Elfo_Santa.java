package practica3;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class Msg_Elfo_Santa extends Behaviour {
    int step = 0;
    boolean finish = false;

    @Override
    public void action() {
        if(((Elfo) myAgent).conversandoConSanta){
            
            switch (step){
                // Enviar el menaje a Santa traducido a GenZ (para que el agente lo entienda)
                case 0 -> {
                    ACLMessage msg = myAgent.blockingReceive();
                    // System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++llegado a elfoooooooooooooooooooo*****************");
                    if (msg.getConversationId().equals(CONVERTAION_IDS.Canal_Santa_Elfo.name()) && msg.getPerformative() == ACLMessage.REQUEST && msg.getSender().getLocalName().equals("Santa")){
                        System.out.println(msg);
                        ACLMessage reply = msg.createReply(ACLMessage.INFORM);
                        String contenidoMsg = msg.getContent().split("\\$")[0];
                        reply.setContent(((Elfo) myAgent).traducirAGenZ(contenidoMsg));
                        //Si la segunda parte del mensaje es 1, cambiamos el canal del elfo para comunicarse con Agente
                        if (msg.getContent().split("\\$")[1].equals("1")) {
                            ((Elfo) myAgent).conversandoConAgente = true;
                            ((Elfo) myAgent).conversandoConSanta = false;
                        }
                        myAgent.send(reply);

                    } else {
                        // System.out.println("Error en el protocolo de comunicación - paso 0");
                        // myAgent.doDelete();
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
