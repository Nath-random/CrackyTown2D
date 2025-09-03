package main;

import enums.GameState;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B;
    Font nasa21, paul1V, confessionFull, pixellettersFull;

    public boolean messageOn = false;
    public String message = "";

    // title screen
    public int commandNum = 0;
    public int titleScreenState = 0; // 0: main screen, 1: character selection
    public BufferedImage chosenCharacter;

    //alt von v0.1.0
    int messageCounter = 0;
    public boolean gameFinished = false;
    double playTime;


    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public String currentDialogue = "";

    public String hintMessage = ""; // e.g. press Enter to speak
    public int hintFramesLeft;


    public UI(GamePanel gp) {
        this.gp = gp;

        //load the fonts
        try {
            InputStream is = getClass().getResourceAsStream("/fonts/Nasa21-l23X.ttf");
            nasa21 = Font.createFont(Font.TRUETYPE_FONT, is);
            is = getClass().getResourceAsStream("/fonts/Paul-le1V.ttf");
            paul1V = Font.createFont(Font.TRUETYPE_FONT, is);
            is = getClass().getResourceAsStream("/fonts/TheConfessionFullRegular-8qGz.ttf");
            confessionFull = Font.createFont(Font.TRUETYPE_FONT, is);
            is = getClass().getResourceAsStream("/fonts/Pixellettersfull-BnJ5.ttf");
            pixellettersFull = Font.createFont(Font.TRUETYPE_FONT, is);


        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
    }

    public void showMessage(String text) {

        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
//        g2.setFont(arial_80B);
        g2.setFont(nasa21);
        g2.setColor(Color.white);

        switch (gp.gameState) {
            case GameState.TITLE:
                drawTitleScreen();
                break;
            case GameState.PLAY:
                drawPlayScreen();
                break;
            case GameState.PAUSE:
                drawPauseScreen();
                break;
            case GameState.DIALOGUE:
                drawDialogueScreen();
        }
    }

    public void drawTitleScreen() {
        if (chosenCharacter == null) {
            chosenCharacter = gp.player.right1;
        }


        if (titleScreenState == 0) {
            // Background
            g2.setColor(new Color(50, 130, 30));
            g2.fillRect(0,0, gp.screenWidth, gp.screenHeight);

            // Game Title
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
            String text = "Cracky Town 2D";
            int x = getCenteredTextX(text);
            int y = gp.tileSize * 3;
            g2.setColor(Color.black);
            g2.drawString(text, x, y);

            // Shadow (eigentlich ist das oben der Schatten)
            g2.setColor(Color.white);
            g2.drawString(text, x + 5, y + 5);

            // Player Image
            x = gp.screenWidth / 2 - gp.tileSize;
            y += gp.tileSize * 2;
            g2.drawImage(chosenCharacter, x, y, gp.tileSize * 2, gp.tileSize * 2, null);



            // Menu and Cursor
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));

            text = "START GAME";
            x = getCenteredTextX(text);
            y += gp.tileSize * 3;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x - gp.tileSize, y - 5);
            }

            text = "CRASH THE GAME";
            x = getCenteredTextX(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x - gp.tileSize, y - 5);
            }

            text = "QUIT";
            x = getCenteredTextX(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 2) {
                g2.drawString(">", x - gp.tileSize, y - 5);
            }
        } else if(titleScreenState == 1) {
            // Background
            g2.setColor(new Color(10, 40, 5));
            g2.fillRect(0,0, gp.screenWidth, gp.screenHeight);

            // Game Title
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 60F));
            String text = "SELECT A CHARACTER";
            int x = getCenteredTextX(text);
            int y = gp.tileSize * 3;
            g2.setColor(Color.black);
            g2.drawString(text, x, y);

            // Shadow (eigentlich ist das oben der Schatten)
            g2.setColor(Color.white);
            g2.drawString(text, x + 5, y + 5);

            // Player Image
            x = gp.screenWidth / 2 - gp.tileSize;
            y += gp.tileSize;
            g2.drawImage(chosenCharacter, x, y, gp.tileSize * 2, gp.tileSize * 2, null);



            // Menu and Cursor
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));

            text = "Juul";
            x = getCenteredTextX(text);
            y += gp.tileSize * 3;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                chosenCharacter = gp.player.right1;
                g2.drawString(">", x - gp.tileSize, y - 5);
            }

            text = "Wold";
            x = getCenteredTextX(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                chosenCharacter = gp.player.newPlayer;
                g2.drawString(">", x - gp.tileSize, y - 5);
            }

            text = "Slime";
            x = getCenteredTextX(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 2) {
                chosenCharacter = gp.player.slime;
                g2.drawString(">", x - gp.tileSize, y - 5);
            }

            text = "return";
            x = getCenteredTextX(text);
            y += gp.tileSize * 2;
            g2.drawString(text, x, y);
            if (commandNum == 3) {
                g2.drawString(">", x - gp.tileSize, y - 5);
            }
        }


    }

    public void drawPlayScreen() {
        drawHint();
    }

    public void drawPauseScreen() {
        String text = "Paused";
        int x = getCenteredTextX(text);
        int y = gp.screenHeight / 2;

        g2.drawString(text, x, y);
    }


    public void drawDialogueScreen() {

        int x = gp.tileSize * 2;
        int y = gp.tileSize;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 4;

        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
        // offset for text
        x += gp.tileSize;
        y += gp.tileSize;
        //drawString ignoriert \n, darum wird line break manuell gemacht
        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }

    }

    //for Dialogue
    public void drawSubWindow(int x, int y, int width, int height) {

        Color c = new Color(0, 0, 0, 200);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5)); // Stroke is a outline


        g2.drawRoundRect(x + 5, y + 5, width - 10, height- 10, 25, 25);

    }


    public void drawHint() {

        if (!hintMessage.equals("")) {
            // Background
            int x = gp.tileSize / 4;
            int y = gp.screenHeight - gp.tileSize * 3;
            int width = gp.screenWidth / 2;
            int height = gp.tileSize * 2;

            Color c = new Color(0, 0, 0, 100);
            g2.setColor(c);
            g2.fillRoundRect(x, y, width, height, 10, 10);


            x = gp.tileSize / 2;
            y = gp.screenHeight - gp.tileSize * 2;

            c = new Color(255, 255, 255);
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
            g2.setColor(c);
            g2.drawString(hintMessage, x, y);

            hintFramesLeft--;
        }

        if (hintFramesLeft <= 0) {
            hintMessage = "";
        }
    }

    public int getCenteredTextX(String text) {
        //gibt ein x zurück sodass der Text genau zentriert in der Mitte ist. Bei drawString ist das x der linke Rand
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return (gp.screenWidth - length) / 2;
    }

}
