package entities.creatures;

import entities.Entity;

import java.awt.*;

public abstract class Creature extends Entity {
    protected double hp;
    protected int width,height;
    protected Rectangle hitbox;
    protected double baseDmg;
    protected boolean friendly;
    protected String name;

    public Creature(float x,float y,String name){
        super(x,y);
        this.name = name;


    }

    public void createHitbox(){
        hitbox=new Rectangle((int)x,(int)y,width,height);
    }
    public void updateHitbox(){
        hitbox.setLocation((int)x,(int)y);
    }

    public boolean getFriendly(){
        return friendly;
    }

    public Rectangle getHitbox(){
        return hitbox;
    }

    public double caculateDmg(){
        return baseDmg;
    }

    public float getX() {return x;}
    public float getY() {return y;}
    public String getName() {return name;}

}
