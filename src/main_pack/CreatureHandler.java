package main_pack;
import entities.creatures.Creature;
import entities.creatures.GreenSlime;

import java.awt.*;
import java.util.LinkedList;

public class CreatureHandler {

    public static LinkedList<Creature> creatures;

    public CreatureHandler(){
        creatures= new LinkedList<>();
        creatures.add(new GreenSlime(100,100,"greenSlime"));
        creatures.add(new GreenSlime(200,100,"greenSlime"));

    }
    public void tick() {
        for (int i = 0; i < creatures.size(); i++) {
            Creature tempObject = creatures.get(i);
            tempObject.tick();

        }
    }

    public void render(Graphics g) {
        for (int i = 0; i < creatures.size(); i++) {
            Creature tempObject = creatures.get(i);
            tempObject.render(g);
        }

    }

    public void addObject(Creature object) {
        this.creatures.add(object);
    }

    public void removeObject(Creature object) {
        this.creatures.remove(object);
    }
}
