package main_pack;

import graphics.Texture;

import java.awt.*;

public class Menu {
    protected Game game;
    private KeyboardInput keyboardInput;
    private MouseInput mouseInput;
    private int selectedIndex = 0;
    private double scale = 5;

    private double transX = Launcher.WIDTH / 2 - (10 * Game.UNITDIMENSION / 2 * scale);
    private double transY = Launcher.HEIGHT / 2 - (6 * Game.UNITDIMENSION / 2 * scale);

    private UI_Element[] ui_elements = new UI_Element[4];


    public Menu(Game game) {
        this.keyboardInput = game.getKeyboardInput();
        this.mouseInput = game.getMouseInput();
        ui_elements[0] = new UI_Element("CONTINUE", (int) (Launcher.WIDTH/2-1.5d*128), 128*3, 26,0,3,1);
        ui_elements[1] = new UI_Element("OPTIONS", Launcher.WIDTH/2-3*128, (int) (128*4.5d), 32,0,6,1);
        ui_elements[2] = new UI_Element("EXIT", (int) (Launcher.WIDTH/2-1.5d*128), (int) (128*6), 29,0,3,1);
        ui_elements[3] = new UI_Element("JUSTTEXT", Launcher.WIDTH/2-3*128, 128, 38,0,6,1);
    }

    public void renderMenu(Graphics g) {
        for (int i = 0; i < ui_elements.length; i++) {
            ui_elements[i].render(g);
        }
        for (int i = 0; i< 6;i++) {
            g.drawImage(Texture.Inventory[24][4],Launcher.WIDTH/2-3*128+i*128,128*2-40,128,128,null);
        }
        g.drawImage(Texture.Inventory[23][4],Launcher.WIDTH/2-4*128+48,128*2-40,128,128,null);
        g.drawImage(Texture.Inventory[25][4],Launcher.WIDTH/2+2*128+32,128*2-40,128,128,null);
    }

    public void moveMenuIndexUp() {
        if (selectedIndex > 0) {
            ui_elements[selectedIndex].setSelected(false);
            selectedIndex--;
            ui_elements[selectedIndex].setSelected(true);
        }
    }

    public void moveMenuIndexDown() {
        if (selectedIndex < 2) {
            ui_elements[selectedIndex].setSelected(false);
            selectedIndex++;
            ui_elements[selectedIndex].setSelected(true);
        }
    }

    public void setMenuIndex(int index) {
        selectedIndex = index;
    }

    public void click() {
        ui_elements[selectedIndex].onClick();
    }

    public void onMouseClick(int mx, int my) {
        Point point = new Point(mx,my);
        for (int i = 0; i < ui_elements.length; i++) {
            if(ui_elements[i].getBox().contains(point)) {
                ui_elements[i].onClick();
            }
        }
    }

    public void onMouseHover(int mx, int my) {
        Point point = new Point(mx,my);
        for (int i = 0; i < ui_elements.length; i++) {
            if(ui_elements[i].getBox().contains(point)) {
                ui_elements[i].setSelected(true);
            }else {
                ui_elements[i].setSelected(false);
            }
        }
    }
}
