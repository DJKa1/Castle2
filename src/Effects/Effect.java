package Effects;

import main_pack.Game;

import java.awt.*;

public abstract class Effect {
    protected int duration;
    protected float x,y;
    protected Graphics g;

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
