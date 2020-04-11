package Handler;
import entities.creatures.Creature;
import main_pack.KeyboardInput;

import java.awt.*;
import java.util.LinkedList;

public class CreatureHandler {

    public static LinkedList<Creature> creatures;

    public CreatureHandler(){
        creatures= new LinkedList<>();

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
            if (KeyboardInput.f3G){
                tempObject.renderHitbox(g);
            }
        }
    }
    public void addObject(Creature object) {
        this.creatures.add(object);
    }
    public void removeObject(Creature object) {
        this.creatures.remove(object);
    }
    public int getCreatureCount(){
        return creatures.size();
    }
}
