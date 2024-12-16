package practica3;

import jade.core.Agent;

public class Rudolph extends Agent {
    public int[][] coordenadasRenos = new int[8][2];
    public String codigoCanal;

    @Override
    protected void setup() {
        for (int i = 0; i< 8; i++){
            coordenadasRenos[i][0] = P3.coordenadasRenos2[i][0];
            coordenadasRenos[i][1] = P3.coordenadasRenos2[i][1];
        }

        codigoCanal = "CanalSecreto-53246";

        addBehaviour(new Msg_Rudolph_Agente());
    }

    @Override
    public void takeDown() {
        System.out.println("Agente Rudolph finalizado");
        System.out.println();
    }
}
