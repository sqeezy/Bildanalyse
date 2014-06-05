import java.awt.image.BufferedImage;
import java.awt.Color;

/**
 * Created by sqeezy on 05.06.14.
 */
public class SwFilter implements IImageFilter{
    @Override
    public void filter(BufferedImage image) {

        int hoehe = image.getHeight();
        int breite = image.getWidth();

        for (int h = 0; h < hoehe; h++) {
            for (int b = 0; b < breite; b++) {
                int rgb = image.getRGB(b, h);
                Color c = new Color(rgb);
                int grauwert = (c.getRed() + c.getGreen() + c.getBlue()) / 3;
                Color newC;
                if (grauwert > 150) {
                    newC = new Color(255, 255, 255);
                } else {
                    newC = new Color(0, 0, 0);
                }
                image.setRGB(b, h, newC.getRGB());
            }


        }
    }
}
