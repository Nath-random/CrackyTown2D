package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, leftPressed, downPressed, rightPressed;

    GamePanel gp;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

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

        if (code == KeyEvent.VK_UP) { //Taste UP gedrückt Zoom in
            gp.zoom(1);
        }
        if (code == KeyEvent.VK_DOWN) { //Taste DOWN gedrückt Zoom out
            gp.zoom(-1);
        }

    }

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
    }
}
