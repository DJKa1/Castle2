package entities.creatures;

import Maps.Map;
import entities.Entity;
import entities.ID;
import main_pack.CreatureHandler;

import java.awt.*;
import java.awt.geom.Rectangle2D;


public abstract class Creature extends Entity {
    protected double hp;
    protected double baseDmg;
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

    public boolean isInMap(){
        if (Map.BORDER.contains(hitbox)){
            return true;
        }
        return false;
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



    public double caculateDmg(){
        return baseDmg;
    }



    public  abstract  void drawHitbox(Graphics g);








}
