package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNum;

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[100];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/maps/worldMap1.txt");
    }

    public void loadMap(String mapFilePath) {
        try {
//            InputStream is = new FileInputStream("res/maps/map1.txt"); // geht auch
            InputStream is = getClass().getResourceAsStream(mapFilePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();

                while (col < gp.maxWorldCol) {
                    String[] numbers = line.split(" "); //length should be == gp.maxWorldCol

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num; //[col][row] macht mehr Sinn als [row][col], weil dann die erste Koordinate x ist.
                    col++;
                }

                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }

            }

        } catch (Exception e) { //bad practice, aber hat der im Tutorial so gemacht
            System.out.println("Something with loading the map went wrong :( .");
            e.printStackTrace();
        }
    }

    public void getTileImage() {
        try {
            // Erklärung warum es getClass benutzt: die read() Methode muss wissen von wo aus der Pfad angegeben wird (relativer Pfad)
            // aber es wird / benutzt, also ist das egal und es wird der absolute Pfad genutzt
            // der Ordner res wird speziell als root Ordner erkannt.
            // man könnte auch z.B. gp.getClass()... schreiben, weil es von da aus auch den res Ordner findet
            // aber z.B. "testString".getClass()... geht nicht, weil String in einem ganz anderen Package ist
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass1.png"));
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/stone1.png"));
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water1.png"));
            tile[2].collision = true;
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass2.png"));
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/dirt1.png"));
            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree1.png"));
            tile[5].collision = true;
            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/flowers1.png"));
            tile[7] = new Tile();
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/flowers2.png"));
            tile[8] = new Tile();
            tile[8].image = ImageIO.read(getClass().getResourceAsStream("/tiles/flowers3.png"));
            tile[9] = new Tile();
            tile[9].image = ImageIO.read(getClass().getResourceAsStream("/tiles/flowers4.png"));
            tile[10] = new Tile();
            tile[10].image = ImageIO.read(getClass().getResourceAsStream("/tiles/path1.png"));
            tile[11] = new Tile();
            tile[11].image = ImageIO.read(getClass().getResourceAsStream("/tiles/path2.png"));
            tile[12] = new Tile();
            tile[12].image = ImageIO.read(getClass().getResourceAsStream("/tiles/path3.png"));
            tile[13] = new Tile();
            tile[13].image = ImageIO.read(getClass().getResourceAsStream("/tiles/path4.png"));
            tile[14] = new Tile();
            tile[14].image = ImageIO.read(getClass().getResourceAsStream("/tiles/path5.png"));
            tile[15] = new Tile();
            tile[15].image = ImageIO.read(getClass().getResourceAsStream("/tiles/path6.png"));
            tile[16] = new Tile();
            tile[16].image = ImageIO.read(getClass().getResourceAsStream("/tiles/path7.png"));
            tile[17] = new Tile();
            tile[17].image = ImageIO.read(getClass().getResourceAsStream("/tiles/path8.png"));
            tile[18] = new Tile();
            tile[18].image = ImageIO.read(getClass().getResourceAsStream("/tiles/path9.png"));
            tile[19] = new Tile();
            tile[19].image = ImageIO.read(getClass().getResourceAsStream("/tiles/path10.png"));
            tile[20] = new Tile();
            tile[20].image = ImageIO.read(getClass().getResourceAsStream("/tiles/path11.png"));
            tile[21] = new Tile();
            tile[21].image = ImageIO.read(getClass().getResourceAsStream("/tiles/path12.png"));
            tile[22] = new Tile();
            tile[22].image = ImageIO.read(getClass().getResourceAsStream("/tiles/path13.png"));
            tile[23] = new Tile();
            tile[23].image = ImageIO.read(getClass().getResourceAsStream("/tiles/stone2.png"));
            tile[24] = new Tile();
            tile[24].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wood1.png"));
        } catch (IOException e) {
            System.out.println("Something went wrong when loading the tile PNGs");
            e.printStackTrace();
        }

    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileCode = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (screenX > 0 - gp.tileSize && screenX < gp.screenWidth + gp.tileSize
                    && screenY > 0 - gp.tileSize && screenY < gp.screenHeight + gp.tileSize) {
                g2.drawImage(tile[tileCode].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
            worldCol++;


            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }

        }

    }

}
