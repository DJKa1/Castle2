package Buffs;

import entities.creatures.Creature;
import graphics.Texture;

import java.awt.*;

public class iced extends Buff {
    private float creatureSpeed,reducedSpeed;

    public iced(Creature owner, int duration, int lvl) {
        super(owner, duration, lvl);
        init();
    }
    private void init(){
        creatureSpeed=owner.getMovementRate();
        if(lvl<=10){
            reducedSpeed=creatureSpeed-(creatureSpeed/10 *lvl);
        }else {
            reducedSpeed=0;
        }
        owner.setMovementRate( reducedSpeed);
    }
    @Override
    public void render(Graphics g) {
        if (lvl>=10) {
            g.drawImage(Texture.Inventory[4][7],(int)(owner.getX()*128),(int)(owner.getY()*128),128,128,null);
        }
    }

    @Override
    public void tick() {
        if(reduceDuration()){
            owner.setMovementRate(creatureSpeed);
        }
    }
}
