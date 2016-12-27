package IA;

import java.awt.Color;
import java.util.Random;


public interface Constantes {
    public final int TAM_CELDA=31   ;
    public final int NUM_CELDAS_WIDTH=40;
    public final int NUM_CELDAS_HEIGHT=20;
    public final int TAM_WIDTH=TAM_CELDA*NUM_CELDAS_WIDTH;
    public final int TAM_HEIGHT=TAM_CELDA*NUM_CELDAS_HEIGHT;
    public final int NUM_PEATONES=5;
    public final int NUM_CARTAS=8;
    public final char JUGADOR='J';
    public final char CAMINO='C';
    public final char CARRETERA_INF='I';
    public final char CARRETERA_SUP='S';
    public final char CARRETERA_DER='R';
    public final char CARRETERA_IZQ='L';
    public final char CARRETERA_ESQ='E';
    public final char BLOCK='B';
    public final char PORTAL='P';
    public final char ACERA='A';
    public final char PEATON='O';
    public final char DEPARTAMENTO='D';
    public final char VEHICULO='V';
    public final char CASA='H';
    public final char INICIOCARTERO='Z';
    public final char PEATONAL_HOR='X';
    public final char PEATONAL_VERT='Y';
    public final char MICRO='M';
    public final int NPORTALES=3;
    
    default int numeroAleatorio(int minimo, int maximo) {
        Random random = new Random();
        int numero_aleatorio = random.nextInt((maximo - minimo) + 1) + minimo;
        return numero_aleatorio;
    }



    
    
                    
}
