package Bildanalyse;

import Bildanalyse.action.*;
import Bildanalyse.filter.*;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;


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
        menu2.add(new JMenuItem(new AnyFilterAction(imageComponent,new GreyFilter(),"Graustufen",KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_DOWN_MASK),'g')));
        menu2.add(new JMenuItem(new AnyFilterAction(imageComponent,new SwFilter(),"B/W",KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_DOWN_MASK),'b')));
        menu2.add(new JMenuItem(new AnyFilterAction(imageComponent,new RedFilter(),"Rotstufen",KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK),'r')));
        menu2.add(new JMenuItem(new AnyFilterAction(imageComponent,new GreenFilter(),"Gruenstufen",KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK),'e')));
        menu2.add(new JMenuItem(new AnyFilterAction(imageComponent,new BlueFilter(),"Blaustufen",KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_DOWN_MASK),'u')));
        menu2.add(new JMenuItem(new AnyFilterAction(imageComponent,new AverageLowPassFilter(3),"AvgLowPass",KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_DOWN_MASK),'l')));

        int[][] filter = new int[][]{{1,1,1},{1,1,1},{1,1,1}};
        menu2.add(new JMenuItem(new AnyFilterAction(imageComponent,new CustomSquareGridFilter(filter),"CustomSquareGridFilter",KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK),'c')));

        int[][] diffFilterY = new int[][]{{-1,0,1}};
        menu2.add(new JMenuItem(new AnyFilterAction(imageComponent,new CustomGridFilter(diffFilterY,0.5),"DiffFilterY",KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_DOWN_MASK),'y')));

        int[][] diffFilterX = new int[][]{{-1},{0},{1}};
        menu2.add(new JMenuItem(new AnyFilterAction(imageComponent,new CustomGridFilter(diffFilterX,0.5),"DiffFilterX",KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK),'x')));

        IImageFilter[] diffFilter = new IImageFilter[]{new CustomGridFilter(diffFilterX,0.5),new CustomGridFilter(diffFilterY,0.5)};
        menu2.add(new JMenuItem(new AnyFilterAction(imageComponent,"DiffFilterBoth",KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_DOWN_MASK),'d',diffFilter)));

        menu2.add(new JMenuItem(new HistoAction(imageComponent)));

        menuBar.add(menu2);

        f.setJMenuBar(menuBar);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(600, 400);
        f.setVisible(true);
    }
}