package Buffs;

import entities.creatures.Creature;
import main_pack.Game;

public class Poison extends Buff{
    private double drainrate;
    private double tickdmg;

    public Poison(Creature owner, int duration) {
        super(owner, duration);
        init();
    }
    public Poison(Creature owner, int duration, int lvl) {
        super(owner, duration, lvl);
        init();
    }

    public void init(){
        drainrate=(double)lvl/ Game.TICKRATE;
        tickdmg=(owner.getMaxHp()/100)*drainrate;
    }

    @Override
    public void tick() {
        reduceDuration();
        owner.hitbyEffect(tickdmg);
    }
}
