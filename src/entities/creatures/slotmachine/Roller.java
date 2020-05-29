package entities.creatures.slotmachine;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Roller {
    private Symbol[] symbols;
    private Symbol endSymbol;
    private double moved = 0;
    private boolean finished = false;
    private BufferedImage top, down;
    private int count = 0;
    private double speed, baseSpeed = 1.1;


    public Roller(int length) {
        symbols = new Symbol[length + 1];
        initRoll();
        speed = baseSpeed * (length + 1);
    }

    private void initRoll() {
        for (int i = 0; i < symbols.length - 1; i++) {
            symbols[i] = new Symbol();
            symbols[i].showRoll();
        }
        endSymbol = new Symbol();
        endSymbol.roll();
        symbols[symbols.length - 1] = endSymbol;
        finished = false;
        top = symbols[1].getImg();
        down = symbols[0].getImg();
    }


    public boolean isFinished() {
        return finished;
    }

    public Symbol getSymbol() {
        return endSymbol;
    }

    public void tick() {

    }

    public void render(Graphics g, int x, int y) {
        for (int i = 0; i < symbols.length; i++) {
            int yy = y - (symbols.length - i - 2) * 128;
            g.drawImage(symbols[i].getImg(), x, yy, 96, 96, null);
        }
    }
}

