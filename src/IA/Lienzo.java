package IA;

import Inteligencia.Estado;
import static IA.Constantes.NUM_CELDAS_HEIGHT;
import static IA.Constantes.NUM_CELDAS_WIDTH;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.util.Timer;

public class Lienzo extends Canvas implements Constantes {

    public Mapa mapa;
    public Image fondo;
    public Autos[] autos;
    public Cartero cartero;
    public Ladron ladron;
    public Peaton peaton;
    public Timer lanzadorTareas;
    public Micro micro;
    public Cuadra[][] cuadras;
    public ArrayList<Portal> portales;
    public Graphics graficoBuffer;
    public Image imagenBuffer;

    public Lienzo() {
        this.mapa = new Mapa(this);

        portales = new ArrayList<>();
        crearPortales();
        cartero = new Cartero(mapa, portales);
        //ladron = new Ladron(mapa, cartero);
        autos = new Autos[5];

        Point peat = new Point(8, 4);
        cuadras = new Cuadra[3][6];

        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 6; i++) {
                int random = (int) Math.floor(Math.random() * (101));
                if (i == 0) {
                    cuadras[j][i] = new Cuadra(1, 6 * j + 1, random, this.mapa);
                    cuadras[j][i].establecerCuadra(random);
                } else {
                    cuadras[j][i] = new Cuadra(6 * i + 1, 6 * j + 1, random, this.mapa);
                    cuadras[j][i].establecerCuadra(random);
                }

            }

        }

        mapa.crearCarreteras();
        Point pmin = new Point(1, 1);
        Point pmax = new Point(6, 18);
        autos[0] = new Autos(mapa, pmin, pmax);
        pmin.x = 7;
        pmin.y = 1;
        pmax.x = 18;
        pmax.y = 18;
        autos[1] = new Autos(mapa, pmin, pmax);
        pmin.x = 19;
        pmin.y = 1;
        pmax.x = 24;
        pmax.y = 18 - 6;
        autos[2] = new Autos(mapa, pmin, pmax);
        pmin.x = 7;
        pmin.y = NUM_CELDAS_HEIGHT - 7;
        pmax.x = 18;
        pmax.y = NUM_CELDAS_HEIGHT - 2;

        autos[3] = new Autos(mapa, pmin, pmax);
        pmin.x = 25;
        pmin.y = 7;
        pmax.x = NUM_CELDAS_WIDTH - 4;
        pmax.y = NUM_CELDAS_HEIGHT - 8;
        micro = new Micro(mapa, pmin, pmax);
        pmin.x--;

        try {
            fondo = ImageIO.read(new File("imagenes/fondo.png"));
        } catch (IOException e) {
            System.out.println(e.toString());
        }

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                cartero.moverCelda(e);
                repaint();
            }
        });
        for (int i = 0; i < portales.size(); i++) {
            cartero.inteligencia.destinos.add(new Estado(portales.get(i).portal.x, portales.get(i).portal.y, 'N', null));
        }

        cartero.inteligencia.buscar(cartero.celdaMovimiento.x, cartero.celdaMovimiento.y, cartero.inteligencia.destinos.get(cartero.inteligencia.nDestinos - 1));
        cartero.inteligencia.calcularRuta();
        cartero.inteligencia.nDestinos--;
        //ladron.inteligencia.destinos.add(new Estado(cartero.celdaMovimiento.x, cartero.celdaMovimiento.y, 'N', null));
        lanzadorTareas = new Timer();
        lanzadorTareas.scheduleAtFixedRate(cartero.inteligencia, 0, 500);
        //lanzadorTareas.scheduleAtFixedRate(ladron.inteligencia, 0, 450);
        lanzadorTareas.scheduleAtFixedRate(micro, 0, 700);

        lanzadorTareas.scheduleAtFixedRate(autos[0], 0, 300);
        lanzadorTareas.scheduleAtFixedRate(autos[1], 0, 300);
        lanzadorTareas.scheduleAtFixedRate(autos[2], 0, 500);
        lanzadorTareas.scheduleAtFixedRate(autos[3], 0, 300);
        
    }

    public void imprimirMapa() {
        for (int i = 0; i < NUM_CELDAS_WIDTH; i++) {
            for (int j = 0; j < NUM_CELDAS_HEIGHT; j++) {
                System.out.print(mapa.celdas[i][j].n + " ");
            }
            System.out.println("");
        }

    }

    @Override
    public void update(Graphics g) {
        if (graficoBuffer == null) {
            imagenBuffer = createImage(this.getWidth(), this.getHeight());
            graficoBuffer = imagenBuffer.getGraphics();
        }
        //volcamos color de fondo e imagen en el nuevo buffer grafico
        graficoBuffer.setColor(getBackground());
        graficoBuffer.fillRect(0, 0, this.getWidth(), this.getHeight());
        graficoBuffer.drawImage(fondo, 0, 0, null);
        mapa.update(graficoBuffer);
        //pintamos la imagen previa
        g.drawImage(imagenBuffer, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        update(g);
    }

    public void crearPortales() {
        int vector[] = new int[2];
        vector[0] = 1;
        vector[1] = 3;

        portales.add(new Portal(mapa, 9, 16, vector.length, vector)); //mapa, celda en x, celda en y, cantidadCartas, numCartas
        int vector1[] = new int[2];
        vector1[0] = 5;
        vector1[1] = 6;

        portales.add(new Portal(mapa, 9, 10, vector1.length, vector1));
        int vector2[] = new int[2];
        vector2[0] = 2;
        vector2[1] = 4;

        portales.add(new Portal(mapa, 21, 10, vector2.length, vector2));

    }
}
