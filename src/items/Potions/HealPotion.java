package items.Potions;

import Buffs.HealCoolDown;
import ID_Lists.BuffID;
import entities.creatures.Creature;
import graphics.Texture;

public class HealPotion extends Potion{
    private final float baseHeal=50;

    public HealPotion(int lvl) {
        super(lvl);
        image= Texture.sprite[24];
    }

    @Override
    public void use(Creature user) {
        if(!user.hasBuff(BuffID.HealCoolDown)){
            user.heal(baseHeal*lvl);
            user.addBuff(new HealCoolDown(user,600));
            super.use(user);
        }
      ;

    }
}
