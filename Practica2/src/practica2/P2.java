
package practica2;
import jade.core.Agent;
import static java.lang.System.exit;
import jade.core.Agent;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;
import java.util.ArrayList;

/**
 *
 * @author alvaro2311
 */
public class P2 {
    public static void main(String[] args){
        
        Mapa mapa;
        Entorno entorno;
        
        //////////////// ENTORNO /////////////////
        mapa = new Mapa();
        //String ruta = "./mapas/mapWithDiagonalWall.txt";
        //String ruta = "./mapas/mapWithComplexObstacle1.txt";
        String ruta = "./mapas/mapWithComplexObstacle2.txt";

        mapa.leermapa(ruta);
        
        //entorno = new Entorno(mapa, 3, 9, 9, 9);
        entorno = new Entorno(mapa, 0, 6, 9, 6);
        //entorno = new Entorno(mapa, 0, 6, 9, 6);
        entorno.mostrarEnTorno();


        //////////////// AGENTE ///////////////
        String host = "localhost";
        String containerName = "container-P2";
        String agentName = "alvarolv";
        
        try {
        jade.core.Runtime rt = jade.core.Runtime.instance();
        
        //Creamos un contenedor de agentes
        Profile p = new ProfileImpl();
        
        p.setParameter(Profile.MAIN_HOST, host);
        p.setParameter(Profile.CONTAINER_NAME, containerName);
        
        ContainerController cc = rt.createAgentContainer(p);
        
        // Creamos un nuevo agente y le pasamos el entorno como argumento
        Object[] agentArgs = new Object[] { entorno };
        AgentController ac = cc.createNewAgent(agentName, Agente.class.getCanonicalName(), agentArgs);
        
        ac.start();        
        
        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
    }
}
   
