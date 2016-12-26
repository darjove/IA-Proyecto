/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IA;


import static IA.Constantes.NUM_CELDAS_HEIGHT;
import static IA.Constantes.NUM_CELDAS_WIDTH;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TimerTask;


/**
 *
 * @author dario
 */
public class BusquedaAnchura extends TimerTask implements Constantes {
        
    public Mapa mapa;
    public Queue<Estado> colaEstados;
    public ArrayList<Estado> historial;
    public ArrayList<Character> pasos;
    //para tener un busqueda anchura multiobjetivo
    public ArrayList<Estado> destinos;
    public int index_pasos;
    public Estado inicial;
    public Estado objetivo;
    public Estado temp;
    public boolean exito;
    public boolean parar;
    public Cartero cartero;
    public int nDestinos;
    
    public BusquedaAnchura(Mapa laberinto,Cartero cartero) {
        
        this.mapa=laberinto;
        colaEstados=new PriorityQueue<>();
        historial=new ArrayList<>();
        pasos=new ArrayList<>();
        destinos=new ArrayList<>();
        index_pasos=0;
        exito=false;
        this.cartero=cartero;
        nDestinos=NPORTALES;
    }
    
 public boolean buscar(int x1,int y1,Estado o) {
        inicial=new Estado(x1,y1,'N',null);
        inicial.prioridad= distancia(x1, y1,
                mapa.lienzoPadre.cartero.cartero.x,
                mapa.lienzoPadre.cartero.cartero.y);
                
        objetivo=new Estado(o.x,o.y,'P', null);
        colaEstados.add(inicial);
        historial.add(inicial);
        if ( inicial.equals(objetivo)) exito=true;
        
        while ( !colaEstados.isEmpty() && !exito ){
            
            temp=colaEstados.poll();

            moverArriba(temp);
            moverAbajo(temp);
            moverIzquierda(temp);
            moverDerecha(temp);
        }
        
        if ( exito ){
            System.out.println("Ruta calculada");
            return true;
        }
            
        
        else {
            System.out.println("La ruta no pudo calcularse");
            return false;
        }
            
    }
    
    //distancia adversario
    public double distancia(int x1,int y1, int x2, int y2) {
        double valor;
        double parte1=Math.pow(Math.abs(x1-x2),2);
        double parte2=Math.pow(Math.abs(y1-y2),2);
        parte1+=parte2;
        valor=Math.sqrt(parte1);
        return valor;
    }
    
    private void moverArriba(Estado e) {  
        if ( e.y > 0 ) {
            if ( mapa.celdas[e.x][e.y-1].puedeMoverse() ) { 
                Estado arriba=new Estado(e.x,e.y-1,'U',e);
                arriba.prioridad=mapa.celdas[e.x][e.y - 1].n;
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
            if ( mapa.celdas[e.x][e.y+1].puedeMoverse()) {
                 Estado abajo=new Estado(e.x,e.y+1,'D',e);  
                 
                    abajo.prioridad=mapa.celdas[e.x][e.y+1].n;
               
               
                 
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
            if (mapa.celdas[e.x-1][e.y].puedeMoverse()) {
                Estado izquierda=new Estado(e.x-1,e.y,'L',e);
                    izquierda.prioridad=mapa.celdas[e.x-1][e.y].n;
               
               
                
                
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
                    derecha.prioridad=mapa.celdas[e.x+1][e.y].n;
               
               
               
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
            pasos.add(predecesor.oper);
            predecesor=predecesor.predecesor;
        }while ( predecesor != null);
        index_pasos=pasos.size()-1;
        
    }

    @Override
    public synchronized void run() {
        
       boolean mov=false;
    if ( pasos.size()>1 ) {
            
             switch(pasos.get(pasos.size()-2)) {
                 
                case 'D': 
                    mov=cartero.moverAbajo();break;
                case 'U':
                    mov=cartero.moverArriba(); break;
                case 'R':
                    mov=cartero.moverDerecha();break;
                case 'L':
                    mov=cartero.moverIzquierda();break;
             }
            if(mov==true){
                pasos.remove(pasos.size()-1);
                mov=false;
            }
          
             mapa.lienzoPadre.repaint();  
             
     
        }
    else{
        colaEstados.clear();
        historial.clear();
        pasos.clear();
        if(nDestinos<=0){
            this.cancel();
        }
        else{
            
            exito=false;
            this.buscar(cartero.celdaMovimiento.x, cartero.celdaMovimiento.y, cartero.inteligencia.destinos.remove(nDestinos-1));
            this.calcularRuta();
            
            nDestinos--;
            
        }
        
        
    }
    
   
   }
       
   }   

