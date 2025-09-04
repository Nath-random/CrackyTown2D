package main;

import enums.GameState;

import java.awt.*;

public class EventHandler {

    GamePanel gp;

    //Hitbox of all event-triggers
    Rectangle eventRect;
    int eventRectDefaultX, eventRectDefaultY;

    public EventHandler(GamePanel gp) {
        this.gp = gp;

        eventRect = new Rectangle();
        eventRect.x = 23;
        eventRect.y = 23;
        eventRect.width = 2;
        eventRect.height = 2;
        eventRectDefaultX = eventRect.x;
        eventRectDefaultY = eventRect.y;
    }

    // Event Coordinates are coded here
    public void checkEvents() {

        if (hit(60,47, "any")) {
            chuehFlade(GameState.DIALOGUE);
        }
        if (hit(75, 42, "right")) {
            healingPool(GameState.DIALOGUE);
        }
        if (hit(12, 45, "any")) {
            teleport(85, 36, GameState.PLAY);
        }
        if (hit(85, 37, "any")) {
            teleport(13, 45, GameState.PLAY);
        }
    }

    //Calculates if the player triggers the event
    public boolean hit(int eventCol, int eventRow, String reqDirection) { //required Direction
        boolean hit = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect.x = eventCol * gp.tileSize + eventRect.x;
        eventRect.y = eventRow * gp.tileSize + eventRect.y;

        if (gp.player.solidArea.intersects(eventRect)) {
            if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;
            }
        }

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect.x = eventRectDefaultX;
        eventRect.y = eventRectDefaultY;
        return hit;
    }

    public void chuehFlade(GameState gameState) {
        gp.gameState = gameState;
        gp.ui.currentDialogue = "You fall into an Chuehflade";
        gp.player.life -= 1;
    }

    public void healingPool(GameState gameState) {

        if (gp.keyH.enterPressed) {
            gp.gameState = gameState;
            gp.ui.currentDialogue = "You drank from the piss water!";
            gp.player.life = gp.player.maxLife;
        }
    }

    public void teleport(int destCol, int destRow, GameState gameState) {
        gp.player.worldX = destCol * gp.tileSize;
        gp.player.worldY = destRow * gp.tileSize;
    }
}
