/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IA;


import java.util.ArrayList;
import java.util.TimerTask;
import javax.swing.JOptionPane;

/**
 *
 * @author dario
 */
public class BusquedaAnchuraLadron extends TimerTask implements Constantes {
    
    public Mapa mapa;
    Ladron ladron;
    public ArrayList<Estado> colaEstados;
    public ArrayList<Estado> historial;
    public ArrayList<Character> pasos;
    //para tener un busqueda anchura multiobjetivo
    public ArrayList<Estado> destinos;
    public int index_pasos;
    public Estado inicial;
    public Estado objetivo;
    public Estado temp;
    public Cartero cartero;
    public boolean exito;
    public boolean parar;
    
    public BusquedaAnchuraLadron(Mapa mapa,Ladron ladron, Cartero cartero) {
        
        this.mapa=mapa;
        this.cartero=cartero;
        this.ladron=ladron;
        colaEstados=new ArrayList<>();
        historial=new ArrayList<>();
        pasos=new ArrayList<>();
        destinos=new ArrayList<>();
        index_pasos=0;
        exito=false;
        parar=false;
    }


    
    public boolean buscar(Estado inicial,Estado objetivo) {
        index_pasos=0;
        colaEstados.add(inicial);
        historial.add(inicial);
        this.objetivo=objetivo;
        exito=false;
        
        if ( inicial.equals(objetivo)) exito=true;
        
        while ( !colaEstados.isEmpty() && !exito ){
            
            temp=colaEstados.get(0);
            //System.out.println(temp.toString());
            colaEstados.remove(0);
            moverArriba(temp);
            moverAbajo(temp);
            moverIzquierda(temp);
            moverDerecha(temp);
        }
        
        if ( exito ) {
            System.out.println("Ruta calculada");
            this.calcularRuta();
            return true;
        }
        else {
            System.out.println("La ruta no pudo calcularse");
            return false;
        }
        
    }
        private void moverArriba(Estado e) {  
        if ( e.y > 0 ) {
            if ( mapa.celdas[e.x][e.y-1].puedeMoverse() ) { 
                 Estado arriba=new Estado(e.x,e.y-1,'U',e);
                 if ( !historial.contains(arriba)) {
                    colaEstados.add(arriba);
                    historial.add(arriba);
                    if ( arriba.equals(objetivo)) {
                        objetivo=arriba;
                        exito=true;
                    }
                    
                 }
            }     
        }
    }
       private void moverAbajo(Estado e) {
       
        if ( e.y+1 < NUM_CELDAS_HEIGHT ) { 
            if ( mapa.celdas[e.x][e.y+1].puedeMoverse() ) {
                 Estado abajo=new Estado(e.x,e.y+1,'D',e);   
                 if ( !historial.contains(abajo)) {
                    colaEstados.add(abajo);
                    historial.add(abajo);
                    //laberinto.celdas[e.x][e.y+1].tipo='A';
                    if ( abajo.equals(objetivo)) {
                        //laberinto.celdas[e.x][e.y+1].tipo='P';
                        objetivo=abajo;
                        exito=true;
                    }
                 }   
            }     
        }
    }    
    
    private void moverIzquierda(Estado e) {
        if ( e.x > 0 ) {
            if ( mapa.celdas[e.x-1][e.y].puedeMoverse() ) {
                Estado izquierda=new Estado(e.x-1,e.y,'L',e);
                if ( !historial.contains(izquierda)) {
                    
                   colaEstados.add(izquierda);
                   historial.add(izquierda);
                   
                   if ( izquierda.equals(objetivo)) {
                       
                       objetivo=izquierda;
                       exito=true;
                   }
                }   
            }    
        }
    }    
    
    private void moverDerecha(Estado e) {
        
        if ( e.x < NUM_CELDAS_WIDTH-1 ) {
            if ( mapa.celdas[e.x+1][e.y].puedeMoverse() ) {
               Estado derecha=new Estado(e.x+1,e.y,'R',e); 
               if ( !historial.contains(derecha)){
                 colaEstados.add(derecha);
                 historial.add(derecha);
                 
                 if ( derecha.equals(objetivo)) {
                     objetivo=derecha;
                     exito=true;
                 }
               }  
            }     
        }
    }    
    
    public void calcularRuta() {
        Estado predecesor=objetivo;
        do{
            pasos.add(0,predecesor.oper);
            predecesor=predecesor.predecesor;
        }while ( predecesor != null);
        index_pasos=pasos.size()-1;
        
    }

    
    public void run() {
       
       if ( ! parar ) {
        
          colaEstados.clear();
          historial.clear();
          pasos.clear(); 
       
          Estado subinicial,subobjetivo;
          boolean resultado;
          
         do{
              
              subinicial=new Estado(ladron.celdaMovimiento.x,
                                ladron.celdaMovimiento.y,'N',null);
              Estado temp= new Estado(cartero.celdaMovimiento.x,cartero.celdaMovimiento.y,'N',null);
              
              subobjetivo=destinos.get(0);
              
              resultado=this.buscar(subinicial,subobjetivo);
          
        
              if ( !resultado) {
                      
                      colaEstados.clear();
                      historial.clear();
                      pasos.clear(); 
                      destinos.remove(subobjetivo);
                      
              }
             
          
              
          
          }while(!resultado && !destinos.isEmpty());
              
  
             
          
      
          if ( pasos.size() > 1 ) {
             switch(pasos.get(1)) {
                case 'D': ladron.moverAbajo();break;
                case 'U': ladron.moverArriba(); break;
                case 'R': ladron.moverDerecha();break;
                case 'L': ladron.moverIzquierda();break;
             }
          
          destinos.add(0, new Estado(cartero.celdaMovimiento.x,cartero.celdaMovimiento.y,'N',null));
          mapa.lienzoPadre.repaint();  
          Estado estadoLadron=new Estado(ladron.celdaMovimiento.x,ladron.celdaMovimiento.y,'N',null);
          Estado estadoCartero=new Estado(cartero.celdaMovimiento.x,cartero.celdaMovimiento.y,'N',null);
          if(estadoLadron.equals(estadoCartero)){
              parar=true;
          }
          
          
           
       }
              
              
       
       }else{
           JOptionPane.showMessageDialog(null, "Ladrón cazó al cartero");
           this.cancel();
       }
       

    }   
}
