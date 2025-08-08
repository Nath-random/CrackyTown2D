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
    Tile[] tile;
    int[][] mapTileNum;

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[10];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];

        getTileImage();
        loadMap("/maps/map1.txt");
    }

    public void loadMap(String mapFilePath) {
        try {
//            InputStream is = new FileInputStream("res/maps/map1.txt"); // geht auch
            InputStream is = getClass().getResourceAsStream(mapFilePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
                String line = br.readLine();

                while (col < gp.maxScreenCol) {
                    String[] numbers = line.split(" "); //length should be == gp.maxScreenCol

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num; //[col][row] macht mehr Sinn als [row][col], weil dann die erste Koordinate x ist.
                    col++;
                }

                if (col == gp.maxScreenCol) {
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
        } catch (IOException e) {
            System.out.println("Something went wrong when loading the tile PNGs");
            e.printStackTrace();
        }

    }

    public void draw(Graphics2D g2) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;


        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {

            int tileCode = mapTileNum[col][row];
            g2.drawImage(tile[tileCode].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;

            if (col == gp.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }

        }

    }

}
