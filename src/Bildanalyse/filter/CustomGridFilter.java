package Bildanalyse.filter;

import Bildanalyse.ImageComponent;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by sqeezy on 06.06.14.
 */
public class CustomGridFilter implements IImageFilter {
    private final int[][] _grid;
    private final int _halfGridWidth;
    private final int _halfGridHeight;
    private final double _factor;

    public CustomGridFilter(int[][] grid){
        this(grid,1.0);
    }

    public CustomGridFilter(int[][] grid,double factor){
        if(grid.length<1 || grid[0].length<1){
            throw new IllegalArgumentException("The size of rows and columns has to be greater then zero.");
        }
        _grid = grid;
        _halfGridWidth = grid.length/2;
        _halfGridHeight = grid[0].length/2;
        _factor = factor;
    }
    @Override
    public void filter(BufferedImage image) {
        BufferedImage imageCopy = ImageComponent.copyImage(image);
        for(int i= _halfGridWidth;i<image.getWidth()- _halfGridWidth;i++){
            for(int j= _halfGridHeight;j<image.getHeight()- _halfGridHeight;j++){
                Color newCol = getNewColor(imageCopy,i,j);
                image.setRGB(i,j,newCol.getRGB());
            }
        }

    }

    private Color getNewColor(BufferedImage image, int w, int h) {
        int weightiningSum = 0;
        int red = 0;
        int blue = 0;
        int green = 0;

        int xStart = w- _halfGridWidth;
        int yStart = h- _halfGridHeight;

        for(int x = 0;x<_grid.length;x++){
            for(int y = 0;y<_grid[0].length;y++){
                Color curColor = new Color(image.getRGB(xStart+x,yStart+y));
                red += curColor.getRed()*_grid[x][y];
                green += curColor.getGreen()*_grid[x][y];
                blue += curColor.getBlue()*_grid[x][y];
            }
        }
        red*=_factor;
        blue*=_factor;
        green*=_factor;

        red = between(0,red,255);
        green = between(0,green,255);
        blue = between(0,blue,255);

        return new Color(red,green,blue);
    }

    private int between(int min, int orig, int max) {
        if(orig<min){
            return min;
        }
        if(orig>max){
            return max;
        }
        return orig;
    }
}
