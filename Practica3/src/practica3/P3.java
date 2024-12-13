
package practica3;
import jade.core.Profile;
import jade.core.ProfileImpl;
import java.awt.Toolkit;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

import javax.swing.JFrame;
import java.awt.Dimension;

import practica3.GUI.MapaPanel;


public class P3 {
    public static void main(String[] args){

        Mapa mapa;
        Entorno entorno;

        //////////////// ENTORNO /////////////////
        mapa = new Mapa();
        String ruta = "./mapas/100x100-conObstaculos.txt";
        entorno = new Entorno(mapa,0,0,50,50);

        mapa.readMap(ruta);

        //entorno = new Entorno(mapa,9,9,0,0);
        entorno.imprimirMapaConsola();

        ////////// INTERFAZ GRAFICA ////////////
        JFrame frame = new JFrame("Practica 2 - Movimiento de un agente en un mundo bidimensional");
        MapaPanel mapaPanel = new MapaPanel(mapa, entorno.filaAgente, entorno.columnaAgente, entorno.filaMeta, entorno.columnaMeta);
        frame.add(mapaPanel);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = (int) (screenSize.height * 2 / 3.0);
        double aspectRatio = (double) mapa.mapa[0].length / mapa.mapa.length;
        int width = (int) (height * aspectRatio);

        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


        //////////////// AGENTE ////////////////
        String host = "localhost";
        String containerName = "container-P2";
        String agentName = "alvarolv";

        try {
            jade.core.Runtime rt = jade.core.Runtime.instance();

            Profile p = new ProfileImpl();

            p.setParameter(Profile.MAIN_HOST, host);
            p.setParameter(Profile.CONTAINER_NAME, containerName);

            ContainerController cc = rt.createAgentContainer(p);

            // Creamos un nuevo agente y le pasamos el entorno e interfaz como argumento
            Object[] agentArgs = new Object[] { entorno, mapaPanel };
            AgentController ac = cc.createNewAgent(agentName, Agente.class.getCanonicalName(), agentArgs);
            ac.start();

        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
    }
}
