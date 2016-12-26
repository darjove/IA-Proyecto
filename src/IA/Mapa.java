package IA; 

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;

public class Mapa extends JComponent implements Constantes {

    public Celdas[][] celdas;
    public Celdas celdaMovimiento;
    Lienzo lienzoPadre;
    

    public Mapa(Lienzo lienzoPadre) {
        celdas = new Celdas[NUM_CELDAS_WIDTH][NUM_CELDAS_HEIGHT];


        this.lienzoPadre=lienzoPadre;
        crearAcera();

        crearCarreteras();
        crearEdificios();
        celdas[NUM_CELDAS_WIDTH-1][0].tipo='K';
     
        crearParadas();
        
        repaint();
        
        this.setSize(TAM_WIDTH, TAM_HEIGHT);
    }
 

    
    private void crearEdificios(){
        crearDepartamentos(2,4);
        crearDepartamentos(2,NUM_CELDAS_HEIGHT-5);
        crearCasas(2,3);
        crearCasas(2,9);
        crearCasas(2,NUM_CELDAS_HEIGHT-4);
        crearCasas(2,10);
  
        
    }
    
    private void crearCasas(int x, int y){
        for(int i=x+1;i<NUM_CELDAS_WIDTH-1;i++){
            if(celdas[i][y].tipo=='C' && celdas[i+1][y].tipo!='L' && celdas[i-1][y].tipo!='R' )
                celdas[i][y]= new Celdas(i+(i * TAM_CELDA), y + (y * TAM_CELDA),'H');

        }
        
    }
    private void crearDepartamentos(int x, int y){
        for(int i=x+1;i<NUM_CELDAS_WIDTH-1;i++){
            if(celdas[i][y].tipo=='C' && celdas[i+1][y].tipo!='L' && celdas[i-1][y].tipo!='R' )
                celdas[i][y]= new Celdas(i+(i * TAM_CELDA), y + (y * TAM_CELDA),'D');

        }
        

    }
    private void crearAcera(){
        
         for(int i=0;i<NUM_CELDAS_WIDTH;i++){
             for(int j=0;j<NUM_CELDAS_HEIGHT;j++){
                 celdas[i][j]= new Celdas(i+(i * TAM_CELDA), j + (j * TAM_CELDA),'C');
                
             }
            
         }
    }
    
    private void crearCarreteras(){
        
        
  //Crear esquinas
        for(int i=0;i<NUM_CELDAS_WIDTH;i=i+6)
            for (int j=0;j<NUM_CELDAS_HEIGHT;j=j+6)
                crearCarreteraEsquinas(i,j);
        
        //crear carreteras verticales
        
        for (int i=0;i<NUM_CELDAS_WIDTH;i=i+6)
            for (int j=0;j<NUM_CELDAS_HEIGHT-1;j++)
                if(celdas[i][j].tipo!='E' && celdas[i][j].tipo!='X' && celdas[i][j].tipo!='Y')
                    crearCarreteraVertical(i,j);
        
        
        //crear carreteras horizontales
        
        for (int i=0;i<NUM_CELDAS_HEIGHT;i=i+6)
            for (int j=0;j<NUM_CELDAS_WIDTH-2;j++)
                if(celdas[j][i].tipo!='E' && celdas[j][i].tipo!='X' && celdas[j][i].tipo!='Y')
                    crearCarreteraHorizontal(j,i);
    
    }

    
    private void crearCarreteraEsquinas(int x, int y){

        celdas[x][y]= new Celdas(x+(x * TAM_CELDA), y + (y * TAM_CELDA),'E');
        celdas[x+1][y]= new Celdas(x+1+((x+1) * TAM_CELDA), y + (y * TAM_CELDA),'E');
        celdas[x][y+1]= new Celdas(x+(x * TAM_CELDA), y+1 + ((y+1) * TAM_CELDA),'E');
        celdas[x+1][y+1]= new Celdas(x+1+((x+1) * TAM_CELDA), y+1 + ((y+1) * TAM_CELDA),'E');
        
        crearPasosPeatonales(x,y);
        
    }
    private void crearPasosPeatonales(int x, int y){
        
        if(x>0 && y>0){
            celdas[x-1][y].tipo='X';
            celdas[x-1][y+1].tipo='X';
            
        }
        if (x<NUM_CELDAS_WIDTH-4 && y>0){
            celdas[x+2][y].tipo='X';
            celdas[x+2][y+1].tipo='X';
        }
        if(y>0 && x>0){
            
                celdas[x][y-1].tipo='Y';
                celdas[x+1][y-1].tipo='Y';
        }
        if(y<NUM_CELDAS_HEIGHT-4 && x>0){
            celdas[x][y+2].tipo='Y';
            celdas[x+1][y+2].tipo='Y';
        }
        
        
    }
    private void crearCarreteraVertical(int x, int y){
        
            celdas[x][y]= new Celdas(x+(x * TAM_CELDA), y + (y * TAM_CELDA),'L');
            celdas[x+1][y]= new Celdas(x+1+((x+1) * TAM_CELDA), y + (y * TAM_CELDA),'R');

            
    
    }
    private void crearCarreteraHorizontal(int x,int y){
      
            celdas[x][y]= new Celdas(x+(x * TAM_CELDA), y + (y * TAM_CELDA),'S');
            celdas[x][y+1]= new Celdas(x+(x * TAM_CELDA), y+1 + ((y+1) * TAM_CELDA),'I');
    }
    @Override   
    public void update(Graphics g) {
        for (int i = 0; i < NUM_CELDAS_WIDTH; i++) {
            for (int j = 0; j < NUM_CELDAS_HEIGHT; j++) {
                celdas[i][j].update(g);
            }
        }
    }

