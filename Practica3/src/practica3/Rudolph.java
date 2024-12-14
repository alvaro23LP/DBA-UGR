package practica3;

import jade.core.Agent;

public class Rudolph extends Agent {
    public int[][] coordenadasRenos = new int[8][2];
    public String codigoCanal;

    @Override
    protected void setup() {
        coordenadasRenos[0][0] = 5;
        coordenadasRenos[0][1] = 2;
        coordenadasRenos[1][0] = 11;
        coordenadasRenos[1][1] = 12;
        coordenadasRenos[2][0] = 8;
        coordenadasRenos[2][1] = 1;
        coordenadasRenos[3][0] = 20;
        coordenadasRenos[3][1] = 5;
        coordenadasRenos[4][0] = 23;
        coordenadasRenos[4][1] = 18;
        coordenadasRenos[5][0] = 27;
        coordenadasRenos[5][1] = 15;
        coordenadasRenos[6][0] = 3;
        coordenadasRenos[6][1] = 26;
        coordenadasRenos[7][0] = 9;
        coordenadasRenos[7][1] = 0;

        codigoCanal = "CanalSecreto-53246";

        addBehaviour(new Msg_Rudolph_Agente());
    }

    @Override
    public void takeDown() {
        System.out.println("Agente Rudolph finalizado");
        System.out.println();
    }
}
