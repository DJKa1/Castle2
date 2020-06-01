package main_pack;

import States.State;
import graphics.Texture;

import java.awt.*;

public class UI_Element {
    private Rectangle box;
    public int cellSize = 128;
    private boolean selected = false;
    public int imgX, imgY, imgWX, imgWY;
    private String content;
    private int shade = 0;


    public UI_Element(String content, int x, int y, int imgX, int imgY, int imgWX, int imgWY) {
        box = new Rectangle(x, y, imgWX * cellSize, imgWY * cellSize);
        this.imgX = imgX;
        this.imgY = imgY;
        this.imgWX = imgWX;
        this.imgWY = imgWY;
        this.content = content;
    }

    public static void drawString(Graphics g, int x, int y, String string, int size, int shade) {
        int index = 0;
        string.toUpperCase();
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            int temp = c;
            int temp_integer = 32; //for upper case
            if (temp <= 90 & temp >= 32) {
                index = temp - temp_integer;
                g.drawImage(Texture.goldenUIElements[index][shade], x + i * size, y, null);
            } else {
                System.out.println("No supported char");
            }
        }
    }

    public void render(Graphics g) {
        for (int yy = 0; yy <imgWY; yy++) {
            for (int xx = 0; xx <imgWX; xx++) {
                g.drawImage(Texture.Inventory[imgX+xx][imgY+yy+shade],box.x+xx*cellSize, box.y+yy*cellSize,cellSize,cellSize, null);
            }
        }

    }

    public void onClick() {
        switch (content) {
            case "CONTINUE":
                setToGameMode();
                break;
            case "OPTIONS":
                break;
            case "EXIT":
                System.exit(0);
                break;
            case "JUSTTEXT":
                break;
        }

    }

    private void setToGameMode() {
        State.setState(Game.gameState);
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        if (selected) {
            shade = 1;
        } else {
            shade = 0;
        }
        if(content.equals("JUSTTEXT")) {
            shade = 0;
        }
    }

    public Rectangle getBox() {
        return box;
    }
}
