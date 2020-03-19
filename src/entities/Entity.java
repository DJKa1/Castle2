package entities;

import entities.creatures.Creature;
import main_pack.CreatureHandler;

import java.awt.*;

import static main_pack.Game.UNIT_SCALE;

public abstract class Entity {
    protected float x,y;
    protected ID id;

    protected float speedY,speedX;

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

    public Entity(float x, float y){
        this.x=x*UNIT_SCALE;
        this.y=y*UNIT_SCALE;

    }






    public abstract void tick();

    public abstract void render(Graphics g);

}
