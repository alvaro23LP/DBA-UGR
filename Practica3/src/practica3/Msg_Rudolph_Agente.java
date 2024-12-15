package practica3;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import java.util.ArrayList;

public class Msg_Rudolph_Agente extends Behaviour {
    int step = 0;
    boolean finish = false;
    ACLMessage msgAnterior = null;
    int contadorRenos = 0;

    @Override
    public void action() {
        switch (step) {
            // Comprobar si el código es correcto
            case 0 -> {
                ACLMessage msg = myAgent.blockingReceive();
                if (msg.getConversationId().equals(((Rudolph) myAgent).codigoCanal) && msg.getPerformative() == ACLMessage.REQUEST && msg.getSender().toString().equals("Agente")) {
                    System.out.println(msg);
                    ACLMessage reply = msg.createReply(ACLMessage.AGREE);
                    reply.setContent("Veo que el codigo es correcto, puedes ayudarme en la misión");
                    myAgent.send(reply);

                    step = 1;
                } else if (!msg.getConversationId().equals(((Rudolph) myAgent).codigoCanal) && msg.getPerformative() == ACLMessage.REQUEST && msg.getSender().toString().equals("Agente")) {
                    System.out.println(msg);
                    ACLMessage reply = msg.createReply(ACLMessage.REFUSE);
                    reply.setContent("Veo que tratas de engañarme, el código que me has mandado no es el correcto");
                    myAgent.send(reply);
                } else {
                    System.out.println("Error en el protocolo de comunicación - paso 0");
                    myAgent.doDelete();
                } 
            }
            // Esperar a que el agente pida coordenadas de un reno
            case 1 -> {
                ACLMessage msg = myAgent.blockingReceive();
                if (msg.getConversationId().equals(((Rudolph) myAgent).codigoCanal) && msg.getPerformative() == ACLMessage.REQUEST && msg.getSender().toString().equals("Agente")) {
                    // cada vez que le digamos la posicion de un reno, incrementamos el contador de renos en 1, cuando llegue a 8, le comunicamos que no hay más renos perdidos
                    System.out.println(msg);
                    ACLMessage reply = msg.createReply(ACLMessage.INFORM);
                    if (contadorRenos < 8) {
                        reply.setContent("Reno " + contadorRenos + " - posción:" + ((Rudolph) myAgent).coordenadasRenos[contadorRenos][0] + "," + ((Rudolph) myAgent).coordenadasRenos[contadorRenos][1]);
                        contadorRenos++;
                    } else {
                        reply.setContent("Ya has encontrado a todos los renos, debes hablar con Santa!");
                    }
                    
                    myAgent.send(reply);

                } else {
                    System.out.println("Error en el protocolo de comunicación - paso 1");
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

