package practica3;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class Msg_Agente_Rudolph extends Behaviour {
    int step = 0;
    boolean finish = false;

    @Override
    public void action() {
        
    }

    @Override
    public boolean done() {
        return finish;
    }
}
