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
                // Recibir respuesta de Santa
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
            }
        }
    }

    @Override
    public boolean done() {
        return finish;
    }
}
