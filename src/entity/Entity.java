package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class Entity {

    GamePanel gp;
    public int worldX, worldY;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction = "down";

    //Animation für Player
    public int spriteCounter = 0;
    public int spriteNum = 1; //wechselt immer hin und her
    public int standCounter = 0;

    //Collision
    public int solidAreaDefaultX, solidAreaDefaultY; //werden benötigt, weil solidArea.x und y beim Collision check verändert werden,
                            // um die hitbox für die nächste Bewegung vorauszuberechnen und dann wird es wieder auf defaultWert gesetzt.
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48); // Default
    public boolean collisionOn = false;

    //Animation für NPC
    public int npcActionCounter = 0; // zählt wie viele Frames er schon die gleiche action gemacht hat
    public int npcNextAction = 0;
    boolean doNothing = false; //when a npc decides to not move

    //Dialog für NPC
    String[] dialogues = new String[20];
    int dialogueIndex = 0;

    //Character Status
    public int maxLife;
    public int life;

    //Für Objects
    public BufferedImage image1, image2, image3;
    public String name;
    public boolean collision = false;


    public Entity (GamePanel gp) {
        this.gp = gp;
    }


    public void update() { //this is not for player, player overrides it
        setAction();

        collisionOn = false;
        gp.cChecker.checkTile(this);
        //Check if this entity hits a object
        gp.cChecker.checkObject(this, false);
        //Check if this entity hits the player
        gp.cChecker.checkPlayer(this);



        // Can only move is collision is false
        if (!collisionOn && !doNothing) {
            switch(direction) {
                case "up":
                    worldY -= speed;
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
        if (spriteCounter >= 20) {
            spriteNum = spriteNum == 1 ? 2 : 1; //wechselt hin und her
            spriteCounter = 0;
        }

    }

    //for NPCs
    public void setAction() {
        return;
    }

    public void speak() {
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;

        // NPC looks at player while speaking
        switch (gp.player.direction) {
            case "up":
                direction = "down";
                break;
            case "left":
                direction = "right";
                break;
            case "down":
                direction = "up";
                break;
            case "right":
                direction = "left";
                break;
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

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (screenX > 0 - gp.tileSize && screenX < gp.screenWidth + gp.tileSize
                && screenY > 0 - gp.tileSize && screenY < gp.screenHeight + gp.tileSize) {
            g2.drawImage(image, screenX, screenY, null);
        }

        // Show Hitbox
        if (gp.showEntityHitbox) {
            g2.setColor(Color.red);
            g2.drawRect(solidArea.x + screenX, solidArea.y + screenY, solidArea.width, solidArea.height);
        }

    }

    public BufferedImage setupSprite(String imagePath) {

        UtilityTool uTool = new UtilityTool();
        BufferedImage scaledImage = null;

        try {
            scaledImage = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            scaledImage = uTool.scaleImage(scaledImage, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            System.out.println("loading the NPC sprites went wrong :(");
            e.printStackTrace();
        }
        return scaledImage;
    }


}
