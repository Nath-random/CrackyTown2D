package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_SlowBoots extends SuperObject {

    public OBJ_SlowBoots() {

        name = "SlowBoots";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/sampleTexture1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
