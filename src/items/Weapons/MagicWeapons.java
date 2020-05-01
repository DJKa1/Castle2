package items.Weapons;
import entities.creatures.Creature;
import items.Quality.Quality;
public abstract class MagicWeapons extends Weapons {
    protected int manacost;

    public MagicWeapons(Quality quality) {
        super(quality);
    }

    @Override
    public void use(Creature user) {
        super.use(user);
        if (delay==0&&user.getManaCount()>manacost){
            delay=cooldown;
            user.reduceMana(manacost);
            fire(user);
        }
    }
}
