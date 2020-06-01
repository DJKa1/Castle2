package main_pack;

import States.State;
import graphics.Texture;

import java.awt.*;

public class UI_Element{
    private Rectangle box;
    private int shade;
    private String content;
    private int cellSize = Game.UNITDIMENSION;
    private boolean selected = false;


    public UI_Element( int x, int y, String Content, int shade) {
        box = new Rectangle(x * cellSize, y*cellSize, (Content.length()+2)*cellSize, cellSize);
        this.shade = shade;
        this.content = Content;

    }

    public void render(Graphics g) {
        g.drawImage(Texture.goldenUIElements[59][shade], box.x, box.y, null);
        drawString(g, 2*16+box.x, 0+box.y, content,cellSize, shade);
    }

    public static void drawString(Graphics g, int x, int y, String string,int size, int shade) {
        int index = 0;
        string.toUpperCase();
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            int temp = c;
            int temp_integer = 32; //for upper case
            if (temp <= 90 & temp >= 32) {
                index = temp - temp_integer;
                g.drawImage(Texture.goldenUIElements[index][shade], x + i*size, y, null);
            } else {
                System.out.println("No supported char");
            }
        }
    }

    public void onClick() {
        switch (content) {
            case "CONTINUE":setToGameMode();break;
            case "OPTIONS":break;
            case "EXIT":System.exit(0);break;
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
        if(selected) {
            shade = 0;
        }else {
            shade = 2;
        }
    }

    public Rectangle getBox() {
        return box;
    }
}
