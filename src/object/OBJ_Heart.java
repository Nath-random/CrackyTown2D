package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Heart extends Entity {
    GamePanel gp;

    public OBJ_Heart(GamePanel gp) {
        super(gp);

        name = "Heart";
        image1 = setupSprite("/objects/heart1");
        image2 = setupSprite("/objects/heart2");
        image3 = setupSprite("/objects/heart3");


    }
}