    boolean noHayAutoAbajo(Celdas celda) {
        if(celdas[celda.x][celda.y+1].esPasoPeatonal() && celda.x<NUM_CELDAS_WIDTH-2 && celda.x>2 && celda.y<NUM_CELDAS_HEIGHT-4) {
            int xc=celda.x;
            int yc=celda.y+2;
            boolean puedeCruzar=true;
            int veces=4;
            while(veces>0){
                
                if(celdas[xc][yc].tipo!='V'){
                    xc--;
                    veces--;

                } else {
                    return false;
                }
            }
            veces=4;
            xc=celda.x;
            yc=celda.y+1;
            while(veces>0){
                if(celdas[xc][yc].tipo!='V'){
                    xc++;
                    veces--;

                } else {
                    return false;
                }
            }
        }
        else if(celda.x<=2){
            int xc=celda.x;
            int yc=celda.y+1;
            boolean puedeCruzar=true;
            int veces=4;
            while(veces>0){
                if(celdas[xc][yc].tipo!='V'){
                    xc++;
                    veces--;

                } else {
                    return false;
                }
            }
        }
        else if(celda.y==4){
            int xc=celda.x;
            int yc=celda.y+2;
            boolean puedeCruzar=true;
            int veces=4;
            while(veces>0){
                if(celdas[xc][yc].tipo!='V'){
                    xc--;
                    veces--;

                } else {
                    return false;
                }
            }
        }else if(celda.x>5 && celda.y>=NUM_CELDAS_HEIGHT-4 && celda.x<NUM_CELDAS_WIDTH-5 ){
            int xc=celda.x;
            int yc=celda.y+1;
            int veces=4;
            while(veces>0){
                if(celdas[xc][yc].tipo!='V'){
                    xc++;
                    veces--;

                } else {
                    return false;
                }
            }
        }
            
       
        return true;
        
        
    }

    boolean noHayAutoArriba(Celdas celda) {
        if(celdas[celda.x][celda.y-1].esPasoPeatonal()  && celda.x<NUM_CELDAS_WIDTH-2 && celda.x>2 && celda.y>2) {
            int xc=celda.x;
            int yc=celda.y-1;
            boolean puedeCruzar=true;
            int veces=4;
            while(veces>0){
                
                if(celdas[xc][yc].tipo!='V'){
                    
                    xc--;
                    veces--;

                } else {
                    return false;
                }
            }
            veces=4;
            xc=celda.x;
            yc=celda.y-2;
            while(veces>0){
                
                if(celdas[xc][yc].tipo!='V'){
                    
                    xc++;
                    veces--;

                } else {
                    return false;
                }
            }

        }else if(celda.x<=2){
            int xc=celda.x;
            int yc=celda.y-2;
            boolean puedeCruzar=true;
            int veces=4;
            while(veces>0){
                if(celdas[xc][yc].tipo!='V'){
                    xc++;
                    veces--;

                } else {
                    return false;
                }
            }
        }
       
        
         
        return true;
        
        
    }

    boolean noHayAutoIzquierda(Celdas celda) {
         if(celdas[celda.x-1][celda.y].esPasoPeatonal()  && celda.y<NUM_CELDAS_HEIGHT-4 && celda.y>2 && celda.x>2) {
            int xc=celda.x-1;
            int yc=celda.y;
            int veces=4;
            while(veces>0){
                
                if(celdas[xc][yc].tipo!='V'){
                    
                    yc++;
                    veces--;

                } else {
                    return false;
                }
            }
            veces=4;
            xc=celda.x-2;
            yc=celda.y;
            while(veces>0){
                
                if(celdas[xc][yc].tipo!='V'){
                    
                    yc--;
                    veces--;

                } else {
                    return false;
                }
            }
            
    }
         else if(celda.x>=2 && celda.y>=NUM_CELDAS_HEIGHT-4){
            int xc=celda.x-2;
            int yc=celda.y;
            int veces=4;
            while(veces>0){
                
                if(celdas[xc][yc].tipo!='V'){
                    
                    yc--;
                    veces--;

                } else {
                    return false;
                }
            }
         }
        return true;
    }

    boolean noHayAutoDerecha(Celdas celda) {
        if(celdas[celda.x+1][celda.y].esPasoPeatonal()  && celda.y<NUM_CELDAS_HEIGHT-4 && celda.y>2 && celda.x>2 && celda.x<NUM_CELDAS_WIDTH-6) {
            int xc=celda.x+1;
            int yc=celda.y;
            int veces=4;
            while(veces>0){
                
                if(celdas[xc][yc].tipo!='V'){
                    
                    yc--;
                    veces--;

                } else {
                    return false;
                }
            }
            veces=4;
            xc=celda.x+2;
            yc=celda.y;
            while(veces>0){
                
                if(celdas[xc][yc].tipo!='V'){
                    
                    yc++;
                    veces--;

                } else {
                    return false;
                }
            }
            
        }
        
        return true;
    }

    void carteroEnPortal() {
        
    }

    private void crearParadas() {
        celdas[27][7].tipo='W';
        celdas[27][8].tipo='A';
        celdas[33][7].tipo='W';
        celdas[33][8].tipo='A';
        celdas[27][12].tipo='W';
        celdas[27][11].tipo='A';
        celdas[33][12].tipo='W';
        celdas[33][11].tipo='A';
        
        repaint();
    }

 


    
    

  


}

