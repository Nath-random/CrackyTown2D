package main;

import enums.GameState;

import java.awt.*;

public class EventHandler {

    GamePanel gp;

    //all EventRects
    EventRect[][] eventRect;

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;

    public EventHandler(GamePanel gp) {
        this.gp = gp;

        eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];

        int col = 0;
        int row = 0;
        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = 23;
            eventRect[col][row].y = 23;
            eventRect[col][row].width = 2;
            eventRect[col][row].height = 2;
            eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
            eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;
            row++;
            if (row == gp.maxWorldRow) {
                row = 0;
                col++;
            }
        }

    }

    // Event Coordinates are coded here
    public void checkEvents() {

        //check if player has moved away from previous event
        int distanceX = Math.abs(previousEventX - gp.player.worldX);
        int distanceY = Math.abs(previousEventY - gp.player.worldY);
        int distance = Math.max(distanceX, distanceY);
        if (distance >= gp.tileSize) {
            canTouchEvent = true;
        }

        if (canTouchEvent) {
            if (hit(60, 47, "any")) {
                chuehFlade(60, 47, GameState.DIALOGUE);
            }
            if (hit(75, 42, "right")) {
                healingPool(75, 42, GameState.DIALOGUE);
            }
            if (hit(12, 45, "any")) {
                teleport(85, 36, GameState.PLAY);
            }
            if (hit(85, 37, "any")) {
                teleport(13, 45, GameState.PLAY);
            }
        }
    }

    //Calculates if the player triggers the event
    public boolean hit(int eventCol, int eventRow, String reqDirection) { //required Direction
        boolean hit = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect[eventCol][eventRow].x = eventCol * gp.tileSize + eventRect[eventCol][eventRow].x;
        eventRect[eventCol][eventRow].y = eventRow * gp.tileSize + eventRect[eventCol][eventRow].y;

        if (gp.player.solidArea.intersects(eventRect[eventCol][eventRow]) && !eventRect[eventCol][eventRow].eventDone) {
            if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;

                previousEventX = gp.player.worldX;
                previousEventY = gp.player.worldY;
            }
        }

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect[eventCol][eventRow].x = eventRect[eventCol][eventRow].eventRectDefaultX;
        eventRect[eventCol][eventRow].y = eventRect[eventCol][eventRow].eventRectDefaultY;
        return hit;
    }

    public void chuehFlade(int col, int row, GameState gameState) {
        gp.gameState = gameState;
        gp.ui.currentDialogue = "You fall into an Chuehflade";
        gp.player.life -= 1;
        canTouchEvent = false;
//        eventRect[col][row].eventDone = true;
    }

    public void healingPool(int col, int row, GameState gameState) {

        if (gp.keyH.enterPressed) {
            gp.gameState = gameState;
            gp.ui.currentDialogue = "You drank from the piss water!\n(You healed fully up!)";
            gp.player.life = gp.player.maxLife;
            eventRect[col][row].eventDone = true;
            gp.tileManager.mapTileNum[col + 1][row] = 2;
        } else {
            gp.ui.hintMessage = "Press ENTER to drink!";
        }
    }

    public void teleport(int destCol, int destRow, GameState gameState) {
        gp.player.worldX = destCol * gp.tileSize;
        gp.player.worldY = destRow * gp.tileSize;
    }
}
