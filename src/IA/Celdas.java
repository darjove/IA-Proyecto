package IA;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.text.AttributedCharacterIterator;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class Celdas extends JComponent implements Constantes{
    int x;
    int y;
    char tipo;
    int xsprite;
    int ysprite;
    int sprite;
    int npeatones;
    BufferedImage camino, block, portal, acera, peaton, carretera_inf,carretera_sup,carretera_der,
                carretera_izq, carretera_esq, departamento, casa, jugadorprincipio, peatonal_ver, 
                peatonal_hor, micro;
    BufferedImage sjugador[][], jugador, svehiculo[], vehiculo;
    
    public Celdas(int x, int y, char tipo){
        this.x=x;
        this.y=y;
        this.tipo=tipo;
        xsprite=0;
        ysprite=0;

        
        try {
            jugador = ImageIO.read(new File("imagenes/sprites_postman.png"));
            peaton = ImageIO.read(new File("imagenes/peaton.png"));
            block = ImageIO.read(new File("imagenes/block.png"));
            camino = ImageIO.read(new File("imagenes/camino.png"));
            portal = ImageIO.read(new File("imagenes/portal.png"));
            acera = ImageIO.read(new File("imagenes/acera.png"));
            carretera_inf=ImageIO.read(new File("imagenes/carretera-inf-hor.png"));
            carretera_sup=ImageIO.read(new File("imagenes/carretera-sup-hor.png"));
            carretera_der=ImageIO.read(new File("imagenes/carretera-der.png"));
            carretera_izq=ImageIO.read(new File("imagenes/carretera-izq.png"));
            carretera_esq=ImageIO.read(new File("imagenes/carretera-esq.png"));
            departamento=ImageIO.read(new File("imagenes/edificio.png"));
            peatonal_ver=ImageIO.read(new File("imagenes/peatonal_vert.png"));
            peatonal_hor=ImageIO.read(new File("imagenes/peatonal_hor.png"));
            vehiculo=ImageIO.read(new File("imagenes/vehiculos.png"));
            casa=ImageIO.read(new File("imagenes/casa.png"));
            jugadorprincipio=ImageIO.read(new File("imagenes/cartero-inicio.png"));
            sjugador= new BufferedImage[4][4];
            micro=ImageIO.read(new File("imagenes/micro.png"));
            
            for(int i = 0; i < 4; i++) {
                for(int j = 0; j < 4; j++) {
                    sjugador[i][j] =jugador.getSubimage(i * TAM_CELDA,j * TAM_CELDA,TAM_CELDA, TAM_CELDA);
                }
            }
            svehiculo= new BufferedImage[4*3];
            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 4; j++) {
                    svehiculo[(i * 4) + j] = vehiculo.getSubimage(i * TAM_CELDA,
                     j* TAM_CELDA, TAM_CELDA, TAM_CELDA);
                    }
                }

               
        } catch (IOException e) {
            System.out.println("Imagenes no encontradas");
        }
    }



  

    
    @Override
    public void update(Graphics g){
        switch(tipo){
            case 'C':
                g.drawImage(camino, x, y,this); break;
            case 'B':
                g.drawImage(block, x, y,this); break;
            case 'P':
                g.drawImage(portal, x, y,this); 

                break;
            case 'A':
                g.drawImage(acera, x,y,this); break;
            case 'O':
                g.drawImage(peaton, x,y,this); break;
            case 'I':
                g.drawImage(carretera_inf, x,y,this); break;
            case 'S':
                g.drawImage(carretera_sup, x,y,this); break;
            case 'R':
                g.drawImage(carretera_der, x,y,this); break;
            case 'L':
                g.drawImage(carretera_izq, x,y,this); break;
            case 'E':
                g.drawImage(carretera_esq, x,y,this); break;
            case 'D':
                g.drawImage(departamento, x,y,this); break;
            case 'J':
                g.drawImage(sjugador[xsprite][ysprite], x,y,this); break;
                
            case 'V':
                g.drawImage(svehiculo[sprite], x,y,this); break;
            case 'H':
                g.drawImage(casa, x,y,this); break;
            case 'Z':
                g.drawImage(jugadorprincipio, x,y,this); break;
            case 'X':
                g.drawImage(peatonal_hor, x,y,this); break;
            case 'Y':
                g.drawImage(peatonal_ver, x,y,this); break;
            case 'M':
                g.drawImage(micro, x,y,this); break;
                
                
            
                  
          
  
        }
        

    }
   int getXsprite(){
       return xsprite;
   }
   public boolean comprobarCelda(int clickX, int clickY){
       Rectangle rect= new Rectangle(x,y,TAM_CELDA,TAM_CELDA);
       return rect.contains(new Point(clickX,clickY));
      
       
       
   }

   public char saberTipo() {
       char c=this.tipo;
         switch(c){
            case 'C': return c;
            case 'B':return c;
            case 'P':return c;
            case 'A':return c;
            case 'O':return c;
            case 'I':return c;
            case 'S':return c;
            case 'R':return c;
            case 'L':return c;
            case 'E':return c;
            case 'D':return c;
            case 'X': return c;
            case 'Y': return c;
            case 'M': return c;
                
         }
         return 0;
   }

    boolean puedeMoverse() {
        switch(tipo){
            case 'X': return true;
            case 'Y': return true;
            case 'C': return true;
            case 'P':  return true;
            default: return false;   
        }
    }

    boolean noHayPersona() {
        switch(tipo){
            case 'J': return false;
            case 'V': return false;
            case 'O': return false;
            case 'D': return false;
            case 'H': return false;
            default: return true; 
        }
    }

    boolean esPasoPeatonal() {
        switch(tipo){
            case 'X': return true;
            case 'Y': return true;
            default: return false; 
        }
    }

    



 
    
  
   



}
