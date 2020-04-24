package Effects;

import Handler.Effectshandler;

import java.awt.*;

public class HitAnimation extends Effect {


    public HitAnimation(float x, float y,int duration,Effectshandler effectshandler) {
        super(x,y,effectshandler);
        this.duration=duration;
    }

    @Override
    public void tick() {
        duration--;
        if(duration<=0) {
            effectshandler.removeObject(this);
        }
    }

    @Override
    public void render() {
        g.setColor(Color.red);
        g.fillRect(getPixelPosition(x),getPixelPosition(y),20,20);

    }
}
