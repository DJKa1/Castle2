package items.Weapons;

import entities.creatures.Creature;
import graphics.Texture;
import items.Quality.Quality;


public class EchoStaff extends MagicWeapons {
    public EchoStaff(Quality quality) {
        super(quality);
        image= Texture.sprite[29];

        //attributes.add(quality.getId().toString());
        //attributes.add(Float.toString(quality.getDmg()));
    }



}
