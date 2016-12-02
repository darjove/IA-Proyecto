
package IA;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.TimerTask;

public class Cartero implements Constantes{
    public Mapa mapa;
    public Celdas cartero;
    public Celdas celdaMovimiento;
    public BusquedaAnchura inteligencia;



    public Cartero(Mapa mapa) {
        this.mapa=mapa;
        celdaMovimiento= new Celdas(NUM_CELDAS_WIDTH-1,1,mapa.celdas[NUM_CELDAS_WIDTH-1][1].tipo);
        cartero=new Celdas(NUM_CELDAS_WIDTH-1,1,mapa.celdas[NUM_CELDAS_WIDTH-1][1].tipo);
        mapa.celdas[cartero.x][cartero.y].tipo='Z';
        inteligencia= new BusquedaAnchura(mapa,this);
        mapa.repaint();
    }
  public void moverCelda(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_DOWN:
                moverAbajo();
                break;
            case KeyEvent.VK_UP:
                 moverArriba();

                break;
            case KeyEvent.VK_RIGHT:
                moverDerecha();
                break;
            case KeyEvent.VK_LEFT:
                moverIzquierda();
                break;
            
        }
     
    }
   public void moverArriba() {
        if(celdaMovimiento.y>0){
           
            if(mapa.celdas[celdaMovimiento.x][celdaMovimiento.y-1].puedeMoverse() && mapa.noHayAutoArriba(celdaMovimiento) ) {
                
                char t=celdaMovimiento.saberTipo();
                celdaMovimiento.tipo=mapa.celdas[celdaMovimiento.x][celdaMovimiento.y-1].tipo;
                
                mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].tipo=t;
                celdaMovimiento.ysprite=3;
                if (mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].xsprite==3){
                    celdaMovimiento.xsprite=0;
                }else{
                    celdaMovimiento.xsprite=mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].xsprite+1;
                }
                celdaMovimiento.y=celdaMovimiento.y-1;
                mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].tipo='J';
                mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].xsprite=celdaMovimiento.xsprite;
                mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].ysprite=celdaMovimiento.ysprite;
                
             
        if(celdaMovimiento.tipo=='P'){
                    
                }
  
                        
            
            }



       }
   }
    //Metodo movimiento izquierda
    public void moverIzquierda() {
        if (celdaMovimiento.x>0)
            if(mapa.celdas[celdaMovimiento.x-1][celdaMovimiento.y].puedeMoverse() && mapa.noHayAutoIzquierda(celdaMovimiento)){
                char t=celdaMovimiento.saberTipo();
                celdaMovimiento.tipo=mapa.celdas[celdaMovimiento.x-1][celdaMovimiento.y].tipo;
                mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].tipo=t;
                
                celdaMovimiento.ysprite=1;
                if (mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].xsprite==3){
                    celdaMovimiento.xsprite=0;
                }else{
                    celdaMovimiento.xsprite=mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].xsprite+1;
                }
                
                celdaMovimiento.x=celdaMovimiento.x-1;
                mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].tipo='J'; 
                mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].xsprite=celdaMovimiento.xsprite;
                mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].ysprite=celdaMovimiento.ysprite;

                if(celdaMovimiento.tipo=='P'){
                    
                }
  

            
        }
    }

    //Metodo movimiento derecha
    public void moverDerecha() {
        if (celdaMovimiento.x<NUM_CELDAS_WIDTH-1) 
            if(mapa.celdas[celdaMovimiento.x+1][celdaMovimiento.y].puedeMoverse() && mapa.noHayAutoDerecha(celdaMovimiento)){
                char t=celdaMovimiento.saberTipo();
                celdaMovimiento.tipo=mapa.celdas[celdaMovimiento.x+1][celdaMovimiento.y].tipo;
                mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].tipo=t;
                celdaMovimiento.ysprite=2;
                if (mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].xsprite==3){
                        celdaMovimiento.xsprite=0;
                    }else{
                        celdaMovimiento.xsprite=mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].xsprite+1;
                    }

                celdaMovimiento.x=celdaMovimiento.x+1;
                mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].tipo='J';
                mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].xsprite=celdaMovimiento.xsprite;
                mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].ysprite=celdaMovimiento.ysprite;
                if(celdaMovimiento.tipo=='P'){
                    
                }
  
            }
    }

    //Metodo movimiento abajo
    public void moverAbajo() {
        if(celdaMovimiento.y<NUM_CELDAS_HEIGHT-1){
            
            if(mapa.celdas[celdaMovimiento.x][celdaMovimiento.y+1].puedeMoverse()  &&  mapa.noHayAutoAbajo(celdaMovimiento)  ) {
                        char t=celdaMovimiento.saberTipo();
                        celdaMovimiento.tipo=mapa.celdas[celdaMovimiento.x][celdaMovimiento.y+1].tipo;
                        mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].tipo=t;                
                        celdaMovimiento.ysprite=0;

                        if (mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].xsprite==3){
                            celdaMovimiento.xsprite=0;
                        }else{
                            celdaMovimiento.xsprite=mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].xsprite+1;
                        }

                        celdaMovimiento.y=celdaMovimiento.y+1;
                        mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].tipo='J';
                        mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].xsprite=celdaMovimiento.xsprite;
                        mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].ysprite=celdaMovimiento.ysprite;
                        if(celdaMovimiento.tipo=='P'){
                    
                }
  
                           

            }
        }
    }
    public void activarJugador(int i, int j){ 
        cartero.tipo='Z';
    }

   
}
