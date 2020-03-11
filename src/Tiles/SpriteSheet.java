package Tiles;

import java.awt.image.BufferedImage;

public class SpriteSheet {

    private BufferedImage image;

    public SpriteSheet(BufferedImage image) {
        this.image = image;
    }
    public BufferedImage grabImage(int col, int rol, int Widht, int Heigth) {
        BufferedImage img = image.getSubimage((col*Widht)-Widht, (rol*Heigth)-Heigth, Widht, Heigth);
        return img;
    }

}
