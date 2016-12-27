package IA;


import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.TimerTask;

public class Cartero implements Constantes{

    public static int[] cartas;
    public Mapa mapa;
    public Celdas cartero;
    public Celdas celdaMovimiento;
    public BusquedaAnchura inteligencia;
    public ArrayList<Portal> portales;
    public static int nCartas;
    



    public Cartero(Mapa mapa, ArrayList<Portal> portales) {
        this.mapa=mapa;
        this.portales=portales;
        celdaMovimiento= new Celdas(NUM_CELDAS_WIDTH-1,1,mapa.celdas[NUM_CELDAS_WIDTH-1][1].tipo);


        
        
        cartero=new Celdas(NUM_CELDAS_WIDTH-1,1,mapa.celdas[NUM_CELDAS_WIDTH-1][1].tipo);
        nCartas=NUM_CARTAS;
        cartas = new int[nCartas];
        for(int i=0;i<NUM_CARTAS;i++){
            cartas[i]=i+1;
        }
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
                mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].tipo='J';
                mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].xsprite=celdaMovimiento.xsprite;
                mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].ysprite=celdaMovimiento.ysprite;
                int j=0;
                if(celdaMovimiento.tipo=='P'){
                    for(int i=0;i<portales.size();i++){
                        if(portales.get(i).portal.x==celdaMovimiento.x && portales.get(i).portal.y==celdaMovimiento.y){
                            j=i;
                            
                        }
                        
                    
                    }
                    for(int k=0;k<NUM_CARTAS;k++){
                        
                        for(int i=0;i<portales.get(j).numCartas.length;i++){
                            if(cartas[k]==portales.get(j).numCartas[i]){
                                cartas[k]=0;
                            }    
                        }
                        
                    }
                    
                    nCartas=nCartas-portales.get(j).nCartas;
                    
                }
                
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
                mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].tipo='J'; 
                mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].xsprite=celdaMovimiento.xsprite;
                mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].ysprite=celdaMovimiento.ysprite;
                int j=0;
                if(celdaMovimiento.tipo=='P'){
                    for(int i=0;i<portales.size();i++){
                        if(portales.get(i).portal.x==celdaMovimiento.x && portales.get(i).portal.y==celdaMovimiento.y){
                            j=i;
                            
                        }
                        
                    
                    }
                    for(int k=0;k<NUM_CARTAS;k++){
                        
                        for(int i=0;i<portales.get(j).numCartas.length;i++){
                            if(cartas[k]==portales.get(j).numCartas[i]){
                                cartas[k]=0;
                            }    
                        }
                        
                    }
                    
                    nCartas=nCartas-portales.get(j).nCartas;
                    
                }
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
                mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].tipo='J';
                mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].xsprite=celdaMovimiento.xsprite;
                mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].ysprite=celdaMovimiento.ysprite;
                int j=0;
                if(celdaMovimiento.tipo=='P'){
                    for(int i=0;i<portales.size();i++){
                        if(portales.get(i).portal.x==celdaMovimiento.x && portales.get(i).portal.y==celdaMovimiento.y){
                            j=i;
                            
                        }
                        
                    
                    }
                    for(int k=0;k<NUM_CARTAS;k++){
                        
                        for(int i=0;i<portales.get(j).numCartas.length;i++){
                            if(cartas[k]==portales.get(j).numCartas[i]){
                                cartas[k]=0;
                            }    
                        }
                        
                    }
                    nCartas=nCartas-portales.get(j).nCartas;
                    
                }
                
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
                        mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].tipo='J';
                        mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].xsprite=celdaMovimiento.xsprite;
                        mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].ysprite=celdaMovimiento.ysprite;
                        int j=0;
                if(celdaMovimiento.tipo=='P'){
                    for(int i=0;i<portales.size();i++){
                        if(portales.get(i).portal.x==celdaMovimiento.x && portales.get(i).portal.y==celdaMovimiento.y){
                            j=i;
                            
                        }
                        
                    
                    }
                    for(int k=0;k<NUM_CARTAS;k++){
                        
                        for(int i=0;i<portales.get(j).numCartas.length;i++){
                            if(cartas[k]==portales.get(j).numCartas[i]){
                                cartas[k]=0;
                            }    
                        }
                        
                    }
                    
                    nCartas=nCartas-portales.get(j).nCartas;

                }
                        return true;
                           

            }
        }
        return false;
    }
    public void activarJugador(int i, int j){ 
        cartero.tipo='Z';
    }

   
}
