package snakegame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Snake extends JPanel {

    Color snakeColor = Color.GREEN;
    Color foodColor = Color.RED;
    int maxSize;
    int size;
    int amount;
    int residue;
    List<int[]> snake = new ArrayList<>();
    int[] food = new int[2];
    String direction = "d";
    String nextDirection = "d";
    Thread t;
    Walker walker;
    int foodCount;
    private final View view;
    
    ImageIcon headIcon = new ImageIcon(Snake.class.getClassLoader().getResource("resources/head3.png"));
    ImageIcon bodyIcon = new ImageIcon(Snake.class.getClassLoader().getResource("resources/body3.png"));
    ImageIcon foodIcon = new ImageIcon(Snake.class.getClassLoader().getResource("resources/food.png"));

    public Snake(View view, int maxSize, int size) {
        this.view = view;
        setBackground(Color.BLACK);
        this.maxSize = maxSize;
        this.size = size;
        this.amount = maxSize / size;
        this.residue = maxSize % size;
        int[] first = {amount / 2 - 1, amount / 2 - 1};
        int[] second = {amount / 2, amount / 2 - 1};
        snake.add(first);
        snake.add(second);
        generateFood();

        walker = new Walker(this);
        t = new Thread(walker);
        t.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (int i = 0; i < snake.size(); i++) {
            int[] segment = snake.get(i);

            if (i == snake.size()-1) {
                g.drawImage(headIcon.getImage(), residue / 2 + segment[0] * amount, residue / 2 + segment[1] * amount, amount - 1, amount - 1, null);
            } else {
                g.drawImage(bodyIcon.getImage(), residue / 2 + segment[0] * amount, residue / 2 + segment[1] * amount, amount - 1, amount - 1, null);
            }
        }

        g.drawImage(foodIcon.getImage(), residue / 2 + food[0] * amount, residue / 2 + food[1] * amount, amount - 1, amount - 1, null);
        
    }

    public void move() {
        matchDirections();
        int[] last = snake.get(snake.size() - 1);
        int x = 0;
        int y = 0;

        switch (direction) {
            case "w":
                y = -1;
                break;
            case "a":
                x = -1;
                break;
            case "s":
                y = 1;
                break;
            case "d":
                x = 1;
                break;
        }

        int[] newest = {Math.floorMod(last[0] + x, amount), Math.floorMod(last[1] + y, amount)};

        boolean exists = false;

        for (int i = 0; i < snake.size(); i++) {
            if (newest[0] == snake.get(i)[0] && newest[1] == snake.get(i)[1]) {
                exists = true;
                break;
            }
        }

        if (exists) {
            int option = JOptionPane.showOptionDialog(null, "Game Over. Do you want to restart?", "Game Over", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            if (option == JOptionPane.YES_OPTION) {
                resetGame();
            } else {
                System.exit(0);
            }
        } else {
            if (newest[0] == food[0] && newest[1] == food[1]) {
                snake.add(newest);
                SoundManager.playSound("resources/eating.wav", -35.0f);
                generateFood();
                foodCount++;
                if (foodCount == ((maxSize / size) * (maxSize / size))) {
                    int option = JOptionPane.showOptionDialog(null, "YOU WIN!. Do you want to restart?", "Congratulations!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                    if (option == JOptionPane.YES_OPTION) {
                        resetGame();
                    } else {
                        System.exit(0);
                    }
                }

                view.updateInfoLabel("Food: " + getFoodCount());
            } else {
                snake.add(newest);
                snake.remove(0);
            }
        }

    }

    public void generateFood() {
        int x = (int) (Math.random() * amount);
        int y = (int) (Math.random() * amount);
        boolean exists = false;

        for (int[] s : snake) {
            if (s[0] == x && s[1] == y) {
                exists = true;
                generateFood();
                break;
            }
        }

        if (!exists) {
            this.food[0] = x;
            this.food[1] = y;
        }
    }

    public void nextDirection(String d) {
        if ((this.direction.equals("d") || this.direction.equals("a")) && (d.equals("w") || d.equals("s"))) {
            this.nextDirection = d;
        }
        if ((this.direction.equals("w") || this.direction.equals("s")) && (d.equals("a") || d.equals("d"))) {
            this.nextDirection = d;
        }

    }

    public void matchDirections() {
        this.direction = this.nextDirection;
    }

    public void resetGame() {
        snake.clear();
        int[] first = {amount / 2 - 1, amount / 2 - 1};
        int[] second = {amount / 2, amount / 2 - 1};
        snake.add(first);
        snake.add(second);

        generateFood();
    }

    public int getFoodCount() {
        return foodCount;
    }

}
