package object;

import main.GamePanel;
import main.UtilityTool;

import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
public class SuperObject {

    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48); //Full Hitbox
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    UtilityTool uTool = new UtilityTool();

    public void draw(Graphics2D g2, GamePanel gp) {

        // only paint if object is on screen
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (screenX > 0 - gp.tileSize && screenX < gp.screenWidth + gp.tileSize
                && screenY > 0 - gp.tileSize && screenY < gp.screenHeight + gp.tileSize) {
            g2.drawImage(image, screenX, screenY, null);
        }



    }
}
