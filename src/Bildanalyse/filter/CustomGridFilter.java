package Bildanalyse.filter;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by sqeezy on 05.06.14.
 */
public class CustomGridFilter implements IImageFilter {
    private final int[][] _grid;
    private final int _halfLength;

    public CustomGridFilter(int[][] grid){
        if(grid.length!=grid[0].length || grid.length%2!=1){
            throw new IllegalArgumentException("Grid has to be square and odd in size.");
        }
        _grid = grid;
        _halfLength = _grid.length/2;
    }

    @Override
    public void filter(BufferedImage image) {
        for(int i= _halfLength;i<image.getWidth()- _halfLength;i++){
            for(int j= _halfLength;j<image.getHeight()- _halfLength;j++){
                Color newCol = getNewColor(image,i,j);
            }
        }
    }

    private Color getNewColor(BufferedImage image, int w, int h) {
        int weightiningSum = 0;
        int red = 0;
        int blue = 0;
        int green = 0;

        int xStart = w-_halfLength;
        int yStart = h-_halfLength;

        for(int x = 0;x<_grid.length;x++){
            for(int y = 0;y<_grid.length;y++){
                Color curColor = new Color(image.getRGB(xStart+x,yStart+y));
                red += curColor.getRed()*_grid[x][y];
                green += curColor.getGreen()*_grid[x][y];
                blue += curColor.getBlue()*_grid[x][y];
                weightiningSum+=_grid[x][y];
            }
        }
        red/=weightiningSum;
        blue/=weightiningSum;
        green/=weightiningSum;

        red = between(0,red,255);
        green = between(0,green,255);
        blue = between(0,blue,255);

        return new Color(red,blue,green);
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
