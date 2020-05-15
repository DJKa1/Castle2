package entities;

import ID_Lists.ID;
import Maps.Map;
import States.GameState;
import Tiles.Tile;
import entities.creatures.Creature;
import Handler.CreatureHandler;
import main_pack.Game;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public abstract class Entity {
    protected float x, y;
    protected ID id;
    protected float width, height;
    protected Rectangle2D.Double hitbox=new Rectangle2D.Double();
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


    public Point2D getCenter(){
        return new Point.Float(x+width/2,y+height/2);

    }
    public Entity(float x, float y) {
        this.x = x;
        this.y = y;
        move=new Vector2D(0,0);

    }

    public abstract void tick();
    public abstract void render(Graphics g);


    public int getPixelPosition(float v){
        return (int)(v*128);
    }

    protected int getPixelPosition(double v){
        return (int)(v*128);
    }


    //HitboxMethods-------------------------------


    public void updateHitbox(float xOffset, float yOffset) {
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
    public Creature[] checkCollision_forAll(){
        Creature []colliders=new Creature[16];
        for (Creature k : CreatureHandler.creatures) {
            int j=0;
            if (k.getHitbox().intersects(hitbox)) {
                if (j < colliders.length) {
                    colliders[j] = k;
                    j++;
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

    public float getFreeSpaceindirectionX(Rectangle2D k) {

        if (k != null) {
            if (speedX < 0) {
                if (x -( k.getX() + k.getWidth()) >= 0) {
                    return (float) -(x- (k.getX() + k.getWidth()));
                } else
                    return 0;
            } else if (speedX > 0) {
                if (k.getX() - (x + width) >= 0) {
                    return (float) (k.getX() - (x + width) - 0.00001);
                } else
                    return 0;
            }
        }
        return -1;
    }

    public float getFreeSpaceindirectionY( Rectangle2D k) {
        if (k != null) {
            if (speedY < 0) {
                if (y -( k.getY() + k.getHeight()) >= 0) {
                    return (float) -(y - (k.getY() + k.getHeight()));
                } else
                    return 0;
            } else if (speedY > 0) {
                if (k.getY() - (y+ height) >= 0) {
                    return (float) (k.getY() - (y + height)-0.00001);
                } else
                    return 0;
            }
        }
        return -1;
    }


       public float getFreeSpaceindirectionX(Rectangle2D.Double hb,Rectangle2D k) {

        if (k != null) {
            if (speedX < 0) {
                if (hb.getX() -( k.getX() + k.getWidth()) >= 0) {
                    return (float) (-(hb.getX() - (k.getX() + k.getWidth()))+0.000001);
                } else
                    return 0;
            } else if (speedX > 0) {
                if (k.getX() - (hb.getX() + hb.getWidth()) >= 0) {
                    return (float) (k.getX() - (hb.getX() + hb.getWidth())-0.000001);
                } else
                    return 0;
            }
        }
        return -1;
    }

    public float getFreeSpaceindirectionY(Rectangle2D.Double hb, Rectangle2D k) {
        if (k != null) {
            if (speedY < 0) {
                if (hb.getY() -( k.getY() + k.getHeight()) >= 0) {
                    return  (float) (-(hb.getY() - (k.getY() + k.getHeight()))+0.000001);
                } else
                    return 0;
            } else if (speedY > 0) {
                if (k.getY() - (hb.getY() + hb.getHeight()) >= 0) {
                    return (float) (k.getY() - (hb.getY() + hb.getHeight())-0.000001);
                } else
                    return 0;
            }
        }
        return -1;
    }


    public Rectangle2D.Double collisionWithTiles(Tile[] tiles,Rectangle2D hb){
        for (Tile t: tiles){
            if(t!=null){
                if (t.isSolid()) {
                    if (t.getHitbox().intersects(hb)) {
                        return t.getHitbox();
                    }
                }
            }
        }
        return null;

    }
    public Tile[] getTilesinDirection(float xSpeed, float ySpeed, Rectangle2D.Double hb) {
        Tile tile1=null, tile2=null,tile3=null,tile4=null;
        if (xSpeed != 0) {
            if (speedX < 0) {
                tile1 = GameState.map.getTilebyCords((int) Math.floor(hb.getX()), (int) Math.floor(hb.getY()),1);
                tile2 = GameState.map.getTilebyCords((int) Math.floor(hb.getX()), (int) Math.floor(hb.getY() + hb.getHeight()),1);
                tile3 = GameState.map.getTilebyCords((int) Math.floor(hb.getX()), (int) Math.floor(hb.getY()),2);
                tile4 = GameState.map.getTilebyCords((int) Math.floor(hb.getX()), (int) Math.floor(hb.getY() + hb.getHeight()),2);

            }
            if (speedX > 0) {
                tile1 = GameState.map.getTilebyCords((int) Math.floor(hb.getX() + hb.getWidth()), (int) Math.floor(hb.getY()),1);
                tile2 = GameState.map.getTilebyCords((int) Math.floor(hb.getX() + hb.getWidth()), (int) Math.floor(hb.getY() + hb.getHeight()),1);
                tile3 = GameState.map.getTilebyCords((int) Math.floor(hb.getX() + hb.getWidth()), (int) Math.floor(hb.getY()),2);
                tile4 = GameState.map.getTilebyCords((int) Math.floor(hb.getX() + hb.getWidth()), (int) Math.floor(hb.getY() + hb.getHeight()),2);
            }
            return new Tile[]{tile1,tile2,tile3,tile4};
        }

        if (ySpeed != 0) {
            if (speedY > 0) {
                tile1 = GameState.map.getTilebyCords((int) Math.floor(hb.getX()), (int) Math.floor(hb.getY() + hb.getHeight()),1);
                tile2 = GameState.map.getTilebyCords((int) Math.floor(hb.getX() + hb.getWidth()), (int) Math.floor(hb.getY() + hb.getHeight()),1);
                tile3 = GameState.map.getTilebyCords((int) Math.floor(hb.getX()), (int) Math.floor(hb.getY() + hb.getHeight()),2);
                tile4 = GameState.map.getTilebyCords((int) Math.floor(hb.getX() + hb.getWidth()), (int) Math.floor(hb.getY() + hb.getHeight()),2);
            }
            if (speedY < 0) {
                tile1 = GameState.map.getTilebyCords((int) Math.floor(hb.getX() + hb.getWidth()), (int) Math.floor(hb.getY()),1);
                tile2 = GameState.map.getTilebyCords((int) Math.floor(hb.getX()), (int) Math.floor(hb.getY()),1);
                tile3 = GameState.map.getTilebyCords((int) Math.floor(hb.getX() + hb.getWidth()), (int) Math.floor(hb.getY()),2);
                tile4 = GameState.map.getTilebyCords((int) Math.floor(hb.getX()), (int) Math.floor(hb.getY()),2);
            }
            return new Tile[]{tile1,tile2,tile3,tile4};
        }

        if(xSpeed==0&&speedY==0){
            tile1= GameState.map.getTilebyCords((int) Math.floor(hb.getX()), (int) Math.floor(hb.getY()),1);
            return new Tile[]{tile1};

        }
        return new Tile[]{null, null};



    }

    public boolean isInMap(){
        return Map.BORDER.contains(hitbox);
    }

    public boolean isTileSolid(int x, int y,int layer){
        if(x>=0&&y>=0){
            Tile t= GameState.map.getTilebyCords(x,y,layer);
            if(t!=null)
            return t.isSolid();
        }
        return false;
    }

}
