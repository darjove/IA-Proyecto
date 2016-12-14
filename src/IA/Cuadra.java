
package IA;


public class Cuadra {
    int x;
    int y;
    int npeatones;
    Mapa mapa;
    public Cuadra(int x, int y, int npeatones,Mapa mapa) {
        this.mapa=mapa;
        this.x = x;
        this.y = y;
        this.npeatones = npeatones;

    }

    
    public void establecerCuadra(int n){
        int d=x+4;
        int a=y+4;
        for(int i=x;i<d;i++){
            for(int j=y;j<a;j++){
                mapa.celdas[i][j].npeatones=n;
            }
        }
    }
   
}
