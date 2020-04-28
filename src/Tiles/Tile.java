package Tiles;


import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Tile {
    protected int x,y;
    protected Rectangle2D.Double hitbox;
    protected boolean isSolid;
    protected boolean interactable;
    protected BufferedImage img;

    public Tile(int x, int y, BufferedImage img, boolean isSolid){
        this.x = x;
        this.y = y;
        this.img = img;
        this.isSolid = isSolid;
        interactable=false;
    }
    public boolean isSolid() {
        return isSolid;
    }

    public void setSolid(boolean solid) {
        isSolid = solid;
    }

    public Rectangle2D.Double getHitbox() {
        return hitbox;
    }

    public void setHitbox(Rectangle2D.Double hitbox) {
        this.hitbox = hitbox;
    }

    public boolean isInteractable() {
        return interactable;
    }

    public void setInteractable(boolean interactable) {
        this.interactable = interactable;
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
