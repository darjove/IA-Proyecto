/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IA;

import java.awt.Point;
import java.util.TimerTask;

/**
 *
 * @author dario
 */
public class Micro extends TimerTask implements Constantes{
    public Mapa mapa;
    public Celdas micro;
    public Celdas celdaMovimiento;
    public Point p1,p2,p3,p4;
    public Peaton[] peatones;
    public int npeatones;
 
    Micro(Mapa mapa, Point xp, Point yp){
        p1= new Point(xp.x,xp.y);
        p2= new Point(yp.x,xp.y);
        p3= new Point(yp.x,yp.y);
        p4= new Point(xp.x,yp.y);
        this.mapa=mapa;
        celdaMovimiento= new Celdas(p1.x,p1.y, mapa.celdas[p1.x][p1.y].tipo);
        
        micro=new Celdas(celdaMovimiento.x, celdaMovimiento.y,'M');
        peatones= new Peaton[NUM_PEATONES];
        
        for(int i=0;i<NUM_PEATONES;i++){
            xp.x=xp.x-1;
            peatones[i]= new Peaton(mapa,xp,p1,yp,i+1);
        }
        

        
        
      
        
        mapa.repaint();
    }

    @Override
    public void run() {
        mapa.lienzoPadre.repaint();
        mapa.lienzoPadre.validate();
        moverMicro();
        mapa.lienzoPadre.repaint();
        mapa.lienzoPadre.validate();

        
        for(int i=0;i<NUM_PEATONES;i++){
                peatones[i].moverPeaton();
                
            }
    }

    private void moverMicro() {
        if (celdaMovimiento.x==p2.x && celdaMovimiento.y<p3.y && celdaMovimiento.y>=p2.y){
            moverAbajo();
      
        }
        else if (celdaMovimiento.x>=p1.x && celdaMovimiento.y==p1.y && celdaMovimiento.x<p2.x){
            moverDerecha();
      
        }
        else if (celdaMovimiento.x<=p3.x && celdaMovimiento.y==p3.y && celdaMovimiento.x>p4.x){
            moverIzquierda(); 
            
    
        
        }
        else if (celdaMovimiento.x==p4.x && celdaMovimiento.y<=p4.y && celdaMovimiento.y>=p1.y){
            moverArriba();
  
        }           
        
   
         
  
    
    }

    private void moverAbajo() {
  if (celdaMovimiento.y<NUM_CELDAS_HEIGHT-1 && mapa.celdas[celdaMovimiento.x][celdaMovimiento.y+1].noHayPersona()){
                char t=celdaMovimiento.saberTipo();
                celdaMovimiento.tipo=mapa.celdas[celdaMovimiento.x][celdaMovimiento.y+1].tipo;
                mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].tipo=t;                

                celdaMovimiento.y=celdaMovimiento.y+1;
                celdaMovimiento.sprite=1;
                mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].tipo='M';
                

                

       
        }
    }

    private void moverDerecha() {
        if (mapa.celdas[celdaMovimiento.x+1][celdaMovimiento.y].noHayPersona()){
            char t=celdaMovimiento.saberTipo();
            celdaMovimiento.tipo=mapa.celdas[celdaMovimiento.x+1][celdaMovimiento.y].tipo;
            mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].tipo=t;
            celdaMovimiento.x=celdaMovimiento.x+1;
            celdaMovimiento.sprite=0;
            mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].tipo='M';

   
        }   
    }

    private void moverIzquierda() {
                if (celdaMovimiento.x>0 && mapa.celdas[celdaMovimiento.x-1][celdaMovimiento.y].noHayPersona()){
                
                char t=celdaMovimiento.saberTipo();
                celdaMovimiento.tipo=mapa.celdas[celdaMovimiento.x-1][celdaMovimiento.y].tipo;
                mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].tipo=t;                

            
                celdaMovimiento.x=celdaMovimiento.x-1;
                celdaMovimiento.sprite=2;
                mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].tipo='M';
                
          

  

            
        }
    }

    private void moverArriba() {
        if (celdaMovimiento.y>0 && mapa.celdas[celdaMovimiento.x][celdaMovimiento.y-1].noHayPersona()){
                char t=celdaMovimiento.saberTipo();
                celdaMovimiento.tipo=mapa.celdas[celdaMovimiento.x][celdaMovimiento.y-1].tipo;
                
                mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].tipo=t;
                

                
                celdaMovimiento.y=celdaMovimiento.y-1;
                celdaMovimiento.sprite=3;
                mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].sprite=3;
                mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].tipo='M';

                
             
        }  
    }
            
}
