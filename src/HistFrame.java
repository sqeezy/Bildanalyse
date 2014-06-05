import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class HistFrame extends JFrame {

    private BufferedImage _img = null;

    public HistFrame(BufferedImage image) {
        super("Farbverteilung");
        _img = image;
        setSize(new Dimension(500, 255 * 2));
        this.setLayout(new GridLayout(0, 3));
        int[][] colorDist = getColorDistribution();
        add(new HistoComponent(colorDist[0], Color.RED));
        add(new HistoComponent(colorDist[1], Color.GREEN));
        add(new HistoComponent(colorDist[2], Color.BLUE));
        setVisible(true);
    }

    private void createHistogramms(Graphics g) {
        int[][] colorDist = getColorDistribution();
    }

    private int[][] getColorDistribution() {
        int[][] colorDist = new int[3][256];
        for (int b = 0; b < _img.getWidth(); b++) {
            for (int h = 0; h < _img.getHeight(); h++) {
                Color c = new Color(_img.getRGB(b, h));
                colorDist[0][c.getRed()] += 1;
                colorDist[1][c.getGreen()] += 1;
                colorDist[2][c.getBlue()] += 1;
            }
        }
        return colorDist;
    }

    private class HistoComponent extends JComponent {
        private int[] _dist;
        private Color _graphColor;

        public HistoComponent(int[] distributiones, Color graphColor) {
            _dist = distributiones;
            _graphColor = graphColor;
        }

        @Override
        public void paintComponent(Graphics g) {
            g.setColor(_graphColor);
            int pixel = _img.getHeight() * _img.getWidth();

            for (int colorValue = 0; colorValue < _dist.length; colorValue++) {
                double height = _dist[colorValue] * 80 * this.getHeight() / pixel;
                height *= 0.8;
                g.fillRect(colorValue, this.getHeight() - (int) height, 1, (int) height);
            }
        }
    }
}
