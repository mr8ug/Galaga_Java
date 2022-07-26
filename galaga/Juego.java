/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galaga;

import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


/**
 *
 * @author mrcar
 */
public class Juego extends JFrame{


    public static int num_aliens = 15;
    public static int num_items=5;
    public static int num_itemsp = 3;

    
    public Juego(){
        inicializar_UI();
    }
    
    private void inicializar_UI(){
        add(new Galaga(num_aliens, num_items,num_itemsp));
        
        setResizable(false);
        pack();
        setSize(1280,720);
        setTitle("GALAGA");
        setLocationRelativeTo(null);
        setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("GALAGA!");

        
    }
    
    public static void main(String[] args){
        SpinnerModel value_aliens = new SpinnerNumberModel(15,5,50,1);
        SpinnerModel value_items = new SpinnerNumberModel(2,1,3,1);
        SpinnerModel value_itemsp = new SpinnerNumberModel(10,5,20,1);
        JSpinner max_aliens ;
        JSpinner max_items;
        JSpinner max_itemsp;

        JFrame frame = new JFrame("GALAGA - by Mr8ug");


        JPanel panel = new JPanel();

        JLabel l_aliens = new JLabel("# Aliens");

        max_aliens = new JSpinner(value_aliens);
        JLabel l_items = new JLabel("# Boosters");

        max_items = new JSpinner(value_items);
        JLabel l_itemsp = new JLabel("# Asteroides");

        max_itemsp = new JSpinner(value_itemsp);


        JLabel label = new JLabel("Iniciar Juego");
        JButton button = new JButton("Iniciar");


        JLabel tut = new JLabel("CONTROLES");
        JLabel control = new JLabel();

        ImageIcon imgControl = new ImageIcon("controls.png");
        control.setIcon(imgControl);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(() -> {
                    num_aliens = (int) max_aliens.getValue();
                    //System.out.println(num_aliens);
                    num_items = (int) max_items.getValue();
                    num_itemsp = (int) max_itemsp.getValue();

                    Juego game = new Juego();
                    game.setVisible(true);

                });

                Tiempo t = new Tiempo();

                t.start();
            }
        });


        frame.add(panel);
        panel.add(l_aliens);
        panel.add(max_aliens);
        panel.add(l_items);
        panel.add(max_items);
        panel.add(l_itemsp);
        panel.add(max_itemsp);



        //panel.add(tut);
        panel.add(control);

        panel.add(label);
        panel.add(button);

        frame.setSize(500,350);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);



    }
}
