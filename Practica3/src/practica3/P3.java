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

    public static int[][] coordenadasRenos2 = new int[8][2];
    public static int[][] coordenadasAgente = new int[1][2];
    public static int[][] coordenadasSanta = new int[1][2];

    public static void main(String[] args) {

        // Llamar a la ventana para ingresar coordenadas antes de iniciar el programa
        mostrarVentanaCoordenadas();

        Mapa mapa;
        Entorno entorno;

        //////////////// ENTORNO /////////////////
        mapa = new Mapa();
        String ruta = "./mapas/100x100-sinObstaculos.txt";
        entorno = new Entorno(mapa, coordenadasAgente[0][1], coordenadasAgente[0][0], -1, -1); // al principio las coordenadas de destino no se conocen

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
    JDialog dialog = new JDialog((Frame) null, "Ingresar Coordenadas", true);
    dialog.setLayout(new GridLayout(11, 1));

    // Panel para ingresar coordenadas
    JPanel[] paneles = new JPanel[10];
    JTextField[][] campos = new JTextField[10][2];

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

        //Coordenadas agente
        paneles[8] = new JPanel(new FlowLayout());
        JLabel label = new JLabel("Agente:");
        JTextField xField = new JTextField(5);
        JTextField yField = new JTextField(5);
        campos[8][0] = xField;
        campos[8][1] = yField;

        paneles[8].add(label);
        paneles[8].add(new JLabel("X: "));
        paneles[8].add(xField);
        paneles[8].add(new JLabel("Y: "));
        paneles[8].add(yField);

        dialog.add(paneles[8]);

        //Coordnadas Santa
        paneles[9] = new JPanel(new FlowLayout());
        JLabel label2 = new JLabel("Santa:");
        JTextField xField2 = new JTextField(5);
        JTextField yField2 = new JTextField(5);
        campos[9][0] = xField2;
        campos[9][1] = yField2;

        paneles[9].add(label2);
        paneles[9].add(new JLabel("X: "));
        paneles[9].add(xField2);
        paneles[9].add(new JLabel("Y: "));
        paneles[9].add(yField2);

        dialog.add(paneles[9]);



    // Botón para guardar coordenadas
    JButton guardarButton = new JButton("Guardar Coordenadas");
    guardarButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                for (int i = 0; i < 8; i++) {
                    int x = Integer.parseInt(campos[i][0].getText());
                    int y = Integer.parseInt(campos[i][1].getText());

                    coordenadasRenos2[i][0] = y;
                    coordenadasRenos2[i][1] = x;
                }

                int x = Integer.parseInt(campos[8][0].getText());
                int y = Integer.parseInt(campos[8][1].getText());
                coordenadasAgente[0][0] = x;
                coordenadasAgente[0][1] = y;

                x = Integer.parseInt(campos[9][0].getText());
                y = Integer.parseInt(campos[9][1].getText());
                coordenadasSanta[0][0] = y;
                coordenadasSanta[0][1] = x;

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
