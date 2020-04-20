package Effects;


import Handler.Effectshandler;
import main_pack.Game;

import java.awt.*;

public class DmgIndicator extends Effect {
    private Effectshandler effectshandler;
    private float fade = 1;
    private float dmg;

    public DmgIndicator(float x, float y,float dmg, Effectshandler effectshandler) {
        duration = 60;
        this.effectshandler = effectshandler;
        this.dmg = dmg;
        this.x = x;
        this.y = y;
    }
    @Override
    public void tick() {
        duration--;
        fade-=1/60d;
        if(duration<=0) {
            effectshandler.removeObject(this);
        }
    }

    @Override
    public void render() {
        ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,fade));
        drawString(g,x,y,Float.toString(dmg),0);
    }

    private void drawString(Graphics g, float x, float y, String string, int shade) {
        int index = 0;
        string.toUpperCase();
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            int temp = (int) c;
            int temp_integer = 32; //for upper case
            if (temp <= 90 & temp >= 32) {
                index = temp - temp_integer;
                g.drawImage(Game.texture.goldenUIElements[index][shade], getPixelPosition(x) + i * 32, getPixelPosition(y),32,32, null);
            } else {
                System.out.println("No supported char");
            }
        }
    }
}
