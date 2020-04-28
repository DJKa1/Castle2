package Buffs;

import ID_Lists.BuffID;
import entities.creatures.Creature;

import java.awt.image.BufferedImage;

public abstract class Buff {

    protected BuffID buffID;
    protected Creature owner;
    protected int duration;
    protected int lvl;
    protected BufferedImage image;

    public Buff(Creature owner, int duration) {
        this.owner=owner;
        this.buffID=BuffID.valueOf(this.getClass().getSimpleName());
        this.duration=duration;
        this.lvl=1;
    }

    public Buff(Creature owner,int duration,int lvl) {
        this.owner=owner;
        this.buffID=BuffID.valueOf(this.getClass().getSimpleName());
        this.duration=duration;
        this.lvl=lvl;
    }

    public void tick(){
        reduceDuration();
    }

    public boolean reduceDuration(){
        if (duration<=0){
           owner.removeBuff(this);
           return true;
        }
        else {
            duration--;
            return false;
        }
    }

    //Getter&&Setter--------------------------
    public BuffID getBuffID() {
        return buffID;
    }

    public Creature getOwner() {
        return owner;
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }


}