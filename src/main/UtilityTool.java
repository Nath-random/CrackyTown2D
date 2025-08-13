package main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UtilityTool {

    // für bessere performance werden Bilder vorher skaliert (nur einmal pro Bild)
    public BufferedImage scaleImage (BufferedImage original, int width, int height) {
        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        Graphics2D g2 = scaledImage.createGraphics(); // g2 ändert das Buffered Image
        g2.drawImage(original, 0, 0, width, height, null); // zeichnet das skalierte Bild auf das BufferedImage
        g2.dispose();
        return scaledImage;
    }

}
