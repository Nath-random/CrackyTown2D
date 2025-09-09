package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_FastBoots extends Entity {
    GamePanel gp;

    public OBJ_FastBoots(GamePanel gp) {
        super(gp);

        name = "FastBoots";
        down1 = setupSprite("/objects/fastBoots1");
    }
}
