package items.Weapons;
import items.Item;
import items.Quality.Primitiv;
import items.Quality.Quality;

public abstract class Weapons extends Item {
    protected float baseDamage;
    protected int delay=0;
    protected int cooldown;

    public Weapons(Quality quality){
        super();
        if(quality==null){
            this.quality=new Primitiv();
        }else {
            this.quality=quality;
        }
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

    public Quality getQuality() {
        return quality;
    }
}
