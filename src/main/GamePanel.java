package main;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable{

    // DEBUG SETTINGS
    public boolean showPlayerHitbox = false;
    boolean showFPS = false;
    boolean showPosition = false;
    boolean enableZooming = true;

    // SCREEN SETTINGS
    final int originalTileSize = 16; //16x16 tile
    final int scale = 3; //16*3 = 48

    public int tileSize = originalTileSize * scale;
    public int maxScreenCol = 16;
    public int maxScreenRow = 12; //Das Spiel hat 16*12 tiles
    public int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public int screenHeight = tileSize * maxScreenRow; // 576 pixels

    // WORLD SETTINGS
    public final int maxWorldCol = 100;
    public final int maxWorldRow = 100;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;


    //Frames per second
    int FPS = 60;

    // SYSTEM
    public TileManager tileManager = new TileManager(this);
    KeyHandler keyH = new KeyHandler(this);
    Sound se = new Sound();
    Sound music = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;


    // ENTITY AND OBJECTS
    public Player player = new Player(this, keyH);
    public SuperObject[] obj = new SuperObject[15];


    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setUpGame() {
        aSetter.setObject();
        playMusic(0);
    }

    public void zoom(int i) { //Experimental, causes bugs
        if (enableZooming) {
            int oldWorldWidth = tileSize * maxWorldCol;
            tileSize += i; //i ist 1 oder -1
            int newWorldWidth = tileSize * maxWorldCol;

            double multiplier = (double) newWorldWidth / oldWorldWidth;

            double newPlayerWorldX = player.worldX * multiplier;
            double newPlayerWorldY = player.worldY * multiplier;
            double newPlayerSpeed = newWorldWidth / 600;

            player.worldX = (int) newPlayerWorldX;
            player.worldY = (int) newPlayerWorldY;
            player.speed = (int) newPlayerSpeed;

        }

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
                // DEBUG
                if (showPosition) {
                    System.out.println("Current Position: x=" + player.worldX / tileSize + ", y=" + player.worldY / tileSize);
                }
                if (showFPS) {
                System.out.println("In der letzten Sekunde wurden " + drawCount + " Frames gezeichnet.");
                }
                drawCount = 0;
                timer = 0;
            }

        }

    }

    public void update() {
        player.update();
    }

    @Override
    public void paintComponent(Graphics g) {
//        System.out.println("öppis");
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g; //explicit down-cast

        tileManager.draw(g2);//Layers: die Sachen werden aufeinander gezeichnet. Also muss player nachher kommen

        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }

        // PLAYER
        player.draw(g2);

        // UI
        ui.draw(g2);


        g2.dispose(); // Löschen für bessere Performance
    }

    public void playMusic(int i) {

        music.setFile(i);
        music.play();
//        music.loop();
    }

    public void stopMusic() {
         music.stop();
    }

    public void playSE(int i) { //Sound effect
        se.setFile(i);
        se.play();
    }

}
