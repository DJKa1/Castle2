package entities.items;

import entities.Entity;

import java.awt.*;

public abstract class Item  extends Entity {
    protected double weight;
    protected String name;

    public Item(float x, float y,double weight) {
        super(x, y);
        this.weight=weight;
    }

    public abstract void renderInv(Graphics g, int xpos, int ypos);

    public String getName(){
        return name;
    }


    public double getWeight(){
        return weight;
    }


}
