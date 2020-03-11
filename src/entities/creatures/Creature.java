package entities.creatures;

import entities.Entity;
import entities.ID;
import main_pack.CreatureHandler;

import java.awt.*;


public abstract class Creature extends Entity {
    protected double hp;
    protected int width,height;
    protected Rectangle hitbox;
    protected double baseDmg;

    public Creature(float x,float y){
        super(x,y);
    }

    public Creature[] checkCollision_forAll(Rectangle hitbox, ID partner) {
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

    public boolean checkCollision_ifOneOf(Rectangle hitbox, ID partner) {
        for (Creature k : CreatureHandler.creatures) {
            if(k.getId()==partner) {
                if (k.getHitbox().intersects(hitbox)) {
                    return true;

                }
            }
        }
        return false;

    }

    public void createHitbox(){
        hitbox=new Rectangle((int)x,(int)y,width,height);
    }
    public void updateHitbox(int xOffset,int yOffset){
        hitbox.setLocation((int)x+xOffset,(int)y+yOffset);
    }

    public Rectangle getHitbox(){
        return hitbox;
    }

    public double caculateDmg(){
        return baseDmg;
    }



}
