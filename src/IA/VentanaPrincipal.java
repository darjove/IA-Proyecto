package IA;

import java.awt.Dimension;
import javax.swing.JFrame;

public class VentanaPrincipal extends JFrame implements Constantes {

    public Lienzo lienzo;

    public VentanaPrincipal() {
        lienzo = new Lienzo();
        this.getContentPane().add(lienzo);
        this.setTitle("Ciudad Virtual | Inteligencia Artificial 2016-2");
        this.setSize(TAM_WIDTH + 40, TAM_HEIGHT + 40);
    }

}
