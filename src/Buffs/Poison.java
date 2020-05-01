package Buffs;

import Effects.DmgIndicator;
import entities.creatures.Creature;
import graphics.Texture;
import main_pack.Game;

public class Poison extends Buff{
    private float drainrate;
    private float tickdmg;
    private int counter=0;

    public Poison(Creature owner, int duration) {
        super(owner, duration);
        init();
    }
    public Poison(Creature owner, int duration, int lvl) {
        super(owner, duration, lvl);
        init();
    }

    private void init(){
        drainrate=(float)lvl/ Game.TICKRATE;
        tickdmg=(owner.getMaxHp()/100)*drainrate;
        image= Texture.sprite[4];
    }

    private float round(float i){
        i*=100;
        Math.round(i);
        i/=100;
        return i;
    }

    @Override
    public void tick() {
        reduceDuration();
        if(counter==Game.TICKRATE){
            owner.hit(tickdmg*Game.TICKRATE,null);
            counter=0;
        }
        counter++;

    }

}
