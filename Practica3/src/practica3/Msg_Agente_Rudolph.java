package practica3;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class Msg_Agente_Rudolph extends Behaviour {
    int step = 0;
    boolean finish = false;
    ACLMessage msgAnterior = null;

    @Override
    public void action() {
        if (((Agente) myAgent).conversandoConRudolph){
            
            switch (step){
                // Enviar mensaje a Rudolph para ofrecerse voluntario
                case 0 -> {
                    ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
                    msg.addReceiver(new AID("Rudolph", AID.ISLOCALNAME));
                    msg.setConversationId(((Agente) myAgent).codigoCanalRudolph);
                    msg.setContent(((Agente) myAgent).transformarAMensajeGenZ("Rudolph, ¿me permitirías ayudarte a rescatar los renos?"));
                    myAgent.send(msg);

                    step = 1;
                }
                // Recibir respuesta de Rudolph
                case 1 -> {
                    ACLMessage msg = myAgent.blockingReceive();
                    System.out.println(msg);
                    if (msg.getConversationId().equals(((Agente) myAgent).codigoCanalRudolph) && msg.getPerformative() == ACLMessage.AGREE){
                        System.out.println("Rudolph aceptó la propuesta");
                        //((Agente) myAgent).buscandoRenos = true;
                        msgAnterior = msg;
                        step = 2;                      

                    } else if (msg.getConversationId().equals(((Agente) myAgent).codigoCanalRudolph) && msg.getPerformative() == ACLMessage.REFUSE){
                        System.out.println("Rudolph rechazó la propuesta");
                        myAgent.doDelete();

                    } else {
                        System.out.println("Error en el protocolo de comunicación - paso 1");
                        myAgent.doDelete();
                    }
                }
                // Pedir a Rudolph que nos guíe a los renos hasta que nos diga que ya los tenemos todos
                case 2 -> {
                    ACLMessage msg = msgAnterior.createReply(ACLMessage.REQUEST);
                    msg.setContent(((Agente) myAgent).transformarAMensajeGenZ("Rudolph, ¿podrías darme las coordenadas de un reno?"));
                    myAgent.send(msg);

                    step = 3;
                }
                // Recibir respuesta de Rudolph, y movernos hacia la posición del reno
                case 3 -> {
                    ACLMessage msg = myAgent.blockingReceive();
                    System.out.println(msg);
                    if (msg.getConversationId().equals(((Agente) myAgent).codigoCanalRudolph) && msg.getPerformative() == ACLMessage.INFORM){
                        //si Rudolph nos da las coordenadas de un reno, nos movemos hacia él y vamos al paso 2, en otro caso vamos al paso 4
                        if(msg.getContent().contains("Reno")){
                            System.out.println("Rudolph ha proporcionado las coordenadas de un reno");
                            int filaReno = Integer.parseInt(msg.getContent().split(":")[1].split(",")[0]);
                            int columnaReno = Integer.parseInt(msg.getContent().split(":")[1].split(",")[1]);
                            ((Agente) myAgent).entorno.filaMeta = ((Agente) myAgent).filaMeta =  filaReno;
                            ((Agente) myAgent).entorno.columnaMeta = ((Agente) myAgent).columnaMeta = columnaReno;
                            ((Agente) myAgent).mapaPanel.actualizarDestinoUI(filaReno, columnaReno, 0);
                            ((Agente) myAgent).buscandoRenos = true;
                            msgAnterior = msg;
                            step = 2;
                            ((Agente) myAgent).conversandoConRudolph = false;
                        } else {
                            step = 4;
                            ((Agente) myAgent).buscandoRenos = false;
                            ((Agente) myAgent).conversandoConRudolph = false;
                            ((Agente) myAgent).conversandoConElfo = true;
                        }

                    } else {
                        System.out.println("Error en el protocolo de comunicación - paso 3");
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
