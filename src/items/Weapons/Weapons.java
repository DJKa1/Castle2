package items.Weapons;
import entities.creatures.Creature;
import items.Item;
import items.Quality.Primitiv;
import items.Quality.Quality;

public abstract class Weapons extends Item {
    protected float baseDamage;
    protected int delay=0;
    protected int cooldown;
    protected Creature user;


    public Weapons(Quality quality){
        super();
        if(quality==null){
            this.quality=new Primitiv();
        }else {
            this.quality=quality;
        }
        assert quality != null;
        quality.rollDmg();
        cooldown=0;
        baseDamage=1;

        attributes.add(quality.getId().toString());
        attributes.add(Math.round(quality.getDmg()*100)+"%");

    }


    @Override
    public void tick() {
        if (delay < cooldown && delay != 0) {
            delay++;
        } else {
            delay = 0;
        }
    }

    @Override
    public void use(Creature k){
        user=k;
    }

    public int getDelay() {
        return delay;
    }

    public int getCooldown() {
        return cooldown;
    }

    public float getDmg(){
        return baseDamage*quality.getDmg();
    }

    public float getBaseDamage() {
        return baseDamage;
    }

    public Creature getUser(){
        return user;
    }

    public Quality getQuality() {
        return quality;
    }
}
