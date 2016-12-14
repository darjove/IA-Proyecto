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

    public Peaton(Mapa mapa, Point p, int n){
        this.mapa=mapa;
        celdaMovimiento=new Celdas(p.x, p.y,mapa.celdas[p.x][p.y].tipo);
        peaton= new Celdas(p.x,p.y,mapa.celdas[p.x][p.y].tipo);
        mapa.celdas[peaton.x][peaton.y].tipo='O';
        
        npeaton=n;
        mapa.repaint();
        
    }
    
        public void moverPeaton(Celdas micro, char mov) {
            char t= celdaMovimiento.tipo;
            switch(mov){
                case 'U':
                    celdaMovimiento.tipo=mapa.celdas[micro.x][micro.y+1+npeaton].tipo;
                    mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].tipo=t;
                    celdaMovimiento.y=micro.y+1+npeaton;
                    celdaMovimiento.x=micro.x;
                    mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].tipo='O';
                    
                    break;
                case 'D':
                    celdaMovimiento.tipo=mapa.celdas[micro.x][micro.y-1-npeaton].tipo;
                    mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].tipo=t;
                    celdaMovimiento.y=micro.y-1-npeaton;
                    celdaMovimiento.x=micro.x;
                    
                    mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].tipo='O';
                    
                    break;
                case 'R':
                    celdaMovimiento.tipo=mapa.celdas[micro.x-1-npeaton][micro.y].tipo;
                    mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].tipo=t;
                    celdaMovimiento.x=micro.x-1-npeaton;
                    celdaMovimiento.y=micro.y;
                    mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].tipo='O';
                    
                    break;
                case 'L':
                    celdaMovimiento.tipo=mapa.celdas[micro.x+1+npeaton][micro.y].tipo;
                    mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].tipo=t;
                    celdaMovimiento.x=micro.x+1+npeaton;
                    celdaMovimiento.y=micro.y;
                    mapa.celdas[celdaMovimiento.x][celdaMovimiento.y].tipo='O';
                    
                     break;
                
            }
                    
   
         
  
    
    }



}

