package galaga;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Galaga extends JPanel implements ActionListener {

    private Timer timer;
    private Nave nave_espacial;
    private Tierra planeta;
    public ArrayList<Alien> aliens;
    public ArrayList<Item> items;
    public ArrayList<Itemp> itemsp;
    private boolean en_partida;
    private boolean choque;
    private boolean victoria;
    private final int posicion_nave_x = 950;
    private final int posicion_nave_y = 360;
    private final int bordes_x =  1280;
    private final int bordes_y = 720;
    private final int DELAY = 15;
    
    
    public int max_aliens;
    public int max_items;
    public int max_itemsp;
    public boolean naves_fin;
    public int puntuacion;
    public int numero_naves=0;

    //private int[][] pos_aliens = {{0,300},{0+22,450},{0+78,100},{0+100,550},{0+400, 29}, {0+250, 59}, {0+456, 89}
    //,{0+98,109},{0+588,259},{0+600,170},{0+482,400},{0+268,570},{0+367,66}};

    //50 aliens para carga aleatoria
    private int[][] pos_aliens ;
    //prueba de carga de aliens randoms
    
    
    private int[][] pos_items;
        
    private int[][] pos_itemsp;
    

    public Galaga(int aliens, int items, int itemsp) {
        pos_aliens = new int[aliens][2];
        max_aliens = aliens;
        pos_items = new int[items][2];
        max_items = items;
        pos_itemsp = new int[itemsp][2];
        max_itemsp = itemsp;
        initBoard();
    }

    private void initBoard() {


        //cargar tablero

        addKeyListener(new Teclado());
        setFocusable(true);
        setBackground(Color.BLACK);
        setOpaque(true);
        en_partida = true;
        victoria = false;
        choque =false;
        naves_fin=false;

        setPreferredSize(new Dimension(bordes_x, bordes_y));

        nave_espacial = new Nave(posicion_nave_x, posicion_nave_y);
        planeta= new Tierra(0,0);
        initAliens();
        initItems();
        initItemsp();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void initAliens() {
        Random rnd = new Random();
        this.aliens = new ArrayList();
        int alien_x;
        int alien_y;
        int min_x = -100;
        int max_x = 300;
        int min_y =50;
        int max_y = 650;
        for (int i=0; i<max_aliens;i++){
            //cargar nuevo alien con posicion aleatoria

            alien_x = (int) (rnd.nextInt(max_x - min_x)+min_x);
            alien_y = (int) (rnd.nextInt(max_y - min_y)+min_y);

            pos_aliens[i][0]=alien_x;
            pos_aliens[i][1]=alien_y;
        }

        //print pos aliens
        //System.out.printf("Numero de aliens"+ pos_aliens.length );
        //for (int i = 0; i<pos_aliens.length;i++){
        //    System.out.println("Alien x="+pos_aliens[i][0]+" y="+pos_aliens[i][1]);
        //}

        for (int[] p : pos_aliens) {

            this.aliens.add(new Alien(p[0],p[1]));

        }
      
            
}
    
    
    //arreglo de items
    public void initItems(){
        Random rnd = new Random();
        items = new ArrayList();

        int item_x;
        int item_y;
        int min_x = 0;
        int max_x = 300;
        int min_y =50;
        int max_y = 650;
        for (int i=0; i<max_items;i++){
            //cargar nuevo alien con posicion aleatoria

            item_x = (int) (rnd.nextInt(max_x - min_x)+min_x);
            item_y = (int) (rnd.nextInt(max_y - min_y)+min_y);

            pos_items[i][0]=item_x;
            pos_items[i][1]=item_y;
        }
        
        for (int []its : pos_items){
            items.add(new Item(its[0],its[1]));
        }
    }
    //arreglo de items penalizados
    
    public void initItemsp(){
        Random rnd = new Random();
        itemsp=new ArrayList();


        int itemp_x;
        int itemp_y;
        int min_x = -100;
        int max_x = 300;
        int min_y =50;
        int max_y = 650;
        for (int i=0; i<max_items;i++){
            //cargar nuevo alien con posicion aleatoria

            itemp_x = (int) (rnd.nextInt(max_x - min_x)+min_x);
            itemp_y = (int) (rnd.nextInt(max_y - min_y)+min_y);

            pos_itemsp[i][0]=itemp_x;
            pos_itemsp[i][1]=itemp_y;
        }
        
        for(int []itsp : pos_itemsp){
            itemsp.add(new Itemp(itsp[0],itsp[1]));
        }
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (en_partida) {

            drawObjects(g);

        } 
        if(naves_fin) {

            Ganaste(g);
           
        }
        
        if(choque){
            TeChocaron(g);
        }
        

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawObjects(Graphics g) {
        if(planeta.isVisible()){
            g.drawImage(planeta.getImage(),planeta.getX(),planeta.getY(),null);
        }
        if (nave_espacial.isVisible()) {
            g.drawImage(nave_espacial.getImage(), nave_espacial.getX(), nave_espacial.getY(),
                    this);
        }
        

        ArrayList<Laser> ms = nave_espacial.getDisparos();

        ms.stream().filter((m) -> (m.isVisible())).forEachOrdered((m) -> {
            g.drawImage(m.getImage(), m.getX(), m.getY(), this);
        });
        
        aliens.stream().filter((a) -> (a.isVisible())).forEachOrdered((a) -> {
            g.drawImage(a.getImage(), a.getX(), a.getY(), this);
        });
        items.stream().filter((a) -> (a.isVisible())).forEachOrdered((a) -> {
            g.drawImage(a.getImage(), a.getX(), a.getY(), this);
        });
        itemsp.stream().filter((a) -> (a.isVisible())).forEachOrdered((a) -> {
            g.drawImage(a.getImage(), a.getX(), a.getY(), this);
        });
        
        g.setColor(Color.WHITE);
        Font small = new Font("Calibri", Font.ROMAN_BASELINE, 25);
        g.setFont(small);
        g.drawString("Puntaje : " +puntuacion + " || \n Enemigos Vivos: "+(max_aliens-numero_naves), 500, 25);

        g.setColor(Color.RED);
        g.drawString("Peligro, los asteroides que destruyen dañaran la tierra",400,650);
        
    }

    private void Ganaste(Graphics g) {

        victoria = true;
        String msg = "FIN DEL JUEGO \n HAS SALVADO EL MUNDO";
        Font small = new Font("Calibri", Font.ROMAN_BASELINE, 50);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.BLUE);
        g.setFont(small);
        g.drawString(msg, (bordes_x - fm.stringWidth(msg)) / 2,
                bordes_y / 2);
        
        
    }
    
    private void TeChocaron(Graphics g) {
        
      String msg = "FIN DEL JUEGO \n LA TIERRA FUE COLONIZADA";
        Font small = new Font("Calibri", Font.ROMAN_BASELINE, 50);
        FontMetrics fm = getFontMetrics(small);
        planeta.death_Tierra();
        g.setColor(Color.red);
        g.setFont(small);
        g.drawString(msg, (bordes_x - fm.stringWidth(msg)) / 2,
                bordes_y / 2);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        enPartida();

        updateNave();
        updateMissiles();
        updateAliens();
        
        updateItems();
        updateItemsP();

        Colisiones();

        repaint();
    }

    private void enPartida() {
        
        if (!en_partida && !choque || !en_partida && victoria) {
            timer.stop();
        }
        
    }

    private void updateNave() {

        if (nave_espacial.isVisible()) {
            nave_espacial.mover();
        }
    }

    private void updateMissiles() {

        ArrayList<Laser> ms = nave_espacial.getDisparos();//probrar con Laser

        for (int i = 0; i < ms.size(); i++) {

            Laser m = ms.get(i);

            if (m.isVisible()) {
                m.mover();
            } else {
                ms.remove(i);
            }
        }
    }

    private void updateAliens() {

        if (aliens.isEmpty()) {

            
            naves_fin=true;
            
        }

        for (int i = 0; i < aliens.size(); i++) {
            
            Alien a = aliens.get(i);
            if (a.isVisible()) {
                a.mover();
            } else {
                aliens.remove(i);
                puntuacion=puntuacion+35;
                numero_naves=numero_naves+1;
                //System.out.println("Alienigenas eliminados= "+numero_naves);
            
            }

            
               

        }
    }
    
    private void updateItems(){
        if (items.isEmpty()){
            
            return;
        }
        for (int i=0; i<items.size();i++){
            Item b = items.get(i);
            if(b.isVisible()){
                b.mover();
            } else{
                nave_espacial.updateNivelDisparo();
                items.remove(i);
                puntuacion=puntuacion+50;
            }
        }
    }
    
    private void updateItemsP(){
        if (itemsp.isEmpty()){
            
            return;
        }
        for (int i=0; i<itemsp.size();i++){
            Itemp c = itemsp.get(i);
            if(c.isVisible()){
                c.mover();
            } else{
                itemsp.remove(i);
                puntuacion=puntuacion-30;
            }
        }
    }
    

    public void Colisiones() {

        Rectangle minave_buzz = nave_espacial.getBounds();
        Rectangle tierra = new Rectangle();

        tierra.setBounds(860, 0, 60, 600);
        for (Alien alien : aliens) {
            Rectangle alienigena = alien.getBounds();

            if (minave_buzz.intersects(alienigena)) {
                nave_espacial.setVisible(false);
                alien.setVisible(false);
                
                choque=true;
                
            }
            
            if(tierra.intersects(alienigena)){
                planeta.setVisible(false);
                alien.setVisible(false);
                choque=true;
                nave_espacial.setVisible(false);
            }

            /*for(Itemp penal : itemsp){
                Rectangle penalizacion = penal.getBounds();

                if(alienigena.intersects(penalizacion)){

                    alien.updateHit();
                    alien.setVisible(false);
                    penal.setVisible(false);
                }
            }*/
        }

        ArrayList<Laser> ms = nave_espacial.getDisparos();

        for (Laser disparo : ms) {

            Rectangle disparo_laser = disparo.getBounds();

            for (Alien alien : aliens) {

                Rectangle alienigena = alien.getBounds();

                if (disparo_laser.intersects(alienigena)) {

                    disparo.setVisible(false);

                    alien.setVisible(false);
                }
            }
            for(Item item : items){
                Rectangle bonus = item.getBounds();
                
                if(disparo_laser.intersects(bonus)){
                    disparo.setVisible(false);
                    item.setVisible(false);
                }
            }
            
            for(Itemp penal : itemsp){
                Rectangle penalizacion = penal.getBounds();
                
                if(disparo_laser.intersects(penalizacion)){
                    disparo.setVisible(false);
                    penal.setVisible(false);
                }
            }
        }
    }

    

    private class Teclado extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            nave_espacial.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            nave_espacial.keyPressed(e);
        }
    }
}

