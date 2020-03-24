package entities.creatures;

import Maps.Map;
import States.GameState;
import Tiles.Tile;
import entities.Entity;
import entities.ID;
import main_pack.CreatureHandler;
import main_pack.Game;

import java.awt.*;
import java.awt.geom.Rectangle2D;


public abstract class Creature extends Entity {
    protected double hp;
    protected double width,height;
    protected double baseDmg;
    protected Rectangle2D.Double hitbox;
    protected double movementRate;

    public Creature(float x,float y){
        super(x,y);
    }



    public Creature[] checkCollision_forAll(Rectangle2D.Double hitbox, ID partner) {
        Creature []colliders=null;
        int i=0;
        for (Creature k : CreatureHandler.creatures) {
            if(k.getId()==partner) {
                if (k.getHitbox().intersects(hitbox)) {
                    colliders[i]=k;
                    i++;
                }
            }
        }
        return colliders;

    }


    public Creature checkCollision_ifOneOf(Rectangle2D.Double hitbox, ID[] partner) {
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

    public boolean collisionWithNextTile(Rectangle2D.Double hitbox,float xSpeed, float ySpeed) {
        Tile tile1,tile2;
        if (xSpeed!=0){
            if(speedX<0) {
                tile1=GameState.map.getTilebyCords((int)Math.floor(hitbox.getX()),(int)Math.floor(hitbox.getY()));
                tile2 = GameState.map.getTilebyCords((int) Math.floor(hitbox.getX()), (int) Math.floor(hitbox.getY() + hitbox.getHeight()));
                if(!tile1.isSolid()&&!tile2.isSolid()) {
                    return false;

                }else return true;


            }

            if(speedX>0) {
                tile1=GameState.map.getTilebyCords((int)Math.floor(hitbox.getX()+hitbox.getWidth()),(int)Math.floor(hitbox.getY()));
                tile2 = GameState.map.getTilebyCords((int) Math.floor(hitbox.getX()+hitbox.getWidth()), (int) Math.floor(hitbox.getY() + hitbox.getHeight()));
                if(!tile1.isSolid()&&!tile2.isSolid()) {
                    return false;

                }else return true;


            }
        }

        if (ySpeed!=0){
            if(speedY>0) {
                tile1=GameState.map.getTilebyCords((int)Math.floor(hitbox.getX()),(int)Math.floor(hitbox.getY()+getHeight()));
                tile2 = GameState.map.getTilebyCords((int) Math.floor(hitbox.getX()+getWidth()), (int) Math.floor(hitbox.getY()+getHeight()));
                if(!tile1.isSolid()&&!tile2.isSolid()) {
                    return false;

                }else return true;


            }

            if(speedY<0) {
                tile1=GameState.map.getTilebyCords((int)Math.floor(hitbox.getX()+hitbox.getWidth()),(int)Math.floor(hitbox.getY()));
                tile2 = GameState.map.getTilebyCords((int) Math.floor(hitbox.getX()), (int) Math.floor(hitbox.getY()));
                if(!tile1.isSolid()&&!tile2.isSolid()) {
                    return false;

                }else return true;


            }
        }



        return false;
    }

    public boolean isInMap(Rectangle2D.Double hitbox){
        if (Map.BORDER.contains(hitbox)){
            return true;
        }
        return false;
    }


    public int getPixelPosition(double v){
        v*= Game.UNIT_SCALE;
        return (int)v;
    }

    public void createHitbox(){
        hitbox=new Rectangle2D.Double(x,y,width,height);
    }
    public void updateHitbox(double xOffset,double yOffset){
        hitbox.setRect(x+xOffset,y+yOffset,width,height);

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

    public void normalizeHitbox(){
        hitbox.setRect(x,y,width,height);
    }

    public Rectangle2D.Double getHitbox(){ return hitbox; }

    public double caculateDmg(){
        return baseDmg;
    }

    public  abstract  void drawHitbox(Graphics g);




}
