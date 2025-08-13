package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_FastBoots extends SuperObject {
    GamePanel gp;

    public OBJ_FastBoots(GamePanel gp) {
        this.gp = gp;

        name = "FastBoots";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/fastBoots1.png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
