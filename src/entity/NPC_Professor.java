package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class NPC_Professor extends Entity {

    public NPC_Professor(GamePanel gp) {
        super(gp);

        direction = "right";
        speed = 1;

        getNPCImage();
        setDialogue();
    }

    public void getNPCImage() {
        up1 = setupSprite("playerUp1");
        up2 = setupSprite("playerUp2");
        left1 = setupSprite("profLeft1");
        left2 = setupSprite("profLeft2");
        right1 = setupSprite("profRight1");
        right2 = setupSprite("profRight2");
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
            System.out.println("loading the NPC sprites went wrong :(");
            e.printStackTrace();
        }
        return scaledImage;
    }

    public void setDialogue() {
        dialogues[0] = "Hallo, ich bin der Dokter";
        dialogues[1] = "Eigentlich bin ich ein Professor, \nich heisse Professor Juul";
    }

    @Override
    public void setAction() {

        npcActionCounter++;

        if (npcActionCounter >= npcNextAction) {


            Random random = new Random();
            int i = random.nextInt(1,101); // 1 - 100
            doNothing = false;

            if (i <= 60) {
                doNothing = true;
                //do nothing
            } else if (i > 60 && i <= 64) {
                direction = "up";
            } else if (i > 64 && i <= 80) {
                direction = "left";
            } else if (i > 80 && i <= 84) {
                direction = "down";
            } else {
                direction = "right";
            }

            npcActionCounter = 0;
            npcNextAction = random.nextInt(5, 90);
        }

    }
}
