import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


class FileOpenAction extends AbstractAction {
    private final ImageComponent viewComponent;

    public FileOpenAction(ImageComponent viewComponent) {
        this.viewComponent = viewComponent;

        putValue(NAME, "�ffnen");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        putValue(MNEMONIC_KEY, (int) 'f');
    }

    public void actionPerformed(ActionEvent e) {
        JFileChooser fileDialog = new JFileChooser();
        fileDialog.setFileFilter(new FileNameExtensionFilter("*.jpg;*.gif",
                "jpg", "gif"));
        fileDialog.showOpenDialog(viewComponent);
        final File file = fileDialog.getSelectedFile();

        if (file != null) {
            new SwingWorker<BufferedImage, Void>() {
                @Override
                protected BufferedImage doInBackground() throws IOException {
                    return ImageIO.read(file);
                }

                @Override
                protected void done() {
                    try {
                        viewComponent.setImage(get());
                    } catch (Exception e) {
                    }
                }
            }.execute();
        }
    }
}