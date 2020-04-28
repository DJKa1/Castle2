package items.Weapons;

import entities.creatures.Creature;
import items.Quality.Quality;


public abstract class MagicWeapons extends Weapons {
    protected int manacost;

    public MagicWeapons(Quality quality) {
        super(quality);
    }


}
