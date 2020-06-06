package entities.groundEffect;

import Buffs.Buff;
import entities.creatures.Creature;
import main_pack.Game;

import java.awt.*;

import static Handler.CreatureHandler.creatures;

public class GroundEffect extends Creature {
    protected int dmg;
    protected int duration;
    protected int delay = 0;
    protected int radius;
    protected Buff buff;
    protected int lifetime;

    public GroundEffect(float x, float y, Game game) {
        super(x, y, game);
    }


    @Override
    public void tick() {
        if(delay<duration) {
            delay++;
        }else {
            delay = 0;
        }
        for(Creature k : creatures) {
            if(Math.sqrt(Math.pow(k.getCenter().getX()-x,2)+Math.pow(k.getCenter().getY()-y,2))<radius&&!(k.getClass().getSimpleName() == this.getClass().getSimpleName())){
                if(delay==duration) {
                    k.hit(dmg,buff);
                }
            }
        }
        if(lifetime<=0){
            game.getCreatureHandler().removeObject(this);
        }
        lifetime--;
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.GREEN);
        g2d.drawOval(getPixelPosition(x-radius),getPixelPosition(y-radius),radius*2*128,radius*2*128);
    }
}
