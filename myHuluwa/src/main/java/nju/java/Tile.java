package nju.java;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

public class Tile extends Thing2D {

    public Tile(int x, int y) {
        super(x, y);

        URL loc = this.getClass().getClassLoader().getResource("tile2.png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }
}