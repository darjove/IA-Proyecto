
package IA;


public class Cuadra implements Constantes{
    int x;
    int y;
    Celdas cuadra;
    int npeatones;
    Mapa mapa;
    public Cuadra(int x, int y, int npeatones,Mapa mapa) {
        this.mapa=mapa;
        this.x = x;
        this.y = y;
        this.npeatones = npeatones;
        cuadra=new Celdas(x,y,'E');
        mapa.celdas[cuadra.x+1][cuadra.y+1].tipo='Q';

        mapa.repaint();
    }

    
    public void establecerCuadra(int n){
        int d=x+8;
        int a=y+7;
        for(int i=x;i<d;i++){
            for(int j=y;j<a;j++){
                
                mapa.celdas[i][j].npeatones=n;
            }
        }
    }
   
}
