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
public class Portal implements Constantes{
    Celdas portal;
    int nCartas;
    Mapa mapa;
    int numCartas[];
    public Portal(Mapa mapa, int x, int y, int nCartas, int numCartas[]){
        this.mapa=mapa;
        this.nCartas=nCartas;
        this.numCartas=numCartas;

        portal=new Celdas(x,y,'P');
        this.mapa.celdas[portal.x][portal.y].tipo='P';
    }
}
