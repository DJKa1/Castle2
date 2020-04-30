package Handler;

import Effects.Effect;

import java.awt.*;
import java.util.LinkedList;

public class Effectshandler {

    public static LinkedList<Effect> effects;

    public Effectshandler(){
        effects = new LinkedList<>();

    }
    public void tick() {
        for (int i = 0; i < effects.size(); i++) {
            Effect tempObject = effects.get(i);
            tempObject.tick();

        }
    }

    public void render(Graphics g) {
        for (int i = 0; i < effects.size(); i++) {
            Effect tempObject = effects.get(i);
            tempObject.setG(g);
            tempObject.render();
        }
    }
    public void addObject(Effect object) {
        effects.add(object);
    }
    public void removeObject(Effect object) {
        effects.remove(object);
    }
    public int getCreatureCount(){
        return effects.size();
    }
}
