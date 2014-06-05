package Bildanalyse.action;

import Bildanalyse.ImageComponent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;


public class OriginalAction extends AbstractAction {
    private final ImageComponent viewComponent;


    public OriginalAction(ImageComponent viewComponent) {
        this.viewComponent = viewComponent;

        putValue(NAME, "Originale Darstellung");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_DOWN_MASK));
        putValue(MNEMONIC_KEY, (int) 'b');
    }

    public void actionPerformed(ActionEvent e) {
        viewComponent.showWorkingImage(false);
        viewComponent.repaint();
    }
}