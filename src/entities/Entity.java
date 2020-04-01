package entities;

import States.GameState;
import Tiles.Tile;
import main_pack.Game;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public abstract class Entity {
    protected float x, y;
    protected ID id;
    protected double width, height;
    protected Rectangle2D.Double hitbox;

    protected float speedY, speedX;

    public float getSpeedY() {
        return speedY;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    public float getSpeedX() {
        return speedX;
    }

    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public ID getId() {
        return id;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Entity(float x, float y) {
        this.x = x;
        this.y = y;

    }

    public abstract void tick();

    public abstract void render(Graphics g);


    //HitboxMethods-------------------------------

    public int getPixelPosition(double v) {
        v *= Game.UNIT_SCALE;
        return (int) v;
    }

    public void createHitbox() {
        hitbox = new Rectangle2D.Double(x, y, width, height);
    }

    public void updateHitbox(double xOffset, double yOffset) {
        hitbox.setRect(x + xOffset, y + yOffset, width, height);

    }

    public void normalizeHitbox() {
        hitbox.setRect(x, y, width, height);
    }

    public Rectangle2D.Double getHitbox() {
        return hitbox;
    }


    //Collision----------------------------------------
    public boolean collisionWithNextTile(float xSpeed, float ySpeed) {
        Tile tile1, tile2;
        if (xSpeed != 0) {
            if (speedX < 0) {
                tile1 = GameState.map.getTilebyCords((int) Math.floor(hitbox.getX()), (int) Math.floor(hitbox.getY()));
                tile2 = GameState.map.getTilebyCords((int) Math.floor(hitbox.getX()), (int) Math.floor(hitbox.getY() + hitbox.getHeight()));
                if (!(tile1==null||tile2==null)) {
                    if (!tile1.isSolid() && !tile2.isSolid()) {
                        return false;
                    }
                } else return true;


            }

            if (speedX > 0) {
                tile1 = GameState.map.getTilebyCords((int) Math.floor(hitbox.getX() + hitbox.getWidth()), (int) Math.floor(hitbox.getY()));
                tile2 = GameState.map.getTilebyCords((int) Math.floor(hitbox.getX() + hitbox.getWidth()), (int) Math.floor(hitbox.getY() + hitbox.getHeight()));
                if (!(tile1==null||tile2==null)) {
                    if (!tile1.isSolid() && !tile2.isSolid()) {
                        return false;
                    }
                } else return true;


            }
        }

        if (ySpeed != 0) {
            if (speedY > 0) {
                tile1 = GameState.map.getTilebyCords((int) Math.floor(hitbox.getX()), (int) Math.floor(hitbox.getY() + getHeight()));
                tile2 = GameState.map.getTilebyCords((int) Math.floor(hitbox.getX() + getWidth()), (int) Math.floor(hitbox.getY() + getHeight()));
                if (!(tile1==null||tile2==null)) {
                    if (!tile1.isSolid() && !tile2.isSolid()) {
                        return false;
                    }
                } else return true;


            }

            if (speedY < 0) {
                tile1 = GameState.map.getTilebyCords((int) Math.floor(hitbox.getX() + hitbox.getWidth()), (int) Math.floor(hitbox.getY()));
                tile2 = GameState.map.getTilebyCords((int) Math.floor(hitbox.getX()), (int) Math.floor(hitbox.getY()));
                if (!(tile1==null||tile2==null)) {
                    if (!tile1.isSolid() && !tile2.isSolid()) {
                        return false;
                    }
                } else return true;


            }
        }
        return false;
    }



    public boolean isTileSolid(int x, int y){
        if(x>=0&&y>=0){
            return GameState.map.getTilebyCords(x,y).isSolid();
        }
        return false;
    }

}
