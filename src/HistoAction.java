import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;


class HistoAction extends AbstractAction {
    private final ImageComponent viewComponent;


    public HistoAction(ImageComponent viewComponent) {
        this.viewComponent = viewComponent;

        putValue(NAME, "Farbverteilungs Darstellung");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK));
        putValue(MNEMONIC_KEY, (int) 'V');
    }

    public void actionPerformed(ActionEvent e) {
        new HistFrame(viewComponent.getImage());
    }
}