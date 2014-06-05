import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;


class RedAction extends AbstractAction {
    private final ImageComponent viewComponent;


    public RedAction(ImageComponent viewComponent) {
        this.viewComponent = viewComponent;

        putValue(NAME, "Rot Darstellung");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
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
                Color newC = new Color(c.getRed(), 0, 0);
                image.setRGB(b, h, newC.getRGB());
            }


        }

        viewComponent.repaint();
    }
}