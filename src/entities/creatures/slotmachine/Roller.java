package entities.creatures.slotmachine;

import java.awt.*;


public class Roller {
    public boolean finished = false, slow = false, halfthrough = false;
    private Symbol[] symbols;
    private Symbol endSymbol;
    private double moved = 0;
    private int count = 0;
    private double speed;


    public Roller(int length) {
        symbols = new Symbol[length];
        initRoll();
        speed = 24;
    }

    private void initRoll() {
        for (int i = 0; i < symbols.length; i++) {
            symbols[i] = new Symbol();
            symbols[i].showRoll();
        }
        endSymbol = new Symbol();
        endSymbol.roll();
        symbols[0] = endSymbol;
        finished = false;
    }

    public boolean isFinished() {
        return finished;
    }

    public Symbol getSymbol() {
        return endSymbol;
    }

    public void tick() {

        if (moved <= (symbols.length - 1) * 128) {
            moved += speed;
        } else {
            finished = true;
        }
        if (slow) {
            if (speed > 1) {
                speed -= 0.1;
                if (speed < 12) {
                    halfthrough = true;
                }
            } else {
                speed = 1;
            }
        }
    }

    public void render(Graphics g, int x, int y) {
        for (int i = 0; i < symbols.length; i++) {
            int yy = y - (symbols.length - i - 1) * 128 + (int) moved;
            if (yy > y - 128 && yy < y + 128) {
                g.drawImage(symbols[i].getImg(), x, yy, 96, 96, null);
            }
        }
    }
}

