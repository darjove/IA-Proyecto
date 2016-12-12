
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
    public Micro micro;
    
    public Lienzo(){
      this.mapa=new Mapa(this);  
      cartero= new Cartero(mapa);
      autos= new Autos[5];
      Point aux= new Point(8,9);      
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
      pmin.x=7;
      pmin.y=NUM_CELDAS_HEIGHT-8;
      pmax.x=18;
      pmax.y=NUM_CELDAS_HEIGHT-3;
  
      autos[3]= new Autos(mapa,pmin,pmax);
      pmin.x=25;
      pmin.y=7;
      pmax.x=NUM_CELDAS_WIDTH-4;
      pmax.y=NUM_CELDAS_HEIGHT-9; 
      micro= new Micro(mapa,pmin,pmax);
      pmin.x--;


     
          addKeyListener(new KeyAdapter(){
          public void keyPressed(KeyEvent e){
            cartero.moverCelda(e);
            repaint();
          }
      });
      
      cartero.inteligencia.destinos.add(new Estado(10,8,'N',null));
      cartero.inteligencia.destinos.add(new Estado(10,2,'N',null));
      cartero.inteligencia.destinos.add(new Estado(20,11,'N',null));
      
      
      lanzadorTareas= new Timer();
      lanzadorTareas.scheduleAtFixedRate(cartero.inteligencia, 0,600);
      lanzadorTareas.scheduleAtFixedRate(micro,0,700);

      lanzadorTareas.scheduleAtFixedRate(autos[0],0,300);
      lanzadorTareas.scheduleAtFixedRate(autos[1],0,300);
      lanzadorTareas.scheduleAtFixedRate(autos[2],0,500);
      lanzadorTareas.scheduleAtFixedRate(autos[3],0,300);
    }
    public void update(Graphics g) {
        mapa.update(g);
    }
    @Override
    public void paint(Graphics g) {
        update(g);
    }
}
