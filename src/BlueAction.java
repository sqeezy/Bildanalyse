import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;


class BlueAction extends AbstractAction {
    private final ImageComponent viewComponent;


    public BlueAction(ImageComponent viewComponent) {
        this.viewComponent = viewComponent;

        putValue(NAME, "Gr�n Darstellung");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_DOWN_MASK));
        putValue(MNEMONIC_KEY, (int) 'b');
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
                Color newC = new Color(0, 0, c.getBlue());
                image.setRGB(b, h, newC.getRGB());
            }


        }

        viewComponent.repaint();
    }
}