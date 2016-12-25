/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IA;

/**
 *
 * @author dario
 */

import IA.Celdas;
import IA.Constantes;
import IA.Mapa;
import java.awt.Point;
import java.util.TimerTask;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dario
 */
public class Peaton implements Constantes {
    public Mapa mapa;
    public Celdas peaton;
    public Celdas celdaMovimiento;
    public int npeaton;
    public Point p1,p2,p3,p4, xp;
    public Peaton(Mapa mapa, Point xp, Point p1, Point yp,int n){
        this.xp= new Point(xp.x,xp.y);
        p2= new Point(yp.x,xp.y);
        p3= new Point(yp.x,yp.y);
        p4= new Point(xp.x,yp.y);
        
        this.mapa = mapa;
        celdaMovimiento = new Celdas(this.xp.x, this.xp.y, mapa.celdas[this.xp.x][this.xp.y].tipo);
        peaton = new Celdas(this.xp.x, this.xp.y, 'O');
        npeaton=n;
        this.p1=p1;
        

        
    }
    
    public void moverPeaton() {
        if (celdaMovimiento.x==p2.x && celdaMovimiento.y<p3.y && celdaMovimiento.y>=p2.y){
              if (noHayPared(celdaMovimiento.x, celdaMovimiento.y + 1)) {
                moverAbajo();
            }
        }
        else if (celdaMovimiento.y==p1.y && celdaMovimiento.x<p2.x){
              if (noHayPared(celdaMovimiento.x + 1, celdaMovimiento.y)) {
                moverDerecha();
            }
        }
        else if (celdaMovimiento.x<=p3.x && celdaMovimiento.y==p3.y && celdaMovimiento.x>p1.x){
               if (noHayPared(celdaMovimiento.x - 1, celdaMovimiento.y)) {
                moverIzquierda();
            }
    
        
        }
        else if (celdaMovimiento.x==p1.x && celdaMovimiento.y<=p4.y && celdaMovimiento.y>=p1.y){
           if (noHayPared(celdaMovimiento.x, celdaMovimiento.y - 1)) {
                moverArriba();
            }
  
        }           
                    
   
         
  
    
    }

    private void moverAbajo() {
        char temp = celdaMovimiento.tipo;
        celdaMovimiento.tipo = mapa.celdas[celdaMovimiento.x][celdaMovimiento.y + 1].tipo;
        mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].tipo = temp;
        celdaMovimiento.y = celdaMovimiento.y + 1;
        mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].tipo = 'O';
       
    }

    private void moverDerecha() {
        char temp = celdaMovimiento.tipo;
        celdaMovimiento.tipo = mapa.celdas[celdaMovimiento.x + 1][celdaMovimiento.y].tipo;
        mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].tipo = temp;
        celdaMovimiento.x = celdaMovimiento.x + 1;
        mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].tipo = 'O';
    }

    private void moverIzquierda() {
        char temp = celdaMovimiento.tipo;
        celdaMovimiento.tipo = mapa.celdas[celdaMovimiento.x - 1][celdaMovimiento.y].tipo;
        mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].tipo = temp;
        celdaMovimiento.x = celdaMovimiento.x - 1;
        mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].tipo = 'O';
    }

    private void moverArriba() {
        char temp = celdaMovimiento.tipo;
        celdaMovimiento.tipo = mapa.celdas[celdaMovimiento.x][celdaMovimiento.y - 1].tipo;
        mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].tipo = temp;
        celdaMovimiento.y = celdaMovimiento.y - 1;
        mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].tipo = 'O';

    }
    private boolean noHayPared(int x, int y) {
        return mapa.celdas[x][y].tipo != VEHICULO && mapa.celdas[x][y].tipo != MICRO
                && mapa.celdas[x][y].tipo!= JUGADOR;
    }


}

