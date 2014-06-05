package Bildanalyse.filter;

import Bildanalyse.ImageComponent;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by sqeezy on 05.06.14.
 */
public class AverageLowPassFilter implements IImageFilter {
    private final int _rasterSize;

    public AverageLowPassFilter(int rasterSize) {
        _rasterSize = rasterSize;
    }

    @Override
    public void filter(BufferedImage image) {
        BufferedImage copyImage = ImageComponent.copyImage(image);

        for (int x = _rasterSize / 2; x < image.getWidth() - _rasterSize / 2; x++) {
            for (int y = _rasterSize / 2; y < image.getHeight() - _rasterSize / 2; y++) {
                Color avgColor = getAverageColor(copyImage, x, y);
                image.setRGB(x,y,avgColor.getRGB());
            }
        }
    }

    private Color getAverageColor(BufferedImage image, int x, int y) {
        int countPixel = 0;
        int red = 0;
        int green = 0;
        int blue = 0;

        for (int i = x - _rasterSize / 2; i <= x + _rasterSize / 2; i++) {
            for (int j = y - _rasterSize / 2; j <= y + _rasterSize / 2; j++) {
                Color curColor = new Color(image.getRGB(i, j));
                red += curColor.getRed();
                green += curColor.getGreen();
                blue += curColor.getBlue();
                countPixel++;
            }

        }

        red/=countPixel;
        blue/=countPixel;
        green/=countPixel;

        return new Color(red,blue,green);
    }
}
