package items.Armor;

import items.Item;
import items.Quality.Primitiv;
import items.Quality.Quality;


public  abstract class Armor extends Item {

    protected double armorValue;
    protected Quality quality;

    public Armor(Quality quality){
        super();
        if(quality==null){
            this.quality=new Primitiv();
        }else {
            this.quality=quality;
        }
        assert quality != null;
        quality.rollArmor();


        attributes.add(quality.getId().toString());



    }


    public double getArmorValue(){
        return armorValue+quality.getArmorBoost();
    }
}
