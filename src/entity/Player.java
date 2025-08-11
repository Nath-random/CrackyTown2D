package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX; //final, weil Spieler immer in der Mitte ist, aber der Hintergrund scrollt
    public final int screenY;

    public int hasKeys = 0;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = (gp.screenWidth - gp.tileSize) / 2 ; //diese Koordinate ist die Ecke links oben vom Spieler
        screenY = (gp.screenHeight - gp.tileSize) / 2;

        solidArea = new Rectangle(); //sind keine world-Koordinaten, wird beim CollisionChecker umgerechnet
        solidArea.x = 8; //Die Zahlen sind bereits mit Skalar 3 skaliert
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 50;
        worldY = gp.tileSize * 50;
        speed = 4;
        direction = "right";
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/playerUp1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/playerUp2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/playerLeft1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/playerLeft2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/playerRight1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/playerRight2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/playerDown1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/playerDown2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        if(keyH.upPressed == true || keyH.leftPressed || keyH.downPressed || keyH.rightPressed) {

            if (keyH.upPressed) {
                direction = "up";
            }else if (keyH.leftPressed) {
                direction = "left";
            }else if (keyH.downPressed) {
                direction = "down";
            }else if (keyH.rightPressed) {
                direction = "right";
            }

            // CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);
//            System.out.println(collisionOn);
            // Can only move is collision is false
            if (!collisionOn) {
                switch(direction) {
                    case "up":
                        worldY -= speed; //links oben ist 0 0, darum muss man minus machen
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }

            spriteCounter++;
            if (spriteCounter > 10) { //alle 10 Frames
                spriteNum = spriteNum == 1 ? 2 : 1; //wechselt hin und her
                spriteCounter = 0;
            }

        }


    }


    public void pickUpObject(int i) {

        if (i < 999) {
            String objectName = gp.obj[i].name;

            switch(objectName) {
                case "Key":
                    hasKeys++;
                    gp.obj[i] = null; //delete the key
                    break;
                case "Door":
                    if (hasKeys > 0) {
                        gp.obj[i] = null;
                        hasKeys--;
//                        System.out.println(hasKeys + " Keys left...");
                    }
                    break;
                case "Chest":
                    break;
            }
        }
    }



    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch(direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);


    }
}
