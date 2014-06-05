import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


class ImageComponent extends JComponent {
    private static final long serialVersionUID = 8055865896136562197L;

    private BufferedImage image;
    private BufferedImage workingImage;
    private boolean bWorkingImage = false;

    public void showWorkingImage(boolean value) {
        this.bWorkingImage = value;
    }


    public void setImage(BufferedImage image) {
        if (!bWorkingImage) {
            this.image = image;
            setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
        } else {
            this.workingImage = image;
            setPreferredSize(new Dimension(workingImage.getWidth(), workingImage.getHeight()));
        }
        repaint();
        invalidate();
    }

    public static BufferedImage copyImage(BufferedImage source) {
        BufferedImage b = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
        Graphics g = b.getGraphics();
        g.drawImage(source, 0, 0, null);
        g.dispose();
        return b;
    }


    public void setWorkingImage() {
        workingImage = copyImage(image);
        bWorkingImage = true;
    }


    /**
     * Die Methode liefert das aktuelle Bild als BufferedImage zur�ck
     */
    public BufferedImage getImage() {
        if (this.bWorkingImage)
            return workingImage;
        else
            return image;
    }


    @Override
    protected void paintComponent(Graphics g) {
        if (!bWorkingImage) {
            if (image != null) {
                g.drawImage(image, 0, 0, this);
            }
        } else {
            if (workingImage != null) {
                g.drawImage(workingImage, 0, 0, this);
            }
        }


    }
}