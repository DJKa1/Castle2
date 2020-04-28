package items.Weapons;

import entities.creatures.Creature;
import items.Quality.Quality;


public abstract class MagicWeapons extends Weapons {
    protected int manacost;

    public MagicWeapons(Quality quality) {
        super(quality);
    }

    public abstract void fire(Creature user);

    @Override
    public void use(Creature user) {
        if (delay==0&&user.getManaCount()>manacost){
            fire(user);
            delay++;
            user.reduceMana(manacost);
        }
    }
}
