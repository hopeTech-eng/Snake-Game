/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package snakegame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author HOPEWELL
 */
public class SnakeGame extends JPanel implements ActionListener {

    private final int TILE_SIZE = 25;
    private final int GRID_WIDTH = 20;
    private final int GRID_HEIGHT = 20;
    private final int BOARD_WIDTH = TILE_SIZE * GRID_WIDTH;
    private final int BOARD_HEIGHT = TILE_SIZE * GRID_HEIGHT;
    
    private final LinkedList<Point> snake = new LinkedList<>();
    private Point food;
    private final Random random = new Random();
    
    private char direction = 'R';
    private boolean directionChangedThisTick = false;
    private boolean running = false;
    private int score = 0;
    
    private Timer timer;
    
    public SnakeGame() {
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e.getKeyCode());
            }
        });
        
        startGame();
        
    }
    
    private void startGame() {
        snake.clear();
        
        int startX = GRID_WIDTH / 2;
        int startY = GRID_HEIGHT / 2;
        snake.add(new Point(startX, startY));
        snake.add(new Point(startX - 1, startY));
        snake.add(new Point(startX - 2, startY));
        
        direction = 'R';
        score = 0;
        running = true;
        
        spawnFood();
         if (timer != null) {
             timer.stop();
         }
         timer = new Timer(120, this);
         timer.start();
         
    }
    private void spawnFood() {
        Point newFood;
        do {
            int x = random.nextInt(GRID_WIDTH);
            int y = random.nextInt(GRID_HEIGHT);
            newFood = new Point(x, y);
            
        } while (snake.contains(newFood));
        food = newFood;
    }
    
    private void handleKeyPress(int keyCode) {
        if (!running && keyCode == KeyEvent.VK_SPACE) {
            startGame();
            return;
        }
        
        if (directionChangedThisTick) return;
        
        switch (keyCode) {
            case KeyEvent.VK_UP -> {
                if (direction != 'D') { direction = 'U'; directionChangedThisTick = true; }
            }
            case KeyEvent.VK_DOWN -> {
                if (direction != 'U') { direction = 'D'; directionChangedThisTick = true;}
            }
            case KeyEvent.VK_LEFT -> {
                if (direction != 'R') { direction = 'L'; directionChangedThisTick = true;}
            }
            case KeyEvent.VK_RIGHT -> {
                if (direction != 'L') {direction = 'R'; directionChangedThisTick = true;}
            }
        }
    }
    private void move() {
        Point head = snake.getFirst();
        Point newHead;
        
        newHead = switch (direction) {
            case 'U' -> new Point(head.x, head.y -1);
            case 'D' -> new Point(head.x, head.y +1);
            case 'L' -> new Point(head.x - 1, head.y);
            default -> new Point(head.x + 1, head.y);
        };
        
        if (newHead.x < 0 || newHead.x >= GRID_WIDTH || newHead.y < 0 || newHead.y >= GRID_HEIGHT) {
        gameOver();
        return;
    }
        
        if (snake.contains(newHead)) {
            gameOver();
            return;
        }
        snake.addFirst(newHead);
        
        if (newHead.equals(food)) {
            score++;
            spawnFood();
        } else {
            snake.removeLast();
        }    
        
        directionChangedThisTick = false;
    }
    
    private void gameOver() {
        running = false;
        timer.stop();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
        }
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.setColor(Color.RED);
        g.fillRect(food.x * TILE_SIZE, food.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        
        for (int i = 0; i < snake.size(); i++) {
            Point p = snake.get(i);
            g.setColor(i == 0 ? Color.GREEN : Color.GREEN.darker());
            g.fillRect(p.x * TILE_SIZE, p.y * TILE_SIZE, TILE_SIZE - 1, TILE_SIZE - 1);
            
        }
        
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("Score: " + score, 10, 20);
        
        if (!running) {
            String msg = "Game Over! Press SPACE to restart";
            FontMetrics fm = g.getFontMetrics();
            int msgWidth = fm.stringWidth(msg);
            g.drawString(msg, (BOARD_WIDTH - msgWidth) / 2, BOARD_HEIGHT / 2);
        }
    }
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame frame = new JFrame("SNAKE GAME");
        SnakeGame game = new SnakeGame();
        
        frame.add(game);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        game.requestFocusInWindow();
    }
    
}