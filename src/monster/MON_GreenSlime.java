package monster;

import entity.Entity;
import main.GamePanel;

import java.awt.image.BufferedImage;
import java.util.Random;

public class MON_GreenSlime extends Entity {
    public MON_GreenSlime(GamePanel gp) {
        super(gp);

        name = "GreenSlime";
        speed = 1;
        maxLife = 4;
        life = maxLife;

        solidArea.x = 8;
        solidArea.y = 10;
        solidArea.width = 35;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getMONImage();
    }

    public void getMONImage() {
        down1 = setupSprite("/monster/slime1");
        down2 = setupSprite("/monster/slime1");
        left1 = down1;
        left2 = down2;
        right1 = down1;
        right2 = down2;
        up1 = down1;
        up2 = down2;
    }

    @Override
    public void setAction() {

        if (gp.keyH.enterPressed) {
            System.out.println("a" + worldX / gp.tileSize + " b" + worldY / gp.tileSize);
            System.out.println("player" + gp.player.worldX / gp.tileSize + " b" + gp.player.worldY / gp.tileSize);
            System.out.println(direction);

        }

        npcActionCounter++;

        if (npcActionCounter >= npcNextAction) {


            Random random = new Random();
            int i = random.nextInt(1,101); // 1 - 100
            doNothing = false;

            if (i <= 60) {
                doNothing = true;
                //do nothing
            } else if (i > 60 && i <= 70) {
                direction = "up";
            } else if (i > 70 && i <= 80) {
                direction = "left";
            } else if (i > 80 && i <= 90) {
                direction = "down";
            } else {
                direction = "right";
            }

            npcActionCounter = 0;
            npcNextAction = random.nextInt(5, 200);
        }


    }

    @Override
    public void speak() {

    }
}
