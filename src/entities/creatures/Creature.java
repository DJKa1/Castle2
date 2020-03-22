package entities.creatures;

import Maps.Map;
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

    public boolean checkCollision_ifOneOf(Rectangle2D.Double hitbox, ID partner) {
        for (Creature k : CreatureHandler.creatures) {
            if(k.getId()==partner) {
                if (k.getHitbox().intersects(hitbox)) {
                    return true;

                }
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

    public Rectangle2D.Double getHitbox(){ return hitbox; }

    public double caculateDmg(){
        return baseDmg;
    }

    public  abstract  void drawHitbox(Graphics g);




}
