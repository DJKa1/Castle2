package Effects;

import Handler.Effectshandler;
import main_pack.Game;

import java.awt.*;

public abstract class Effect {
    protected int duration;
    protected float x,y;
    protected Graphics g;
    protected Effectshandler effectshandler;

    public Effect( float x, float y,Effectshandler effectshandler){
        this.effectshandler = effectshandler;
        this.x = x;
        this.y = y;
    }



    public abstract void tick();
    public abstract void render();

    public void setG(Graphics g) {
        this.g = g;
    }
    public int getPixelPosition(float v) {
        v *= Game.UNIT_SCALE;
        return (int) v;
    }
    public int getPixelPosition(double v) {
        v *= Game.UNIT_SCALE;
        return (int) v;
    }
}
