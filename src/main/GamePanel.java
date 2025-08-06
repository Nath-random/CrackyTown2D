package main;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable{

    // SCREEN SETTINGS
    final int originalTileSize = 16; //16x16 tile
    final int scale = 3; //16*3 = 48

    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12; //Das Spiel hat 16*12 tiles
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    //Frames per second
    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    //players default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        //game loop
        double drawInterval = 1000000000 / FPS; //bei 60FPS etwa 0.0167 seconds
        double delta = 0;
        long lastFrameTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastFrameTime) / drawInterval;
            timer += (currentTime - lastFrameTime);
            lastFrameTime = currentTime;
            if (delta >= 1) {
                update();
                repaint(); //ist komisch, aber ruft irgendwie paintComponent auf
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("In der letzten Sekunde wurden " + drawCount + " Frames gezeichnet.");
                drawCount = 0;
                timer = 0;
            }

        }

    }

    public void update() {
        if (keyH.upPressed) {
            playerY -= playerSpeed; //links oben ist 0 0, darum muss man minus machen
        }
        if (keyH.leftPressed) {
            playerX -= playerSpeed;
        }
        if (keyH.downPressed) {
            playerY += playerSpeed;
        }
        if (keyH.rightPressed) {
            playerX += playerSpeed;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
//        System.out.println("öppis");
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g; //explicit down-cast

        g2.setColor(Color.white);

        g2.fillRect(playerX, playerY, tileSize, tileSize);

        g2.dispose(); // Löschen für bessere Performance
    }

}
