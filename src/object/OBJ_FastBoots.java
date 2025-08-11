package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_FastBoots extends SuperObject {

    public OBJ_FastBoots() {

        name = "FastBoots";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/sampleTexture1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
