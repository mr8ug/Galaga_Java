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
public class Item extends Graficos {
    
    
    private final int Pos_Xi = -200;
    
    public Item (int x, int y){
        super(x,y);
        
        init_Item();
    }
    
    private void init_Item(){
        
        cargaImagen("item.png");
        getImageDimensions();
    }
    
    public void mover(){
        if(x<0){
            x=Pos_Xi;
        }
        
        x+=0;
    }
}
