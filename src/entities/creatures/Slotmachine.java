package entities.creatures;

import graphics.Animation;
import graphics.Texture;
import main_pack.Game;
import main_pack.KeyboardInput;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Slotmachine extends Creature {
    private Animation animation;
    private boolean rooling = false;
    private boolean nearby = false;
    private int time = 0;
    public Slotmachine(float x, float y, Game game) {
        super(x, y, game);
        animation = new Animation(3,Texture.SlotMachine[0],Texture.SlotMachine[1],Texture.SlotMachine[2],Texture.SlotMachine[3],Texture.SlotMachine[4],Texture.SlotMachine[5],Texture.SlotMachine[6],Texture.SlotMachine[7],Texture.SlotMachine[8],Texture.SlotMachine[9],Texture.SlotMachine[10],Texture.SlotMachine[11]);
        hitbox = new Rectangle2D.Double(x,y,3,3);
    }
    public void tick() {
        animation.runAnimation();
        if(rooling) {
            roll();
        }
        double dx = Math.sqrt(Math.pow(game.getPlayer().getX()+0.5-(x+1.5),2)+Math.pow(game.getPlayer().getY()+0.5-(y+3),2));
        if(dx<1) {
            nearby = true;
            if(KeyboardInput.x&&!rooling) {
                rooling =true;
                roll();
            }
        }else {
            nearby = false;
        }
    }

    private void roll() {
        time++;
        if (time>60) {
            System.out.println("You lose");
            time = 0;
            rooling = false;
        }
    }

    @Override
    public void render(Graphics g) {
        if (!rooling) {
            animation.drawAnimation(g,getPixelPosition(x),getPixelPosition(y),128*3);
        }else {
            g.drawImage(Texture.SlotMachine[12],getPixelPosition(x),getPixelPosition(y),128*3,128*3,null);

        }
        if(nearby) {
            g.setColor(Color.RED);
            g.fillRect(getPixelPosition(x),getPixelPosition(y)-100,100,100);
        }
    }
}
