package items.Weapons;
import items.Item;
import items.Quality.Primitiv;
import items.Quality.Quality;

public abstract class Weapons extends Item {
    protected float baseDamage;
    protected int delay=0;
    protected int cooldown;
    protected Quality quality;

    public Weapons(Quality quality){
        super();
        cooldown=0;
        baseDamage=1;
        if(quality==null){

            this.quality=new Primitiv();

        }else {
            this.quality=quality;
        }
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
