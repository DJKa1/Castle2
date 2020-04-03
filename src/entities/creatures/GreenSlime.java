package entities.creatures;
import entities.ID;
import main_pack.CreatureHandler;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class GreenSlime extends Creature {
    public GreenSlime(float x, float y,CreatureHandler creatureHandler) {
        super(x, y,creatureHandler);
        id= ID.Greenslime;
        width= 1;
        height=1;
        baseDmg=1;
        hp=50;
        hitbox=new Rectangle2D.Double(x,y,width,height);
    }
    @Override
    public void tick() {
        removeifdead();
    }

    @Override
    public void render(Graphics g) {

    }
    @Override
    public void drawHitbox(Graphics g) {
        g.setColor(Color.green);
        g.drawRect(getPixelPosition(x),getPixelPosition(y),getPixelPosition(width),getPixelPosition(height));
    }

}
