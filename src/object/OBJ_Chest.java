package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class OBJ_Chest extends Entity {


    public OBJ_Chest(GamePanel gp) {
        super(gp);

        name = "Chest";
        down1 = setupSprite("/objects/chest1");
    }
}
