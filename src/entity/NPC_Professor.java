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
        up1 = setupSprite("/npc/playerUp1");
        up2 = setupSprite("/npc/playerUp2");
        left1 = setupSprite("/npc/profLeft1");
        left2 = setupSprite("/npc/profLeft2");
        right1 = setupSprite("/npc/profRight1");
        right2 = setupSprite("/npc/profRight2");
        down1 = setupSprite("/npc/playerDown1");
        down2 = setupSprite("/npc/playerDown2");
    }


    public void setDialogue() {
        dialogues[0] = "Hallo, ich bin der Dokter";
        dialogues[1] = "Eigentlich bin ich ein Professor, \nich heisse Professor Juul";
        dialogues[2] = "Ich gebe dir jetzt ein paar Tipps:\nAlso mit der Taste P kannst du das Spiel\npausieren";
        dialogues[3] = "Mit Objekten die verdächtig aussehen,\nkannst du interagieren indem\ndu sie anschaust und Enter drückst!";
        dialogues[4] = "Du willst wissen wieso ich so aussehe?";
        dialogues[5] = "Gehts noch?!!?\nDieses Kinn hat mich ein Vermögen\ngekostet!";
        dialogues[6] = "Ich rede nicht mehr mit dir...";
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
            } else if (i > 60 && i <= 62) {
                direction = "up";
            } else if (i > 62 && i <= 80) {
                direction = "left";
            } else if (i > 80 && i <= 82) {
                direction = "down";
            } else {
                direction = "right";
            }

            npcActionCounter = 0;
            npcNextAction = random.nextInt(5, 90);
        }

    }
}
