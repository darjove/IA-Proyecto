package IA;

import java.awt.Dimension;
import javax.swing.JFrame;

public class VentanaPrincipal extends JFrame implements Constantes {

    public Lienzo lienzo;

    public VentanaPrincipal() {
        lienzo = new Lienzo();
        this.getContentPane().add(lienzo);
        this.setSize(TAM_WIDTH + 40, TAM_HEIGHT + 40);
    }

}
