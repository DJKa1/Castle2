package entities.creatures;
import States.GameState;
import entities.ID;
import Handler.CreatureHandler;
import main_pack.Game;
import Handler.ProjectileHandler;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class GreenSlime extends Creature {
    public GreenSlime(float x, float y, CreatureHandler creatureHandler, ProjectileHandler projectileHandler) {
        super(x, y,creatureHandler,projectileHandler);
        id= ID.Greenslime;
        width= 1;
        height=1;
        baseDmg=1;
        hp=100;
        maxHp = hp;
        hitbox=new Rectangle2D.Double(x,y,width,height);
    }
    @Override
    public void tick() {
        removeifdead();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(GameState.texture.sprite[43],getPixelPosition(x),getPixelPosition(y),Game.UNIT_SCALE,Game.UNIT_SCALE,null);
        renderHealthbar(g);
    }
    @Override
    public void drawHitbox(Graphics g) {
        g.setColor(Color.green);
        g.drawRect(getPixelPosition(x),getPixelPosition(y),getPixelPosition(width),getPixelPosition(height));
    }

}
