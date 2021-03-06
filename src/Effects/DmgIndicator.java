package Effects;


import Handler.Effectshandler;
import graphics.Texture;
import main_pack.Game;

import java.awt.*;
import java.awt.event.MouseAdapter;

public class DmgIndicator extends Effect {

    private float fade = 1;
    private float dmg;

    public DmgIndicator(float x, float y,float dmg, Effectshandler effectshandler) {
        super(x,y,effectshandler);
        duration = 60;
        this.dmg = roundatdigit(dmg,100);

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
        ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1));
    }

    private void drawString(Graphics g, float x, float y, String string, int shade) {
        int index = 0;
        string.toUpperCase();
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            int temp = c;
            int temp_integer = 32; //for upper case
            if (temp <= 90 & temp >= 32) {
                index = temp - temp_integer;
                g.drawImage(Texture.goldenUIElements[index][shade], getPixelPosition(x) + i * 32, getPixelPosition(y),32,32, null);
            } else {
                System.out.println("No supported char");
            }
        }
    }


    private float roundatdigit(float value,int digitUp10){
        value= Math.round(value*digitUp10);
        value/=digitUp10;
        return value;
    }



}
