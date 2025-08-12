package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {

    public int worldX, worldY;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1; //wechselt immer hin und her
    public int standCounter = 0;

    public int solidAreaDefaultX, solidAreaDefaultY; //werden benötigt, weil solidArea.x und y beim Collision check verändert werden,
                            // um die hitbox für die nächste Bewegung vorauszuberechnen und dann wird es wieder auf defaultWert gesetzt.
    public Rectangle solidArea;
    public boolean collisionOn = false;
}
