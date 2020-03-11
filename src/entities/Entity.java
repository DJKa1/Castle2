package entities;

import java.awt.*;

public abstract class Entity {
    protected float x,y;
    protected ID id;

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
        this.x=x;
        this.y=y;

    }



    public abstract void tick();

    public abstract void render(Graphics g);

}
