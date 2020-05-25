package Effects;

import Handler.Effectshandler;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class DeathEffect extends Effect {
    private Rectangle2D.Float[] rectangle2DS = new Rectangle2D.Float[60];
    private float fade = 1;
    public DeathEffect(float x, float y, Effectshandler effectshandler) {
        super(x, y, effectshandler);
        duration = 100;
        for (int i = 0; i< rectangle2DS.length;i++) {
            rectangle2DS[i] = new Rectangle2D.Float(getPixelPosition(x+0.5),getPixelPosition(y+0.5),16,16);
        }
    }

    @Override
    public void tick() {
        duration--;
        fade-=1/100d;
        if(duration<=0) {
            effectshandler.removeObject(this);
        }
        for (int i = 0; i< rectangle2DS.length;i++) {
            rectangle2DS[i].x += (Math.random()*2-1)*5;
            rectangle2DS[i].y += (Math.random()*2-1)*5;
        }
    }

    @Override
    public void render() {
        g.setColor(Color.WHITE);
        Graphics2D g2d = (Graphics2D) g;
        ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,fade));
        for (int i = 0; i< rectangle2DS.length;i++) {
            g2d.fill(rectangle2DS[i]);
        }
        ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1));
    }
}
