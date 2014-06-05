import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;


class SwAction extends AbstractAction {
    private final ImageComponent viewComponent;


    public SwAction(ImageComponent viewComponent) {
        this.viewComponent = viewComponent;

        putValue(NAME, "S/W Darstellung");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        putValue(MNEMONIC_KEY, (int) 's');
    }

    public void actionPerformed(ActionEvent e) {
        viewComponent.setWorkingImage();
        BufferedImage image = this.viewComponent.getImage();

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

        viewComponent.repaint();
    }
}