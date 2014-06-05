package Bildanalyse.action;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import Bildanalyse.*;
import Bildanalyse.filter.IImageFilter;


public class AnyFilterAction extends AbstractAction {
    private final ImageComponent viewComponent;
    private IImageFilter _filter;

    public AnyFilterAction(ImageComponent viewComponent, IImageFilter filter, String menueName, KeyStroke key, char menueKey) {
        this.viewComponent = viewComponent;

        _filter = filter;

        putValue(NAME, menueName);
        putValue(ACCELERATOR_KEY, key);
        putValue(MNEMONIC_KEY, (int) menueKey);
    }

    public void actionPerformed(ActionEvent e) {
        viewComponent.setWorkingImage();
        BufferedImage image = this.viewComponent.getImage();

        _filter.filter(image);

        viewComponent.repaint();
    }
}

