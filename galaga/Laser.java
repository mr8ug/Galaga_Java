/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galaga;

import java.util.ArrayList;

/**
 *
 * @author mrcar
 */
public class Laser extends Graficos {
    
    private  int Galaga_Width = 960;
    private  int Vel_Laser =10;

    public void setLaser_boost() {
        this.laser_boost = laser_boost+1;
    }

    public int laser_boost = 0;
    
    public Laser(int x, int y, int nivel){

        super(x,y);
        laser_boost = nivel;
        init_laser();
    }
    
    private void init_laser(){
        if(laser_boost ==0) {
            cargaImagen("laser.png");
        }
        if (laser_boost == 1){
            cargaImagen("laser2.png");
            getImageDimensions();
            Vel_Laser+=5;

        }
        if (laser_boost >= 2){
            cargaImagen("laser3.png");
            getImageDimensions();
            Vel_Laser+=10;
        }

        getImageDimensions();
        
    }
    
    public void mover(){
        x-= Vel_Laser;
        
        if(x> Galaga_Width)
            vis = true;
        
        
    }





}
