package entities.creatures;
import ID_Lists.ID;
import graphics.Texture;
import main_pack.Game;
import java.awt.*;

public class GreenSlime extends Creature {
    public GreenSlime(float x, float y, Game game) {
        super(x, y,game);
        width= 1;
        height=1;
        baseDmg=1;
        hp=10;
        maxHp = hp;
        xpOnDeath=30;
        targetingRange=3;
        targetable=new ID[]{ID.Player};
        movementRate= (float) 0.05;
        armorValue=7;
        followingMultiplier=2;
        normalizeHitbox();
        normalizeMovementhitbox();

    }

    public GreenSlime(float x, float y, int lvl ,Game game) {
        super(x, y,game);
        this.lvl=lvl;
        width= 1;
        height=1;
        baseDmg= (float) getDmgMultiplier(lvl);
        hp= (float) (10*getLifeMultiplier(lvl));
        armorValue=7*getArmorMultiplier(lvl);
        maxHp = hp;
        xpOnDeath=30;
        targetingRange=3;
        targetable=new ID[]{ID.Player};
        movementRate= (float) 0.05;

        followingMultiplier=2;
        normalizeHitbox();
        normalizeMovementhitbox();




    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Texture.sprite[43],getPixelPosition(x),getPixelPosition(y),Game.UNIT_SCALE,Game.UNIT_SCALE,null);
        renderHealthbar(g);
        renderLvl(g);

    }

}
