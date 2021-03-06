package entities.creatures.slotmachine;

import Sound.Sound;
import entities.creatures.Creature;
import graphics.Animation;
import graphics.Texture;
import main_pack.Game;
import main_pack.KeyboardInput;

import java.awt.*;

public class Slotmachine extends Creature {
    private final int width = 3;
    private Animation animation;
    private boolean running = false, rolling = false;
    private Roller[] rollers;
    private boolean nearby;
    private String lastResult = "";
    private int timeoutafterroll = 0, delay = 100;
    private int cost = 10;
    private boolean pay = false;
    private int winAmount = 0;

    private boolean jackPot = false;

    private boolean playOnce[] = new boolean[1];

    public Slotmachine(float x, float y, Game game) {
        super(x, y, game);
        animation = new Animation(3, Texture.SlotMachine[0], Texture.SlotMachine[1], Texture.SlotMachine[2], Texture.SlotMachine[3], Texture.SlotMachine[4], Texture.SlotMachine[5], Texture.SlotMachine[6], Texture.SlotMachine[7], Texture.SlotMachine[8], Texture.SlotMachine[9], Texture.SlotMachine[10], Texture.SlotMachine[11]);
        rollers = new Roller[width];
    }

    public void tick() {
        animation.runAnimation();
        if (running) {
            for (Roller r : rollers) {
                r.tick();
            }
            for (int i = 0; i < rollers.length; i++) {
                if (rollers[i].halfthrough) {
                    if (i < rollers.length - 1) {
                        rollers[i + 1].slow = true;
                        int c = 0;
                        for (int a = 0; a < rollers.length; a++) {
                            if (rollers[a].isFinished()) {
                                c++;
                            }
                        }
                        if (c == rollers.length) {
                            finishRoll();
                        }
                    }
                } else {
                    return;
                }
            }
            if (!rolling) {
                timeoutafterroll++;
                if (timeoutafterroll >= delay) {
                    running = false;
                }
                if (pay) {
                    game.getPlayer().getInventory().money += winAmount / (double) delay;
                    Sound.playSound("SlotWin");
                }
            }
        } else {
            double dx = Math.sqrt(Math.pow(game.getPlayer().getX() + 0.5 - (x + 1.5), 2) + Math.pow(game.getPlayer().getY() + 0.5 - (y + 3), 2));
            if (dx < 1) {
                nearby = true;
                if (KeyboardInput.x && !running && game.getPlayer().getInventory().getMoneyCount() > cost) {
                    roll();
                }
            } else {
                nearby = false;
            }
        }
    }

    private void roll() {
        game.getPlayer().getInventory().money -= cost;
        lastResult = "";
        running = true;
        rolling = true;
        buildRollers();
        rollers[0].slow = true;
        timeoutafterroll = 0;
        pay = false;
        winAmount = 0;
        jackPot = false;
        for (int i = 0; i < playOnce.length; i++) {
            playOnce[i] = true;
        }
    }

    private void buildRollers() {
        rollers[0] = new Roller(24);
        rollers[1] = new Roller(46);
        rollers[2] = new Roller(70);

    }

    private int booltoInt(boolean b) {
        if (b) {
            return 1;
        }
        return 0;
    }

    private void finishRoll() {
        rolling = false;
        int c = 0;
        for (int a = 0; a < rollers.length; a++) {
            if (a < rollers.length - 1) {
                if (rollers[a].getSymbol().getId() == rollers[a + 1].getSymbol().getId()) {
                    c++;
                }
            }
        }
        if (c == rollers.length - 1) {
            pay = true;
            winAmount = 100;
            jackPot = true;
        } else if (c == rollers.length - 2) {
            pay = true;
            winAmount = 10;
        } else {
            if (playOnce[0]) {
                Sound.playSound("YouLose");
                playOnce[0] = false;
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

            if (jackPot) {
                drawJackpot(g,getPixelPosition(x)+8,getPixelPosition(y+1)-16);
                drawJackpot(g,getPixelPosition(x+1),getPixelPosition(y+1)-16);
                drawJackpot(g,getPixelPosition(x+2)-8,getPixelPosition(y+1)-16);
            } else {
                for (int i = 0; i < rollers.length; i++) {
                    rollers[i].render(g, getPixelPosition(x) + 26 + 118 * i, getPixelPosition(y) + 128);
                }
            }
        }
        g.drawImage(Texture.SlotMachine[12], getPixelPosition(x), getPixelPosition(y), 128 * 3, 128 * 3, null);
    }

    private void drawJackpot(Graphics g,int px,int py) {
        for (int yy = 0; yy < 4; yy++) {
            for (int xx = 0; xx < 4; xx++) {
                g.drawImage(Texture.Inventory[8 + xx][1 + yy], px+xx*32, py+yy*32, 32, 32, null);
            }
        }

    }
}
