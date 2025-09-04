package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_SlowBoots extends SuperObject {
    GamePanel gp;

    public OBJ_SlowBoots(GamePanel gp) {
        this.gp = gp;

        name = "SlowBoots";
        try {
            image1 = ImageIO.read(getClass().getResourceAsStream("/objects/slowBoots1.png"));
            image1 = uTool.scaleImage(image1, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
