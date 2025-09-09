package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import entity.Entity;

public class OBJ_SlowBoots extends Entity {
    GamePanel gp;

    public OBJ_SlowBoots(GamePanel gp) {
        super(gp);

        name = "SlowBoots";
        down1 = setupSprite("/objects/slowBoots1");
    }
}
