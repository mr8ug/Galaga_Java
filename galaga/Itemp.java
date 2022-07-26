/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galaga;

/**
 *
 * @author mrcar
 */
public class Itemp extends Graficos {
    
    
    private final int Pos_Xi = -200;
    
    public Itemp (int x, int y){
        super(x,y);
        
        init_Itemp();
    }
    
    private void init_Itemp(){
        
        cargaImagen("meteoro.png");
        getImageDimensions();
    }
    
    public void mover(){
        /*if(x<0){
            x=Pos_Xi;
        }*/
        
        x+=1;
    }
}
