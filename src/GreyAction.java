import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;


class GreyAction extends AbstractAction {
    private final ImageComponent viewComponent;


    public GreyAction(ImageComponent viewComponent) {
        this.viewComponent = viewComponent;

        putValue(NAME, "Grau Darstellung");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_DOWN_MASK));
        putValue(MNEMONIC_KEY, (int) 'g');
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
                Color newC = new Color(grauwert, grauwert, grauwert);
                image.setRGB(b, h, newC.getRGB());
            }


        }

        viewComponent.repaint();
    }
}