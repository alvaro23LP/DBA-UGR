package practica3;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class Msg_Elfo_Agente extends Behaviour {
    int step = 0;
    boolean finish = false;

    @Override
    public void action() {
        if(((Elfo) myAgent).conversandoConAgente){
            switch (step){ 
                // Enviar mensaje al agente traducido a boomer (para que Santa lo entienda)
                case 0 -> {
                    ACLMessage msg = myAgent.blockingReceive();
                    // System.out.println("///////////////////////////////////////////////////////////////llegado a elfoooooooooooooooooooo*****************");
                    if (msg.getConversationId().equals(CONVERTAION_IDS.Canal_Agente_Elfo.name()) && msg.getPerformative() == ACLMessage.REQUEST && msg.getSender().getLocalName().equals("Agente")){
                        System.out.println(msg);
                        ACLMessage reply = msg.createReply(ACLMessage.INFORM);
                        String contenidoMsg = msg.getContent().split("\\$")[0];
                        reply.setContent(((Elfo) myAgent).traducirABoomer(contenidoMsg));

                        //Si la segunda parte del mensaje es 1, cambiamos el canal del elfo para comunicarse con Santa
                        if (msg.getContent().split("\\$")[1].equals("1")) {
                            ((Elfo) myAgent).conversandoConAgente = false;
                            ((Elfo) myAgent).conversandoConSanta = true;
                        }
                        myAgent.send(reply);

                    } else {
                        // System.out.println("**********************");
                        // System.out.println(msg.getConversationId());
                        // System.out.println(msg.getPerformative());
                        // System.out.println(msg.getSender().getLocalName());
                        // System.out.println("**********************");

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

