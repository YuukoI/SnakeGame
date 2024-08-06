package snakegame;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Panel extends JPanel{
    
    Color panelColor = Color.DARK_GRAY;
    int maxSize;
    int size;
    int amount;
    int residue;
    
    public Panel(View view, int maxSize, int size){
        this.maxSize = maxSize;
        this.size = size;
        this.amount = maxSize / size;
        this.residue = maxSize % size;
        setBackground(Color.BLACK);
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.setColor(panelColor);
        for (int i = 0; i < amount; i++) {
            for (int j = 0; j < amount; j++) {
                g.fillRect(residue / 2 + i*amount, residue / 2 + j*amount, amount-1, amount-1);
            }
        }
    }
    
}
