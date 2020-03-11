package main_pack;

import entities.Entity;

import java.awt.*;
import java.util.LinkedList;

public class EntityHandler {

    public static LinkedList<Entity> entities;

    public EntityHandler(){
        entities= new LinkedList<>();

    }
    public void tick() {
        for (int i = 0; i < entities.size(); i++) {
            Entity tempObject = entities.get(i);
            tempObject.tick();

        }
    }

    public void render(Graphics g) {
        for (int i = 0; i < entities.size(); i++) {
            Entity tempObject = entities.get(i);
            tempObject.render(g);
        }

    }

    public void addObject(Entity object) {
        this.entities.add(object);
    }

    public void removeObject(Entity object) {
        this.entities.remove(object);
    }
}
