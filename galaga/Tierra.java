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
public class Tierra extends Graficos {
    public int xf=0;
    public int yf=0;
    
    public int n=0;
    
    public Tierra(int x, int y){
        super(x,y);
        
        init_Tierra();
    }
    
    private void init_Tierra(){
        
        cargaImagen("fondo1280x720.png");
                getImageDimensions();
    }

    public void death_Tierra(){
        cargaImagen("fondodestruido1280x720.png");
        getImageDimensions();
    }
    
    
}
