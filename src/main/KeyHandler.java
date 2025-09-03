package main;

import enums.GameState;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, leftPressed, downPressed, rightPressed, enterPressed;

    GamePanel gp;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    //Diese Methode wird bei einer positiven Flanke einmal aufgerufen
    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        // TITLE STATE
        if (gp.gameState == GameState.TITLE && gp.ui.titleScreenState == 0) {
            if (code == KeyEvent.VK_W && gp.ui.commandNum > 0) { //Cursor nach oben
                gp.ui.commandNum--;
            }
            if (code == KeyEvent.VK_S && gp.ui.commandNum < 2) { //Cursor nach unten
                gp.ui.commandNum++;
            }
            if (code == KeyEvent.VK_ENTER) { // 3 Options
                switch (gp.ui.commandNum) {
                    case 0:
                        gp.ui.titleScreenState = 1;
//                        gp.playMusic(0);
                        break;
                    case 1:
                        GameCrasher.crashWhileLoop();
                        break;
                    case 2:
                        System.exit(1);
                        break;
                }

            }
        } else if (gp.gameState == GameState.TITLE && gp.ui.titleScreenState == 1) {
            if (code == KeyEvent.VK_W && gp.ui.commandNum > 0) { //Cursor nach oben
                gp.ui.commandNum--;
            }
            if (code == KeyEvent.VK_S && gp.ui.commandNum < 3) { //Cursor nach unten
                gp.ui.commandNum++;
            }
            if (code == KeyEvent.VK_ENTER) { // 3 Options
                switch (gp.ui.commandNum) {
                    case 0:
                        gp.gameState = GameState.PLAY;
                        break;
                    case 1:
                        gp.gameState = GameState.PLAY;
                        break;
                    case 2:
                        gp.gameState = GameState.PLAY;
                        break;
                    case 3:
                        gp.ui.titleScreenState = 0;
                        gp.ui.commandNum = 0;
                        break;
                }

            }
        }

        // PLAY STATE
        if (gp.gameState == GameState.PLAY) {
            if (code == KeyEvent.VK_W) { //Taste W gedrückt UP
                upPressed = true;
            }
            if (code == KeyEvent.VK_A) { //Taste A gedrückt LEFT
                leftPressed = true;
            }
            if (code == KeyEvent.VK_S) { //Taste S gedrückt DOWN
                downPressed = true;
            }
            if (code == KeyEvent.VK_D) { //Taste D gedrückt RIGHT
                rightPressed = true;
            }

            if (code == KeyEvent.VK_ENTER) {
                enterPressed = true;
            }


            // Zoom
            if (code == KeyEvent.VK_UP) { //Taste UP gedrückt Zoom in
                gp.zoom(1);
            }
            if (code == KeyEvent.VK_DOWN) { //Taste DOWN gedrückt Zoom out
                gp.zoom(-1);
            }

            // PAUSE AND RESUME GAME
            if (code == KeyEvent.VK_P) {
                gp.gameState = GameState.PAUSE;

            }
        }


        // PAUSE STATE
        else if (gp.gameState == GameState.PAUSE) {
            if (code == KeyEvent.VK_P) {
                gp.gameState = GameState.PLAY;

            }



        }



        // DIALOGUE STATE
        else if (gp.gameState == GameState.DIALOGUE) {
            if (code == KeyEvent.VK_ENTER) {
                gp.gameState = GameState.PLAY;
            }


        }




    }

    // Wenn man raus-tabt und erst dann eine Taste loslässt, dann wird diese Methode nicht aufgerufen
    // Darum bewegt sich der Spieler weiter, obwohl man loslässt
    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = false;
        }

    }
}
