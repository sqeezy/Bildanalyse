import javax.swing.*;


public class ImageViewer {
    public static void main(String[] args) {
        JFrame f = new JFrame("Bildbetrachter");

        ImageComponent imageComponent = new ImageComponent();
        f.add(new JScrollPane(imageComponent));
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Datei");
        menu.setMnemonic('D');
        menu.add(new JMenuItem(new FileOpenAction(imageComponent)));
        menu.add(new JMenuItem(new FileSaveAction(imageComponent)));
        menuBar.add(menu);
        JMenu menu2 = new JMenu("Operationen");
        menu2.setMnemonic('G');
        menu2.add(new JMenuItem(new OriginalAction(imageComponent)));
        menu2.add(new JMenuItem(new GreyAction(imageComponent)));
        menu2.add(new JMenuItem(new SwAction(imageComponent)));
        menu2.add(new JMenuItem(new RedAction(imageComponent)));
        menu2.add(new JMenuItem(new GreenAction(imageComponent)));
        menu2.add(new JMenuItem(new BlueAction(imageComponent)));
        menu2.add(new JMenuItem(new HistoAction(imageComponent)));

        menuBar.add(menu2);

        f.setJMenuBar(menuBar);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(600, 400);
        f.setVisible(true);
    }
}