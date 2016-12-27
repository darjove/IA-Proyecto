/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IA;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author dario
 */
public class Ladron implements Constantes {
    public Mapa mapa;
    public Celdas ladron;
    public Celdas celdaMovimiento;
    public BusquedaAnchuraLadron inteligencia;
    public Cartero cartero;


    Ladron(Mapa mapa, Cartero cartero) {
        this.mapa=mapa;
        
        
        celdaMovimiento= new Celdas(NUM_CELDAS_WIDTH-2,NUM_CELDAS_HEIGHT-3,mapa.celdas[NUM_CELDAS_WIDTH-2][NUM_CELDAS_HEIGHT-3].tipo);
        ladron= new Celdas(celdaMovimiento.x,celdaMovimiento.y,mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].tipo);
    this.cartero=cartero;
        inteligencia= new BusquedaAnchuraLadron(mapa,this, cartero);
        mapa.celdas[ladron.x][ladron.y].tipo='F';
        
        
    }

   
       public boolean moverArriba() {
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
                mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].tipo='F';
      
         
                
                return true;
     
                
                        
            
            }

            

       }
        return false;
   }
    //Metodo movimiento izquierda
    public boolean moverIzquierda() {
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
                mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].tipo='F';
      
                return true;
  

            
        }
        return false;
    }

    //Metodo movimiento derecha
    public boolean moverDerecha() {
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
                mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].tipo='F';
       
                return true;
  
            }
        return false;
    }

    //Metodo movimiento abajo
    public boolean moverAbajo() {
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
                        mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].tipo='F';
        

                }
                        return true;
                           

            
        }
        return false;
    }

}
