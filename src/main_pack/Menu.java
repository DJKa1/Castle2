package main_pack;

import java.awt.*;

public class Menu {
    private Game game;
    private KeyboardInput keyboardInput;
    private MouseInput mouseInput;
    private int selectedIndex=0;

    private double scale = 5;


    public Menu(Game game) {
        this.keyboardInput = game.getKeyboardInput();
        this.mouseInput = game.getMouseInput();
    }

    public void renderMenu(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(Launcher.WIDTH/2-(10*Game.UNITDIMENSION/2*scale),Launcher.HEIGHT/2-(6*Game.UNITDIMENSION/2*scale));
        g2d.scale(scale, scale);
        g.drawImage(Game.texture.goldenUIElements[36][0], 0, 0, null);
        g.drawImage(Game.texture.goldenUIElements[36][1], 0, 2 * Game.UNITDIMENSION, null);
        g.drawImage(Game.texture.goldenUIElements[36][2], 0, 4 * Game.UNITDIMENSION, null);

        drawString(g,2,0,"CONTINUE",0);
        drawString(g,2,2,"OPTIONS",1);
        drawString(g,2,4,"EXIT",2);
        g2d.scale(1 / scale, 1 / scale);
        g2d.translate(-Launcher.WIDTH/2-(10*Game.UNITDIMENSION/2*scale),-Launcher.HEIGHT/2-(4*Game.UNITDIMENSION/2*scale));
    }

    private void drawString(Graphics g,int x,int y,String string,int shade) {
        int index = 0;
        for (int i = 0;i<string.length();i++) {
            char c = string.charAt(i);
            int temp = (int) c;
            int temp_integer = 64; //for upper case
            if (temp <= 90 & temp >= 65) {
                index = temp - temp_integer;
                g.drawImage(Game.texture.goldenUIElements[index+9][shade],x*Game.UNITDIMENSION+i*Game.UNITDIMENSION,y*Game.UNITDIMENSION,null);
            }
        }
    }

}
