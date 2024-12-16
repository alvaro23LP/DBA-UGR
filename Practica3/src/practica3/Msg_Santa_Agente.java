package practica3;

//import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class Msg_Santa_Agente extends Behaviour {
    int step = 0;
    boolean finish = false;
    ACLMessage msgAnterior = null;
    boolean aceptarMision = false;

    @Override
    public void action() {
        if (((Santa) myAgent).conversandoConAgente) {
            switch (step) {
                // Recibir mensaje del agente que se ofrece voluntario
                case 0 -> {
                    System.out.println("**********************");
                    ACLMessage msg = myAgent.blockingReceive();
                    if (msg.getConversationId().equals(CONVERTAION_IDS.Canal_Agente_Santa.name()) && msg.getPerformative() == ACLMessage.PROPOSE && msg.getSender().getLocalName().equals("Agente")) {
                        System.out.println(msg);
                        msgAnterior = msg;

                        // Numero aleatorio del 0 al 10, si es mayor a 2, Santa acepta la propuesta, si no la rechaza
                        if (Math.random() * 10 > 2) {
                            ((Santa) myAgent).mensajeParaTraducirParaAgente = ((Santa) myAgent).transformarAMensajeBoomer("Santa acepta tu propuesta por ser un niño bueno, aqui tienes el código:" + ((Santa) myAgent).codigoCanal + ":");
                            aceptarMision = true;
                        } else {
                            ((Santa) myAgent).mensajeParaTraducirParaAgente = ((Santa) myAgent).transformarAMensajeBoomer("Santa cree que eres un niño malo, no quiero tu ayuda en la misión");
                            aceptarMision = false;
                        }

                        step = 1;
                        ((Santa) myAgent).conversandoConAgente = false;
                        ((Santa) myAgent).conversandoConElfo = true;

                    } else {
                        System.out.println("Error en el protocolo de comunicación - paso 0");
                        myAgent.doDelete();
                    }
                }
                // En caso de aceptar la propuesta
                case 1 -> {
                    if (aceptarMision) {
                        ACLMessage reply = msgAnterior.createReply(ACLMessage.ACCEPT_PROPOSAL);
                        reply.setContent(((Santa) myAgent).mensajeTraducidoParaAgente);
                        myAgent.send(reply);
                    } else {
                        ACLMessage reply = msgAnterior.createReply(ACLMessage.REJECT_PROPOSAL);
                        reply.setContent(((Santa) myAgent).mensajeTraducidoParaAgente);
                        myAgent.send(reply);
                    }
                    step = 2;
                }
                // Mientras se encuentran los renos, Santa espera
                case 2 -> {
                    // El agente le va informando cada vez que se encuentra un reno, mientras recibe los mensajes inform, santa incrementa su contador hasta llegar a 8, momento en el que pasa al siguiente paso
                    ACLMessage msg = myAgent.blockingReceive();
                    if (msg.getConversationId().equals(CONVERTAION_IDS.Canal_Agente_Santa.name()) && msg.getPerformative() == ACLMessage.INFORM && msg.getSender().getLocalName().equals("Agente")) {
                        System.out.println(msg);
                        ((Santa) myAgent).numeroRenos++;
                        System.out.println("Santa: Bien! Ya tenemos " + ((Santa) myAgent).numeroRenos);
                        if (((Santa) myAgent).numeroRenos == 8) {
                            step = 3;
                        }
                    } else {
                        System.out.println("Error en el protocolo de comunicación - paso 2");
                        myAgent.doDelete();
                    }
                }
                // Esperar a que el Agente pregunte la localización
                case 3 -> {
                    ACLMessage msg = myAgent.blockingReceive();
                    if (msg.getConversationId().equals(CONVERTAION_IDS.Canal_Agente_Santa.name()) && msg.getPerformative() == ACLMessage.REQUEST && msg.getSender().getLocalName().equals("Agente")) {
                        System.out.println(msg);
                        msgAnterior = msg;
                        ((Santa) myAgent).mensajeParaTraducirParaAgente = ((Santa) myAgent).transformarAMensajeBoomer("Traeme mis renos chico, estoy en esta localización:" + ((Santa) myAgent).filSanta + "," + ((Santa) myAgent).colSanta) + ":";
                        
                        step = 4;
                        ((Santa) myAgent).conversandoConAgente = false;
                        ((Santa) myAgent).conversandoConElfo = true;
                    } else {
                        System.out.println("Error en el protocolo de comunicación - paso 3");
                        myAgent.doDelete();
                    }
                }
                // Reenvio de mensaje traducido al agente con las coordenadas
                case 4 -> {                    
                    ACLMessage reply = msgAnterior.createReply(ACLMessage.INFORM);
                    reply.setContent(((Santa) myAgent).mensajeTraducidoParaAgente);
                    myAgent.send(reply);
                    
                    step = 5;
                }
                // Respuesta y envío del mensaje final (HoHoHo!)
                case 5 -> {
                    ACLMessage msg = myAgent.blockingReceive();
                    if (msg.getConversationId().equals(CONVERTAION_IDS.Canal_Agente_Santa.name()) && msg.getPerformative() == ACLMessage.INFORM && msg.getSender().getLocalName().equals("Agente")) {
                        System.out.println(msg);
                        ACLMessage reply = msg.createReply(ACLMessage.INFORM);
                        reply.setContent("HoHoHo!");
                        myAgent.send(reply);
                        finish = true;
                    } else {
                        System.out.println("Error en el protocolo de comunicación - paso 5");
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
