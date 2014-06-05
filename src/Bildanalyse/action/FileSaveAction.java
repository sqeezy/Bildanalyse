package Bildanalyse.action;

import Bildanalyse.ImageComponent;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;


public class FileSaveAction extends AbstractAction {
    private final ImageComponent viewComponent;

    public FileSaveAction(ImageComponent viewComponent) {
        this.viewComponent = viewComponent;

        putValue(NAME, "Speichern");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        putValue(MNEMONIC_KEY, (int) 's');
    }

    public void actionPerformed(ActionEvent e) {
        if (viewComponent.getImage() == null) {
            JOptionPane.showMessageDialog(viewComponent, "Keine Datei zum speichern vorhanden!");
        }
        JFileChooser fileDialog = new JFileChooser();
        fileDialog.setFileFilter(new FileNameExtensionFilter("*.jpg",
                "jpg"));
        fileDialog.showOpenDialog(viewComponent);
        final File file = fileDialog.getSelectedFile();

        if (file != null) {
            try {
                ImageIO.write(viewComponent.getImage(), "jpg", file);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(viewComponent, "Fehler beim speichern der Datei!");
            }
        }
    }
}