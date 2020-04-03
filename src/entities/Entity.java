package entities;

import Maps.Map;
import States.GameState;
import Tiles.Tile;
import entities.creatures.Creature;
import main_pack.CreatureHandler;
import main_pack.Game;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.lang.reflect.Array;

public abstract class Entity {
    protected float x, y;
    protected ID id;
    protected float width, height;
    protected Rectangle2D.Double hitbox;
    protected Vector2D move;


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

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }
    public int boolToInt(boolean b) {
        return b ? 1 : 0;
    }

    public Entity(float x, float y) {
        this.x = x;
        this.y = y;

    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public  abstract  void drawHitbox(Graphics g);


    //HitboxMethods-------------------------------
    public int getPixelPosition(float v) {
        v *= Game.UNIT_SCALE;
        return (int) v;
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


    //Collision------------------------------------------

    public Creature[] checkCollision_forAll( ID[] partner) {
        Creature []colliders=new Creature[16];
        for (Creature k : CreatureHandler.creatures) {
            int j=0;
            for ( int i=0 ; i<partner.length;i++) {
                if (k.getId() == partner[i]) {
                    if (k.getHitbox().intersects(hitbox)) {
                        if (j < colliders.length) {
                            colliders[j] = k;
                            j++;
                        }
                    }
                }
            }
        }
        return colliders;
    }

    public Creature checkCollision_ifOneOf( ID[] partner) {
        for (Creature k : CreatureHandler.creatures) {
            for ( int i=0 ; i<partner.length;i++){
                if(k.getId()==partner[i]) {
                    if (k.getHitbox().intersects(hitbox)) {
                        return k;

                    }
                }
            }
        }
        return null;
    }

    public float getFreeSpaceindirectionX(Rectangle2D.Double k) {

        if (k != null) {
            if (speedX < 0) {
                if (x -( k.getX() + k.getWidth()) >= 0) {
                    return (float) -(x - (k.getX() + k.getWidth()));
                } else
                    return 0;
            } else if (speedX > 0) {
                if (k.getX() - (x + width) >= 0) {
                    return (float) (k.getX() - (x + width) - 0.0001);
                } else
                    return 0;
            }
        }
        return -1;
    }

    public float getFreeSpaceindirectionY(Rectangle2D.Double k) {
        if (k != null) {
            if (speedY < 0) {
                if (y -( k.getY() + k.getHeight()) >= 0) {
                    return (float) -(y - (k.getY() + k.getHeight()));
                } else
                    return 0;
            } else if (speedY > 0) {
                if (k.getY() - (y + height) >= 0) {
                    return (float) (k.getY() - (y + height)-0.00001);
                } else
                    return 0;
            }
        }
        return -1;
    }

    public Rectangle2D.Double collisionWithTiles(Tile[] tiles){
        for (Tile t: tiles){
            if(t!=null){
                if (t.isSolid()) {
                    if (t.getHitbox().intersects(hitbox)) {
                        return t.getHitbox();
                    }
                }
            }
        }
        return null;

    }
    public Tile[] getTilesinDirection(float xSpeed, float ySpeed) {
        Tile tile1=null, tile2=null;
        if (xSpeed != 0) {
            if (speedX < 0) {
                tile1 = GameState.map.getTilebyCords((int) Math.floor(hitbox.getX()), (int) Math.floor(hitbox.getY()));
                tile2 = GameState.map.getTilebyCords((int) Math.floor(hitbox.getX()), (int) Math.floor(hitbox.getY() + hitbox.getHeight()));
            }
            if (speedX > 0) {
                tile1 = GameState.map.getTilebyCords((int) Math.floor(hitbox.getX() + hitbox.getWidth()), (int) Math.floor(hitbox.getY()));
                tile2 = GameState.map.getTilebyCords((int) Math.floor(hitbox.getX() + hitbox.getWidth()), (int) Math.floor(hitbox.getY() + hitbox.getHeight()));
            }
        }

        if (ySpeed != 0) {
            if (speedY > 0) {
                tile1 = GameState.map.getTilebyCords((int) Math.floor(hitbox.getX()), (int) Math.floor(hitbox.getY() + getHeight()));
                tile2 = GameState.map.getTilebyCords((int) Math.floor(hitbox.getX() + getWidth()), (int) Math.floor(hitbox.getY() + getHeight()));
            }
            if (speedY < 0) {
                tile1 = GameState.map.getTilebyCords((int) Math.floor(hitbox.getX() + hitbox.getWidth()), (int) Math.floor(hitbox.getY()));
                tile2 = GameState.map.getTilebyCords((int) Math.floor(hitbox.getX()), (int) Math.floor(hitbox.getY()));
            }
        }

        return new Tile[]{tile1,tile2};
    }

    public boolean isInMap(){
        if (Map.BORDER.contains(hitbox)){
            return true;
        }
        return false;
    }

    public boolean isTileSolid(int x, int y){
        if(x>=0&&y>=0){
            Tile t= GameState.map.getTilebyCords(x,y);
            if(t!=null)
            return t.isSolid();
        }
        return false;
    }

}
