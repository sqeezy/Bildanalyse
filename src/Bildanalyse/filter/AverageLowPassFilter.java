package Bildanalyse.filter;

import Bildanalyse.ImageComponent;

import java.awt.image.BufferedImage;

/**
 * Created by sqeezy on 05.06.14.
 */
public class AverageLowPassFilter implements IImageFilter {
    @Override
    public void filter(BufferedImage image) {
        BufferedImage copyOrig = ImageComponent.copyImage(image);


    }
}
