package entities.creatures.slotmachine;

import ID_Lists.SymbolID;
import graphics.Texture;

import java.awt.*;

public class Roller {
    private Symbol[] symbols;
    private Symbol endSymbol;
    private double moved = 0;
    private boolean finished=false;


    public Roller(int length) {
        symbols = new Symbol[length];
        initRoll();


    }

    private void initRoll() {
        for (int i = 0; i < symbols.length; i++) {
            symbols[i] = new Symbol();
            symbols[i].showRoll();
        }
        endSymbol = new Symbol();
        endSymbol.roll();
        finished = false;
    }

    public int getLenght() {
        return symbols.length;
    }

    public boolean isFinished() {
        return finished;
    }

    public SymbolID getSymbol(){
        return endSymbol.getId();
    }

    public void render(Graphics g, int x, int y) {
        moved += 0.5;
            if (moved / 128 < symbols.length) {
                if (moved / 128 > 1) {
                    g.drawImage(symbols[(int) ((moved / 128) - 1)].getImg(), x, (int) (y + moved - 128 - (Math.floor(moved / 128) * 128)), 96, 96, null);
                }
                g.drawImage(symbols[(int) (moved / 128)].getImg(), x, (int) (y + moved - (Math.floor(moved / 128) * 128)), 96, 96, null);
            } else {
                finished = true;
                g.drawImage(endSymbol.getImg(), x, y, 96, 96, null);
            }
        }
}

