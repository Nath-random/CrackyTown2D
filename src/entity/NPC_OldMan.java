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
        setDialogue();
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

    public void setDialogue() {
        dialogues[0] = "Press Enter zum Quittieren!!\nThen talk to me again";
        dialogues[1] = "Willkommen auf der Cracky-Insel, \nin 130 Jahren wird hier die \nMetropole Cracky-Town entstehen";
        dialogues[2] = "Ich komme aus dem Jahr 2040.\nIn der Zukunft sehen alle so aus wie ich.";
        dialogues[3] = "Ich heisse übrigens Juul";
        dialogues[4] = "Leider ist meine Zeitmaschine kaputt.\nEs ist nicht möglich sie zu reparieren.\nSie wird erst 2040 wieder erfunden.";
        dialogues[5] = "Bis dahin müssen wir Cracky-Town\n selber wieder aufbauen.\nBist du dabei? Dann drücke alt+f4!";
        dialogues[6] = "I bi dumm :(";
        dialogues[7] = "wennd nomol machsch stürtzts ab!!";
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


    @Override
    public void speak() {
        super.speak();
    }



}
