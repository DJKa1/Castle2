package Tiles;


import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {
    private int x,y;
    private Rectangle hitbox;
    private String tile;
    private boolean isSolid;
    private BufferedImage img;

    public Tile(int x, int y,BufferedImage img, boolean isSolid){
        this.x = x;
        this.y = y;
        this.img = Texture.sprite[16];
        this.isSolid = isSolid;
    }
    public Tile(int x, int y,BufferedImage img, Rectangle hitbox) {
        this.x = x;
        this.y = y;
        this.img = img;
        this.isSolid = true;
    }

    public boolean isSolid() {
        return isSolid;
    }

    public void tick() {

    }

    public void render(Graphics g, int x, int y) {
        g.drawImage(img,x,y,null);
    }

    public BufferedImage getImg() {
        return img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
