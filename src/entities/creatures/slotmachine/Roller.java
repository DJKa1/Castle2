package entities.creatures.slotmachine;

import graphics.Texture;

import java.awt.*;

public class Roller {
    public boolean rolling = true;
    private int time = 0;
    private int[] symbolsY = new int[3];
    private int speed = 12;
    private int speedToggle = 20;
    private int tempSpeedToggle = 0;
    private int x, y;
    public boolean stop = false;

    public Roller(int x, int y) {
        this.x = x;
        this.y = y;
        symbolsY[0] = 0;
        symbolsY[1] = 1*128;
        symbolsY[2] = 2*128;
    }

    public void roll() {
        if (stop) {
            tempSpeedToggle++;
        }

        if (tempSpeedToggle >= speedToggle) {
            speed -= 2;
            tempSpeedToggle = 0;
            if (speed < 0) {
                speed = 0;
            }
        }

        for (int yy = 0; yy < 3; yy++) {
            moveSymbol(yy);
        }


    }

    private void moveSymbol(int sy) {
        if (symbolsY[sy] <= 128 * 2) {
            symbolsY[sy] += speed;
            if (speed == 0 && rolling) {
                symbolsY[sy] += 1;
            }
        } else {
            int index = sy - 1;
            if (index == -1) {
                index = 2;
            }
            if (symbolsY[index] >= 128 * 2) {
                symbolsY[sy] = 0;
                if (speed == 0) {
                    rolling = false;
                }
            }
        }

    }

    public void render(Graphics g) {
        g.drawImage(Texture.sprite[56], x, y + symbolsY[0], 96, 96, null);
        g.drawImage(Texture.sprite[57], x, y + symbolsY[1], 96, 96, null);
        g.drawImage(Texture.sprite[58], x, y + symbolsY[2], 96, 96, null);
    }

}
