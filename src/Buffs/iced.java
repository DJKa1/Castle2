package Buffs;

import entities.creatures.Creature;

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
    public void tick() {
        if(reduceDuration()){
            owner.setMovementRate(creatureSpeed);
        }
    }
}
