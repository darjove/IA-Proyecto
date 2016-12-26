
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
    public Peaton peaton;
    public Timer lanzadorTareas;
    public Micro micro;
    public Cuadra[][] cuadras;
    public Lienzo(){
      this.mapa=new Mapa(this);  
      cartero= new Cartero(mapa);
      autos= new Autos[5];
      Point peat= new Point(8,4);
      cuadras= new Cuadra[3][6];
      
      
      
      
      
      for(int j=0;j<3;j++){
          for(int i=0; i<6;i++){
              int random=(int)Math.floor(Math.random()*(101));
              if(i==0){
                  cuadras[j][i]= new Cuadra(1,6*j+1,random,this.mapa);
                  cuadras[j][i].establecerCuadra(random);
              }
              
              else{
                  cuadras[j][i]= new Cuadra(6*i+1,6*j+1,random,this.mapa);
                cuadras[j][i].establecerCuadra(random);
              }
                
           }
          
      }
      
      imprimirMapa();
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
      pmin.y=NUM_CELDAS_HEIGHT-7;
      pmax.x=18;
      pmax.y=NUM_CELDAS_HEIGHT-2;
  
      autos[3]= new Autos(mapa,pmin,pmax);
      pmin.x=25;
      pmin.y=7;
      pmax.x=NUM_CELDAS_WIDTH-4;
      pmax.y=NUM_CELDAS_HEIGHT-8; 
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
      cartero.inteligencia.buscar(cartero.celdaMovimiento.x, cartero.celdaMovimiento.y, cartero.inteligencia.destinos.get(cartero.inteligencia.nDestinos-1));
      
      cartero.inteligencia.calcularRuta();  
      cartero.inteligencia.nDestinos--;
      
      lanzadorTareas= new Timer();
      lanzadorTareas.scheduleAtFixedRate(cartero.inteligencia, 0,600);
      lanzadorTareas.scheduleAtFixedRate(micro,0,700);

      lanzadorTareas.scheduleAtFixedRate(autos[0],0,300);
      lanzadorTareas.scheduleAtFixedRate(autos[1],0,300);
      lanzadorTareas.scheduleAtFixedRate(autos[2],0,500);
      lanzadorTareas.scheduleAtFixedRate(autos[3],0,300);
    }

    public void imprimirMapa(){
        for(int i=0; i<NUM_CELDAS_WIDTH;i++){
            for(int j=0; j<NUM_CELDAS_HEIGHT;j++){
                System.out.print(mapa.celdas[i][j].n + " ");
            }
            System.out.println("");
        }
        
    }
    public void update(Graphics g) {
        mapa.update(g);
    }
    @Override
    public void paint(Graphics g) {
        update(g);
    }
}
