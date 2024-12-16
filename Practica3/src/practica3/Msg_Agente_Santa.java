package practica3;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class Msg_Agente_Santa extends Behaviour {
    int step = 0;
    boolean finish = false;
    ACLMessage msgAnterior = null;

    @Override
    public void action() {
        if (((Agente) myAgent).conversandoConSanta){
            
            switch (step){
                // Enviar mensaje a Santa para ofrecerse voluntario
                case 0 -> {
                    ACLMessage msg = new ACLMessage(ACLMessage.PROPOSE);
                    msg.addReceiver(new AID("Santa", AID.ISLOCALNAME));
                    msg.setConversationId(CONVERTAION_IDS.Canal_Agente_Santa.name());
                    msg.setContent(((Agente) myAgent).mensajeTraducidoParaSanta);
                    myAgent.send(msg);

                    step = 1;
                }
                // Recibir respuesta de Santa con el codigo del canal en caso de que acepte la propuesta
                case 1 -> {
                    ACLMessage msg = myAgent.blockingReceive();
                    System.out.println(msg);
                    if (msg.getConversationId().equals(CONVERTAION_IDS.Canal_Agente_Santa.name()) && msg.getPerformative() == ACLMessage.ACCEPT_PROPOSAL){
                        System.out.println("Santa aceptó la propuesta");
                        msgAnterior = msg;
                        // Dividir el mensaje recibido en dos. EL separador es : y nos quedaremos con la segunda parte
                        ((Agente) myAgent).codigoCanalRudolph = msg.getContent().split(":")[1];

                        step = 2;

                        ((Agente) myAgent).conversandoConSanta = false;
                        ((Agente) myAgent).conversandoConRudolph = true;

                    } else if (msg.getConversationId().equals(CONVERTAION_IDS.Canal_Agente_Santa.name()) && msg.getPerformative() == ACLMessage.REJECT_PROPOSAL){
                        System.out.println("Santa rechazó la propuesta");
                        myAgent.doDelete();

                    } else {
                        System.out.println("Error en el protocolo de comunicación - paso 1");
                        myAgent.doDelete();
                    }
                }
                // Envio de mensaje traducido a Santa informando de que se ha encontrado un reno
                case 2 -> {
                    if (((Agente) myAgent).buscandoRenos){
                        ACLMessage msg = msgAnterior.createReply(ACLMessage.INFORM);
                        msg.setContent(((Agente) myAgent).mensajeTraducidoParaSanta);
                        myAgent.send(msg);

                        step = 2;
                        ((Agente) myAgent).conversandoConSanta = false;
                        ((Agente) myAgent).conversandoConRudolph = true;
                    } else {
                        step = 3;
                    }
                }
                // Envio de mensaje traducido a Santa preguntado si le puede dar su localización
                case 3 -> {                    
                    ACLMessage msg = msgAnterior.createReply(ACLMessage.REQUEST);
                    msg.setContent(((Agente) myAgent).mensajeTraducidoParaSanta);
                    myAgent.send(msg);

                    step = 4;                    
                }
                // Recepción de coordenadas de Santa y movimiento hacia su posición
                case 4 -> {
                    ACLMessage msg = myAgent.blockingReceive();
                    System.out.println(msg);
                    if (msg.getConversationId().equals(CONVERTAION_IDS.Canal_Agente_Santa.name()) && msg.getPerformative() == ACLMessage.INFORM){
                        System.out.println("Santa ha enviado su localización");
                        msgAnterior = msg;
                        ((Agente) myAgent).entorno.filaMeta = ((Agente) myAgent).filaMeta = Integer.parseInt(msg.getContent().split(":")[1].split(",")[0]);
                        ((Agente) myAgent).entorno.columnaMeta = ((Agente) myAgent).columnaMeta = Integer.parseInt(msg.getContent().split(":")[1].split(",")[1]);
                        ((Agente) myAgent).mapaPanel.actualizarDestinoUI(((Agente) myAgent).filaMeta, ((Agente) myAgent).columnaMeta, 0);
                        step = 5;
                        ((Agente) myAgent).conversandoConElfo= false;
                        ((Agente) myAgent).conversandoConRudolph = true;
                        
                    } else {
                        System.out.println("Error en el protocolo de comunicación - paso 4");
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
