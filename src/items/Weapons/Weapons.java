package items.Weapons;
import Inventory.Inventory;
import items.Item;
import items.Quality.Primitiv;
import items.Quality.Quality;

public abstract class Weapons extends Item {
    protected float baseDamage;
    protected int delay=0;
    protected int cooldown;
    protected Quality quality;


    public Weapons() {
        super();
        cooldown=0;
        this.quality=new Primitiv();
        initValues();
    }


    protected void initValues(){
        baseDamage=baseDamage*quality.getDmg();
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

    public float getBaseDmg(){
        return baseDamage;
    }

    public float getBaseDamage() {
        return baseDamage;
    }

    public Quality getQuality() {
        return quality;
    }
}
