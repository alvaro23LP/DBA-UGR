package practica3;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class Msg_Agente_Elfo extends Behaviour {
    int step = 0;
    boolean finish = false;

    @Override
    public void action() {
        if (((Agente) myAgent).conversandoConElfo){
            //System.out.println("Conversando con elfo++*-*/+/+*/*-+---- step: " + step);
            
            switch (step){
                // Enviar mensaje al elfo para que lo traduzca (ofrecerse para la misión)
                case 0 -> {
                    ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
                    msg.addReceiver(new AID("Elfo", AID.ISLOCALNAME));
                    msg.setConversationId(CONVERTAION_IDS.Canal_Agente_Elfo.name());
                    ((Agente) myAgent).mensajeParaTraducirParaSanta = ((Agente) myAgent).transformarAMensajeGenZ("Santa quiero ofrecerme voluntario para la misión");
                    msg.setContent(((Agente) myAgent).mensajeParaTraducirParaSanta+"$1");
                    myAgent.send(msg);
                    //System.out.println(msg);
                    
                    step = 1;
                }
                // Recibir mensaje del elfo traducido y almacenarlo. Cambiar de conversación a Santa
                case 1 -> {
                    ACLMessage msg = myAgent.blockingReceive();
                    System.out.println(msg);
                    if (msg.getConversationId().equals(CONVERTAION_IDS.Canal_Agente_Elfo.name()) && msg.getPerformative() == ACLMessage.INFORM){
                        
                        ((Agente) myAgent).mensajeTraducidoParaSanta = msg.getContent();
                        
                        step = 2;
                        
                        ((Agente) myAgent).conversandoConElfo = false;
                        ((Agente) myAgent).conversandoConSanta = true;

                    } else {
                        System.out.println("Error en el protocolo de comunicación - paso 1");
                        myAgent.doDelete();
                    }
                }
                // Enviar mensaje al elfo para que lo traduzca (informar de que se ha encontrado reno)
                case 2 -> {
                    if (((Agente) myAgent).buscandoRenos){
                        ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
                        msg.addReceiver(new AID("Elfo", AID.ISLOCALNAME));
                        msg.setConversationId(CONVERTAION_IDS.Canal_Agente_Elfo.name());
                        ((Agente) myAgent).mensajeParaTraducirParaSanta = ((Agente) myAgent).transformarAMensajeGenZ("Santa, he encontrado un reno! ");
                        msg.setContent(((Agente) myAgent).mensajeParaTraducirParaSanta+"$0");
                        myAgent.send(msg);
                        
                        step = 3;
                    } else {
                        step = 4;
                    }
                }
                // Recibir mensaje del elfo traducido y almacenarlo. Cambiar de conversación a Santa
                case 3 -> {
                    ACLMessage msg = myAgent.blockingReceive();
                    System.out.println(msg);
                    if (msg.getConversationId().equals(CONVERTAION_IDS.Canal_Agente_Elfo.name()) && msg.getPerformative() == ACLMessage.INFORM){
                        
                        ((Agente) myAgent).mensajeTraducidoParaSanta = msg.getContent();
                        
                        step = 2;
                        
                        ((Agente) myAgent).conversandoConElfo = false;
                        ((Agente) myAgent).conversandoConSanta = true;

                    } else {
                        System.out.println("Error en el protocolo de comunicación - paso 3");
                        myAgent.doDelete();
                    }
                }
                // Enviar mensaje al elfo para que lo traduzca (pedir la ubicación de Santa)
                case 4 -> {
                    ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
                    msg.addReceiver(new AID("Elfo", AID.ISLOCALNAME));
                    msg.setConversationId(CONVERTAION_IDS.Canal_Agente_Elfo.name());
                    ((Agente) myAgent).mensajeParaTraducirParaSanta = ((Agente) myAgent).transformarAMensajeGenZ("Santa he encontrado todos los renos, ¿Podrías decirme tu localización?");
                    msg.setContent(((Agente) myAgent).mensajeParaTraducirParaSanta+"$1");
                    myAgent.send(msg);
                    //System.out.println(msg);
                    
                    step = 5;
                }
                // Recibir mensaje del elfo traducido y almacenarlo. Cambiar de conversación a Santa
                case 5 -> {
                    ACLMessage msg = myAgent.blockingReceive();
                    System.out.println(msg);
                    if (msg.getConversationId().equals(CONVERTAION_IDS.Canal_Agente_Elfo.name()) && msg.getPerformative() == ACLMessage.INFORM){
                        
                        ((Agente) myAgent).mensajeTraducidoParaSanta = msg.getContent();                        
                        step = 6;                         
                        ((Agente) myAgent).conversandoConElfo = false;
                        ((Agente) myAgent).conversandoConSanta = true;

                    } else {
                        System.out.println("Error en el protocolo de comunicación - paso 5");
                        myAgent.doDelete();
                    }
                }
                // Enviar mensaje al elfo para que lo traduzca (informar a Santa que hemos llegado)
                case 6 -> {
                    ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
                    msg.addReceiver(new AID("Elfo", AID.ISLOCALNAME));
                    msg.setConversationId(CONVERTAION_IDS.Canal_Agente_Elfo.name());
                    ((Agente) myAgent).mensajeParaTraducirParaSanta = ((Agente) myAgent).transformarAMensajeGenZ("Santa estoy aquí, tengo a todos los renos!");
                    msg.setContent(((Agente) myAgent).mensajeParaTraducirParaSanta+"$1");
                    myAgent.send(msg);
                    //System.out.println(msg);
                    
                    step = 7;
                }
                // Recibir mensaje del elfo traducido y almacenarlo. Cambiar de conversación a Santa
                case 7 -> {
                    ACLMessage msg = myAgent.blockingReceive();
                    System.out.println(msg);
                    if (msg.getConversationId().equals(CONVERTAION_IDS.Canal_Agente_Elfo.name()) && msg.getPerformative() == ACLMessage.INFORM){
                        
                        ((Agente) myAgent).mensajeTraducidoParaSanta = msg.getContent();                        
                        step = 8;
                        finish = true;                  
                        ((Agente) myAgent).conversandoConElfo = false;
                        ((Agente) myAgent).conversandoConSanta = true;

                    } else {
                        System.out.println("Error en el protocolo de comunicación - paso 7");
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
