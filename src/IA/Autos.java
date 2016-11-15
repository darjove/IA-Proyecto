
package IA;

import java.awt.Point;
import java.util.TimerTask;

public class Autos extends TimerTask implements Constantes{
    public Mapa mapa;
    public Celdas auto;
    public Celdas celdaMovimiento;
    public Point p1,p2,p3,p4;


    public Autos(Mapa mapa, Point xp, Point yp) {
        p1= new Point(xp.x,xp.y);
        p2= new Point(yp.x,xp.y);
        p3= new Point(yp.x,yp.y);
        p4= new Point(xp.x,yp.y);
        this.mapa=mapa;
        celdaMovimiento= new Celdas(p1.x,p1.y, mapa.celdas[p1.x][p1.y].tipo);
        auto=new Celdas(p1.x, p1.y,'V');
        mapa.celdas[auto.x][auto.y].tipo='V';
        mapa.repaint();
    }
    public void moverAdversario(){
        if (celdaMovimiento.x==p2.x && celdaMovimiento.y<p3.y && celdaMovimiento.y>=p2.y)     moverAbajo();
        else if (celdaMovimiento.x>=p1.x && celdaMovimiento.y==p1.y && celdaMovimiento.x<p2.x) moverDerecha();
        else if (celdaMovimiento.x<=p3.x && celdaMovimiento.y==p3.y && celdaMovimiento.x>p4.x) moverIzquierda();
        else if (celdaMovimiento.x==p4.x && celdaMovimiento.y<=p4.y && celdaMovimiento.y>=p1.y) moverArriba();           
        
   
         
  
    }
    public void moverDerecha(){

        if (mapa.celdas[celdaMovimiento.x+1][celdaMovimiento.y].noHayPersona()){
            char t=celdaMovimiento.saberTipo();
            celdaMovimiento.tipo=mapa.celdas[celdaMovimiento.x+1][celdaMovimiento.y].tipo;
            mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].tipo=t;
            celdaMovimiento.x=celdaMovimiento.x+1;
            celdaMovimiento.sprite=0;
            mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].sprite=0;
            mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].tipo='V';
            
   
        }   
        
    }
    private void moverAbajo() {
        if (celdaMovimiento.y<NUM_CELDAS_HEIGHT-1 && mapa.celdas[celdaMovimiento.x][celdaMovimiento.y+1].noHayPersona()){
                char t=celdaMovimiento.saberTipo();
                celdaMovimiento.tipo=mapa.celdas[celdaMovimiento.x][celdaMovimiento.y+1].tipo;
                mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].tipo=t;                

                celdaMovimiento.y=celdaMovimiento.y+1;
                celdaMovimiento.sprite=1;
                mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].sprite=1;
                mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].tipo='V';

       
        }
    }
    public void moverIzquierda(){
        if (celdaMovimiento.x>0 && mapa.celdas[celdaMovimiento.x-1][celdaMovimiento.y].noHayPersona()){
                
                char t=celdaMovimiento.saberTipo();
                celdaMovimiento.tipo=mapa.celdas[celdaMovimiento.x-1][celdaMovimiento.y].tipo;
                mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].tipo=t;                

            
                celdaMovimiento.x=celdaMovimiento.x-1;
                celdaMovimiento.sprite=2;
                mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].sprite=2;
                mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].tipo='V';

       
          

  

            
        }
    }
    
    public void moverArriba(){
                if (celdaMovimiento.y>0 && mapa.celdas[celdaMovimiento.x][celdaMovimiento.y-1].noHayPersona()){
                char t=celdaMovimiento.saberTipo();
                celdaMovimiento.tipo=mapa.celdas[celdaMovimiento.x][celdaMovimiento.y-1].tipo;
                
                mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].tipo=t;
                

                
                celdaMovimiento.y=celdaMovimiento.y-1;
                celdaMovimiento.sprite=3;
                mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].sprite=3;
                mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].tipo='V';

                
             
        }  
    }
    @Override
    public void run() {


        moverAdversario();
        mapa.lienzoPadre.repaint();
    }


   
}
