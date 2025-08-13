package entity;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    BufferedImage slowShoe, fastShoe;

    String spriteOverride = null; // null bedeutet: kein Override

    public final int screenX; //final, weil Spieler immer in der Mitte ist, aber der Hintergrund scrollt
    public final int screenY;

    public int hasKeys = 0;

    //For tile based movement
    boolean moving = false;
    int pixelCounter = 0;

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

        //Tile based movement
        if (gp.tileBasedMovement) {
            solidArea.x = 1;
            solidArea.y = 1;
            solidArea.width = 46;
            solidArea.height = 46;
            solidAreaDefaultX = solidArea.x;
            solidAreaDefaultY = solidArea.y;
        }

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
        up1 = setupSprite("playerUp1");
        up2 = setupSprite("playerUp2");
        left1 = setupSprite("playerLeft1");
        left2 = setupSprite("playerLeft2");
        right1 = setupSprite("playerRight1");
        right2 = setupSprite("playerRight2");
        down1 = setupSprite("playerDown1");
        down2 = setupSprite("playerDown2");

        slowShoe = setupSprite("playerShoe1");
        fastShoe = setupSprite("playerShoe2");

    }

    public BufferedImage setupSprite(String imageName) {

        UtilityTool uTool = new UtilityTool();
        BufferedImage scaledImage = null;

        try {
            scaledImage = ImageIO.read(getClass().getResourceAsStream("/player/" + imageName + ".png"));
            scaledImage = uTool.scaleImage(scaledImage, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            System.out.println("loading the player sprites went wrong :(");
            e.printStackTrace();
        }
        return scaledImage;
    }

    public void update() {

        if(keyH.upPressed || keyH.leftPressed || keyH.downPressed || keyH.rightPressed) {
            standCounter = 0;

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
            if (spriteCounter >= 10) { //alle 10 Frames
                spriteNum = spriteNum == 1 ? 2 : 1; //wechselt hin und her
                spriteCounter = 0;
            }

        } else { // Stand still position
            standCounter++;

            if (standCounter >= 20) {
                spriteNum = 1;
            }
        }

    }

    //Version for tileBased Movement
    public void updateTileBased() {
        //wenn spieler sich nicht bewegt, kann er sich in ein neues Feld begeben
        if (!moving) {

            if(keyH.upPressed || keyH.leftPressed || keyH.downPressed || keyH.rightPressed) {
                standCounter = 0;
                moving = true;

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
            }  else { // Stand still position
                standCounter++;

                if (standCounter >= 20) {
                    spriteNum = 1;
                }
            }


        }

        if (moving) { //in here key input isn't checked. This results in tile based movement
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
            if (spriteCounter >= 10) { //alle 10 Frames
                spriteNum = spriteNum == 1 ? 2 : 1; //wechselt hin und her
                spriteCounter = 0;
            }
            pixelCounter += speed;

            if (pixelCounter >= 48) { //Speed muss ein Faktor von 48 sein, sonst weicht es vom Grid ab
                moving = false;
                pixelCounter = 0;
            }
        }
    }


    public void pickUpObject(int i) {

        if (i < 999) {
            String objectName = gp.obj[i].name;

            switch(objectName) {
                case "Key":
                    gp.playSE(0);
                    hasKeys++;
                    gp.obj[i] = null; //delete the key
                    gp.ui.showMessage("You got a brand new key!");
//                    System.out.println("You now have + " + hasKeys + " keys!");
                    break;
                case "Door":
                    if (hasKeys > 0) {
                        gp.playSE(0);
                        gp.obj[i] = null;
                        hasKeys--;
                        gp.ui.showMessage("You used a key!");
//                        System.out.println(hasKeys + " Keys left...");
                    } else {
                        gp.ui.showMessage("You need a key... Trottel");
                    }
                    break;
                case "Chest":
                    gp.playSE(2);
                    gp.obj[i] = null;
                    System.out.println("you opened the fools chest");
                    break;
                case "SlowBoots":
                    speed = 2;
                    spriteOverride = "SlowBoots";
                    gp.ui.showMessage("You are now slow");
                    gp.stopMusic();
                    gp.playSE(3);
//                    gp.ui.gameFinished =  true;
                    break;
                case "FastBoots":
                    spriteOverride = "FastBoots";
                    speed = 17;
                    gp.playSE(1);
                    gp.ui.showMessage("You are now super fast!");
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

        //Sprite Override
        if ("SlowBoots".equals(spriteOverride)) {
            image = slowShoe;
        } else if ("FastBoots".equals(spriteOverride)) {
            image = fastShoe;
        }

        g2.drawImage(image, screenX, screenY,null);

        // Show Hitbox
        if (gp.showPlayerHitbox) {
            g2.setColor(Color.red);
            g2.drawRect(solidArea.x + screenX, solidArea.y + screenY, solidArea.width, solidArea.height);
        }

    }
}
