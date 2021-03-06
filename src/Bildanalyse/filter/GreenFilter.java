package Bildanalyse.filter;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by sqeezy on 05.06.14.
 */
public class GreenFilter implements IImageFilter {
    @Override
    public void filter(BufferedImage image) {
        int hoehe = image.getHeight();
        int breite = image.getWidth();

        for (int h = 0; h < hoehe; h++) {
            for (int b = 0; b < breite; b++) {
                int rgb = image.getRGB(b, h);
                Color c = new Color(rgb);
                Color newC = new Color(0, c.getGreen(), 0);
                image.setRGB(b, h, newC.getRGB());
            }
        }
    }
}
