package practica3;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import practica3.GUI.MapaPanel;

public class P3 {

    public static int[][] coordenadasRenos2 = new int[8][2]; // Declaración de la matriz

    public static void main(String[] args) {

        // Llamar a la ventana para ingresar coordenadas antes de iniciar el programa
        mostrarVentanaCoordenadas();

        Mapa mapa;
        Entorno entorno;

        //////////////// ENTORNO /////////////////
        mapa = new Mapa();
        String ruta = "./mapas/100x100-sinObstaculos.txt";
        entorno = new Entorno(mapa, 0, 0, -1, -1); // al principio las coordenadas de destino no se conocen

        mapa.readMap(ruta);

        ////////// INTERFAZ GRAFICA ////////////
        JFrame frame = new JFrame("Practica 3 - Comunicación de agentes");
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
        String containerName = "container-P3";

        String agentName = "Agente";
        String santaName = "Santa";
        String rudolphName = "Rudolph";
        String elfName = "Elfo";

        try {
            jade.core.Runtime rt = jade.core.Runtime.instance();

            Profile p = new ProfileImpl();

            p.setParameter(Profile.MAIN_HOST, host);
            p.setParameter(Profile.CONTAINER_NAME, containerName);

            ContainerController cc = rt.createAgentContainer(p);

            // Creamos un nuevo agente y le pasamos el entorno e interfaz como argumento
            Object[] agentArgs = new Object[]{entorno, mapaPanel};

            AgentController ac0 = cc.createNewAgent(agentName, Agente.class.getCanonicalName(), agentArgs);
            AgentController ac1 = cc.createNewAgent(santaName, Santa.class.getCanonicalName(), null);
            AgentController ac2 = cc.createNewAgent(rudolphName, Rudolph.class.getCanonicalName(), null);
            AgentController ac3 = cc.createNewAgent(elfName, Elfo.class.getCanonicalName(), null);


            ac0.start();
            ac1.start();
            ac2.start();
            ac3.start();

        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
    }

    /**
     * Muestra una ventana para ingresar las coordenadas de los renos.
     */
    /**
 * Muestra una ventana modal para ingresar las coordenadas de los renos.
 */
private static void mostrarVentanaCoordenadas() {
    JDialog dialog = new JDialog((Frame) null, "Ingresar Coordenadas de Renos", true);
    dialog.setLayout(new GridLayout(10, 1));

    // Panel para ingresar coordenadas
    JPanel[] paneles = new JPanel[8];
    JTextField[][] campos = new JTextField[8][2];

    for (int i = 0; i < 8; i++) {
        paneles[i] = new JPanel(new FlowLayout());
        JLabel label = new JLabel("Reno " + (i + 1) + ":");
        JTextField xField = new JTextField(5);
        JTextField yField = new JTextField(5);
        campos[i][0] = xField;
        campos[i][1] = yField;

        paneles[i].add(label);
        paneles[i].add(new JLabel("X: "));
        paneles[i].add(xField);
        paneles[i].add(new JLabel("Y: "));
        paneles[i].add(yField);

        dialog.add(paneles[i]);
    }

    // Botón para guardar coordenadas
    JButton guardarButton = new JButton("Guardar Coordenadas");
    guardarButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                for (int i = 0; i < 8; i++) {
                    int x = Integer.parseInt(campos[i][0].getText());
                    int y = Integer.parseInt(campos[i][1].getText());

                    coordenadasRenos2[i][0] = x;
                    coordenadasRenos2[i][1] = y;
                }

                JOptionPane.showMessageDialog(dialog, "Coordenadas guardadas correctamente.");
                dialog.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Error: Por favor, ingrese solo números.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    });

    dialog.add(guardarButton);
    dialog.pack();
    dialog.setLocationRelativeTo(null); // Centrar la ventana
    dialog.setVisible(true);
}

}
