package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class NPC_OldMan extends Entity {


    public NPC_OldMan (GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;

        getNPCImage();
    }


    public void getNPCImage() {
        up1 = setupSprite("playerUp1");
        up2 = setupSprite("playerUp2");
        left1 = setupSprite("playerLeft1");
        left2 = setupSprite("playerLeft2");
        right1 = setupSprite("playerRight1");
        right2 = setupSprite("playerRight2");
        down1 = setupSprite("playerDown1");
        down2 = setupSprite("playerDown2");

    }


    public BufferedImage setupSprite(String imageName) {

        UtilityTool uTool = new UtilityTool();
        BufferedImage scaledImage = null;

        try {
            scaledImage = ImageIO.read(getClass().getResourceAsStream("/npc/" + imageName + ".png"));
            scaledImage = uTool.scaleImage(scaledImage, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            System.out.println("loading the player sprites went wrong :(");
            e.printStackTrace();
        }
        return scaledImage;
    }

    @Override
    public void setAction() {
        npcActionCounter++;

        if (npcActionCounter >= npcNextAction) {


            Random random = new Random();
            int i = random.nextInt(1,101); // 1 - 100
            doNothing = false;

            if (i <= 40) {
                doNothing = true;
                //do nothing
            } else if (i > 40 && i <= 55) {
                direction = "up";
            } else if (i > 55 && i <= 70) {
                direction = "left";
            } else if (i > 70 && i <= 85) {
                direction = "down";
            } else {
                direction = "right";
            }

            npcActionCounter = 0;
            npcNextAction = random.nextInt(5, 90);
        }


    }


}
