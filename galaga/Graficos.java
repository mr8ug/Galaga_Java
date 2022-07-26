/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galaga;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author mrcar
 */
public class Graficos {
    protected int x;
    protected int y;
    protected int Ancho;
    protected int Alto;
    protected boolean vis;
    private Image imagen;
    
    public Graficos(int x, int y){
        this.x=x;
        this.y=y;
        vis=true;
    }
    
    protected void getImageDimensions(){
        Ancho = imagen.getWidth(null);
        Alto = imagen.getHeight(null);
    }
    
    protected void cargaImagen(String archivo){
        Image nuevo_archivo = new ImageIcon(getClass().getResource(archivo)).getImage();
        //  ImageIcon nuevo_archivo = new ImageIcon(imageName);
        imagen=nuevo_archivo;
    }
    
    public Image getImage(){
        return imagen;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public boolean isVisible(){
        return vis;
    }
    
    public void setVisible(Boolean visibilidad){
        vis=visibilidad;
    }
    
    public Rectangle getBounds(){
        return new Rectangle(x,y,Ancho,Alto);
    }
}
