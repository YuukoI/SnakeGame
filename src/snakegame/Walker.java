package snakegame;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Walker implements Runnable {

    Snake s;
    boolean state = true;

    public Walker(Snake s) {
        this.s = s;
    }

    @Override
    public void run() {
        while (true) {
            s.move();
            s.repaint();
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Walker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void stop(){
        this.state = false;
    }
}
