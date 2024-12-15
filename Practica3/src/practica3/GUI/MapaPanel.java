package practica3.GUI;

import practica3.Mapa;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Random;


public class MapaPanel extends JPanel {
    private Mapa mapa;
    private int filaAngente, columnaAgente;
    private int filaMeta, columnaMeta;
    private BufferedImage pacmanImagen, cerezaImagen;
    private BufferedImage[] fantasmas = new BufferedImage[3];
    private Random random = new Random();
    private ArrayList<Point> caminoRecorrido = new ArrayList<>();
    private Point puntoInicial;

    public MapaPanel(Mapa mapa, int filaAgente, int columnaAgente, int filaMeta, int columnaMeta) {
        this.mapa = mapa;
        this.filaAngente = filaAgente;
        this.columnaAgente = columnaAgente;
        this.filaMeta = filaMeta;
        this.columnaMeta = columnaMeta;
        this.puntoInicial = new Point(columnaAgente, filaAgente);

        // Establecer el color de fondo donde los fantasmas 
        setBackground(new Color(0, 0, 139)); 

        //Cargar las imágenes
        try {
            pacmanImagen = ImageIO.read(new File("src/practica3/GUI/images/pacman2.png"));
            cerezaImagen = ImageIO.read(new File("src/practica3/GUI/images/reno.png"));
            fantasmas[0] = ImageIO.read(new File("src/practica3/GUI/images/fantasma_rojo.png"));
            fantasmas[1] = ImageIO.read(new File("src/practica3/GUI/images/fantasma_azul.png"));
            fantasmas[2] = ImageIO.read(new File("src/practica3/GUI/images/fantasma_rosa.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void actualizarPosicionAgente(int nuevaFilAgente, int nuevaColAgente) {
        // Añadir la posición actual del agente a la lista de camino recorrido
        caminoRecorrido.add(new Point(columnaAgente, filaAngente));
        // Actualizar la posición del agente
        this.filaAngente = nuevaFilAgente;
        this.columnaAgente = nuevaColAgente;
        repaint();
    }

    //tipo 0: la meta es un reno, tipo 1: la meta es Santa
    public void actualizarDestinoUI(int filaMeta, int columnaMeta, int tipo) {
        this.filaMeta = filaMeta;
        this.columnaMeta = columnaMeta;
        if (tipo == 1) {
            try{
                cerezaImagen = ImageIO.read(new File("src/practica3/GUI/images/santa.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int panelWidth = getWidth();
        int panelHeight = getHeight();
        int numCols = mapa.mapa[0].length;
        int numRows = mapa.mapa.length;
        int cellWidth = panelWidth / numCols;
        int cellHeight = panelHeight / numRows;

        for (int y = 0; y < numRows; y++) {
            for (int x = 0; x < numCols; x++) {
                if (mapa.mapa[y][x] == -1) {
                    // Dibujar un fantasma aleatorio
                    BufferedImage ghostImage = fantasmas[random.nextInt(fantasmas.length)];
                    g.drawImage(ghostImage, x * cellWidth, y * cellHeight, cellWidth, cellHeight, this);
                } else {
                    g.setColor(Color.BLUE);
                    g.drawRect(x * cellWidth, y * cellHeight, cellWidth, cellHeight);
                    g.setColor(Color.BLACK);
                    g.fillRect(x * cellWidth, y * cellHeight, cellWidth, cellHeight);
                }
                g.setColor(new Color(0, 0, 139));
                g.drawRect(x * cellWidth, y * cellHeight, cellWidth, cellHeight);
            }
        }

        // Dibuja el camino recorrido con puntos amarillos
        g.setColor(Color.YELLOW);
        int pointSize = Math.min(cellWidth, cellHeight) / 2; 
        for (Point p : caminoRecorrido) {
            int x = p.x * cellWidth + (cellWidth - pointSize) / 2;
            int y = p.y * cellHeight + (cellHeight - pointSize) / 2;
            g.fillOval(x, y, pointSize, pointSize);
        }

        // Dibuja la posición inicial con un punto verde
        g.setColor(Color.GREEN);
        g.fillOval(puntoInicial.x * cellWidth, puntoInicial.y * cellHeight, cellWidth, cellHeight);

        // Dibuja un círculo blanco en la casilla de la meta
        g.setColor(Color.WHITE);
        g.fillOval(columnaMeta * cellWidth, filaMeta * cellHeight, cellWidth, cellHeight);

        // Dibuja la cereza (meta)
        if (cerezaImagen != null) {
            g.drawImage(cerezaImagen, columnaMeta * cellWidth, filaMeta * cellHeight, cellWidth, cellHeight, this);
        } else {
            g.setColor(Color.RED);
            g.fillOval(columnaMeta * cellWidth, filaMeta * cellHeight, cellWidth, cellHeight);
        }

        // Dibuja Pacman (agente)
        if (pacmanImagen != null) {
            g.drawImage(pacmanImagen, columnaAgente * cellWidth, filaAngente * cellHeight, cellWidth, cellHeight, this);
        } else {
            g.setColor(Color.BLUE);
            g.fillOval(columnaAgente * cellWidth, filaAngente * cellHeight, cellWidth, cellHeight);
        }
    }
}