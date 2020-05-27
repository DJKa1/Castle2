package entities.creatures.slotmachine;

import entities.creatures.Creature;
import graphics.Animation;
import graphics.Texture;
import main_pack.Game;
import main_pack.KeyboardInput;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Slotmachine extends Creature {
    private Animation animation;
    private boolean running = false;
    private boolean nearby = false;
    private int duration = 60 * 10;
    private Roller[] rollers = new Roller[3];

    private int time = 0;

    public Slotmachine(float x, float y, Game game) {
        super(x, y, game);
        animation = new Animation(3, Texture.SlotMachine[0], Texture.SlotMachine[1], Texture.SlotMachine[2], Texture.SlotMachine[3], Texture.SlotMachine[4], Texture.SlotMachine[5], Texture.SlotMachine[6], Texture.SlotMachine[7], Texture.SlotMachine[8], Texture.SlotMachine[9], Texture.SlotMachine[10], Texture.SlotMachine[11]);
        hitbox = new Rectangle2D.Double(x, y, 3, 3);
        reset();
    }

    public void tick() {
        animation.runAnimation();
        double dx = Math.sqrt(Math.pow(game.getPlayer().getX() + 0.5 - (x + 1.5), 2) + Math.pow(game.getPlayer().getY() + 0.5 - (y + 3), 2));
        if (dx < 1) {
            nearby = true;
            if (KeyboardInput.x && !running) {
                running = true;
            }
        } else {
            nearby = false;
        }
        if (running) {
            for (int i = 0;i<3;i++) {
                rollers[i].roll();
            }
            rollers[0].stop=true;
            if(!rollers[0].rolling){
                rollers[1].stop = true;
            }
            if(!rollers[1].rolling){
                rollers[2].stop = true;
            }
            time++;
            if (time > duration) {
                System.out.println("You lose");
                time = 0;
                running = false;
                reset();
            }
        }
    }



    @Override
    public void render(Graphics g) {
        if (!running) {
            animation.drawAnimation(g, getPixelPosition(x), getPixelPosition(y), 128 * 3);
        } else {
            g.setColor(new Color(25, 16, 46));
            g.fillRect(getPixelPosition(x), getPixelPosition(y), 128 * 3, 128 * 3);
            for (int i = 0;i<3;i++) {
                rollers[i].render(g);
            }
            g.drawImage(Texture.SlotMachine[12], getPixelPosition(x), getPixelPosition(y), 128 * 3, 128 * 3, null);
        }
        if (nearby) {
            g.setColor(Color.RED);
            g.fillRect(getPixelPosition(x), getPixelPosition(y) - 100, 100, 100);
        }
    }
    private void reset() {
        rollers[0] = new Roller(getPixelPosition(x)+24,getPixelPosition(y));
        rollers[1] = new Roller(getPixelPosition(x)+144,getPixelPosition(y));
        rollers[2] = new Roller(getPixelPosition(x)+264,getPixelPosition(y));
    }
}
