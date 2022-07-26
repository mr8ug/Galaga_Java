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
public class Tiempo extends Thread implements Runnable {
    
    public void run(){

        findeljuego();
    }

    private void findeljuego() {
        try{Thread.sleep(2000);
        }catch(Exception e){
        System.out.print("Expection: "+e);
        }
    }
    
}
