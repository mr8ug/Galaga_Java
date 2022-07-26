/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galaga;

import java.util.ArrayList;
import java.awt.event.KeyEvent;
/**
 *
 * @author mrcar
 */
public class Nave extends Graficos{
    public int xf;
    public int yf;

    public int nivel;
    private ArrayList disparos;
    public int n=0;
    
    public Nave(int x, int y){
        super(x,y);
        
        inicializar_nave();
    }
    
    private void inicializar_nave(){
        disparos = new ArrayList();
        cargaImagen("player.png");
                getImageDimensions();
    }
    
    public void mover(){
        x+=xf;
        y+=yf;
        
        if(x<1){
            x=1;
        }
        if(y<-50){
            y=0;
        }

        if(y>650){
            y=y-50;
        }
    }
    
    public ArrayList getDisparos(){
        return disparos;
    }
    
    public void keyPressed(KeyEvent tecla){
        int boton = tecla.getKeyCode();
        
        
        
       
       
        if(boton == KeyEvent.VK_UP || boton == KeyEvent.VK_NUMPAD8 || tecla.getKeyChar()=='w'){
            yf=-5;
        }
        if(boton == KeyEvent.VK_DOWN || boton == KeyEvent.VK_NUMPAD2 || tecla.getKeyChar()=='s'){
            yf=5;
        }
        
        if(boton == KeyEvent.VK_RIGHT || boton == KeyEvent.VK_NUMPAD4 || tecla.getKeyChar()=='a' ){
            xf=5;
        }
        if(boton == KeyEvent.VK_LEFT || boton == KeyEvent.VK_NUMPAD6 || tecla.getKeyChar()=='d' ){
            xf=-5;
        }
        if(tecla.getKeyChar()=='f' || boton == KeyEvent.VK_SPACE){
            disparar();
            n=n+1;
            //System.out.println("Disparos realizados: "+n);
            
        }
        
    }
    
    public void disparar(){
        //el 40 es para asegurar que salga desde el centro de la nave
        disparos.add(new Laser(x,y+40, nivel));
        
        
    }
    
    public void keyReleased(KeyEvent tecla){
        int boton = tecla.getKeyCode();
        
       
        
        
       
        if(boton == KeyEvent.VK_UP || boton == KeyEvent.VK_NUMPAD8 || tecla.getKeyChar()=='w'){
            yf=0;
        }
        if(boton == KeyEvent.VK_DOWN || boton == KeyEvent.VK_NUMPAD2 || tecla.getKeyChar()=='s'){
            yf=0;
        }
        if(boton == KeyEvent.VK_RIGHT || boton == KeyEvent.VK_NUMPAD4 || tecla.getKeyChar()=='a' ){
            xf=0;
        }
        if(boton == KeyEvent.VK_LEFT || boton == KeyEvent.VK_NUMPAD6 || tecla.getKeyChar()=='d' ){
            xf=0;
        }
    }

    public void updateNivelDisparo(){
        nivel +=1;
    }



}
