
package IA;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Timer;

public class Lienzo extends Canvas implements Constantes{
    public Mapa mapa;
    public Autos[] autos; 
    public Cartero cartero;
    public Timer lanzadorTareas;
    public Lienzo(){
      this.mapa=new Mapa(this);  
      cartero= new Cartero(mapa);
      autos= new Autos[5];
      Point pmin=new Point(1,1);
      Point pmax= new Point(6,18);
      autos[0]= new Autos(mapa,pmin,pmax);
      pmin.x=7;
      pmin.y=1;
      pmax.x=18;
      pmax.y=18;
      autos[1]= new Autos(mapa,pmin,pmax);
      pmin.x=19;
      pmin.y=1;
      pmax.x=24;
      pmax.y=18-6;
      autos[2]= new Autos(mapa,pmin,pmax);
  

      
       

     
          addKeyListener(new KeyAdapter(){
          public void keyPressed(KeyEvent e){
            cartero.moverCelda(e);
            repaint();
          }
      });
      
      lanzadorTareas= new Timer();

      lanzadorTareas.scheduleAtFixedRate(autos[0],0,300);
      lanzadorTareas.scheduleAtFixedRate(autos[1],0,300);
      lanzadorTareas.scheduleAtFixedRate(autos[2],0,500);

    }
    public void update(Graphics g) {
        mapa.update(g);
    }
    @Override
    public void paint(Graphics g) {
        update(g);
    }
}
