package entities.creatures;
import Effects.Effect;
import Handler.Effectshandler;
import States.GameState;
import ID_Lists.ID;
import Handler.CreatureHandler;
import main_pack.Game;
import Handler.ProjectileHandler;
import java.awt.*;

public class GreenSlime extends Creature {
    public GreenSlime(float x, float y, Game game) {
        super(x, y,game);
        width= 1;
        height=1;
        baseDmg=1;
        hp=10;
        maxHp = hp;
        targetingRange=3;
        targetable=new ID[]{ID.Player};
        movementRate= (float) 0.05;
        armorValue=15;
        followingMultiplier=2;
        normalizeHitbox();
        normalizeMovementhitbox();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(GameState.texture.sprite[43],getPixelPosition(x),getPixelPosition(y),Game.UNIT_SCALE,Game.UNIT_SCALE,null);
        renderHealthbar(g);
    }

}
