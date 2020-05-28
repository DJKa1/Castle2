package entities.creatures.slotmachine;

import ID_Lists.SymbolID;
import entities.creatures.Creature;
import graphics.Animation;
import graphics.Texture;
import main_pack.Game;
import main_pack.KeyboardInput;
import org.lwjgl.Sys;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Slotmachine extends Creature {
    private Animation animation;
    private boolean running = false;
    private Roller[] rollers;
    private boolean nearby;
    private boolean rollFinished;
    private String status="";

    private final int width = 3;



    public Slotmachine(float x, float y, Game game) {
        super(x, y, game);
        animation = new Animation(3, Texture.SlotMachine[0], Texture.SlotMachine[1], Texture.SlotMachine[2], Texture.SlotMachine[3], Texture.SlotMachine[4], Texture.SlotMachine[5], Texture.SlotMachine[6], Texture.SlotMachine[7], Texture.SlotMachine[8], Texture.SlotMachine[9], Texture.SlotMachine[10], Texture.SlotMachine[11]);
        hitbox = new Rectangle2D.Double(x, y, 3, 3);
        rollers=new Roller[width];
    }

    public void tick() {
        animation.runAnimation();
        double dx = Math.sqrt(Math.pow(game.getPlayer().getX() + 0.5 - (x + 1.5), 2) + Math.pow(game.getPlayer().getY() + 0.5 - (y + 3), 2));
        if (dx < 1) {
            nearby = true;
            if (KeyboardInput.x && !running) {
               roll();
            }
        } else {
            nearby = false;
        }

        if (running) {
            boolean temp = true;
            for (Roller r : rollers) {

                if (!r.isFinished()) {
                    temp = false;
                }
            }
            if (temp){
                endRoll();
            }

        }
    }

    private void endRoll(){
        rollFinished=false;
        running=false;
        status="LOSE";
        SymbolID temp =rollers[0].getSymbol();
        for (Roller r : rollers){
            if (r.getSymbol()!=temp){
                return;
            }
        }
        status="WIN";




    }

    private void roll(){
        running = true;
        status="running";
        rollFinished=false;
        buildRollers();
    }

    private void buildRollers(){
        rollers[0]=new Roller(3);
        rollers[1]=new Roller(6);
        rollers[2]=new Roller(9);
    }


    @Override
    public void render(Graphics g) {
        if (!running) {
            animation.drawAnimation(g, getPixelPosition(x), getPixelPosition(y), 128 * 3);
        } else {
            g.setColor(new Color(25, 16, 46));
            g.fillRect(getPixelPosition(x), getPixelPosition(y), 128 * 3, 128 * 3);

            for (int i=0; i<rollers.length; i++){
                rollers[i].render(g,getPixelPosition(x)+32+128*i,getPixelPosition(y)+128);
            }
            g.drawImage(Texture.SlotMachine[12], getPixelPosition(x), getPixelPosition(y), 128 * 3, 128 * 3, null);

            System.out.println(status);
        }
    }
}
