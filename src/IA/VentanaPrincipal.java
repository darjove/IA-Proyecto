package IA;


import java.awt.Dimension;
import javax.swing.JFrame;

public class VentanaPrincipal  extends JFrame implements Constantes{
   public Dimension tamPantalla;
   public Lienzo lienzo;
   
   
   public VentanaPrincipal(){
       lienzo=new Lienzo();
       tamPantalla=super.getToolkit().getScreenSize();
       this.getContentPane().add(lienzo);
       this.setSize(tamPantalla.width,tamPantalla.height);
   }
    
    
}
