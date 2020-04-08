package main_pack;

import java.awt.*;

public class Menu {
    protected Game game;
    private KeyboardInput keyboardInput;
    private MouseInput mouseInput;
    private int selectedIndex = 0;

    private double scale = 5;

    private UI_Element[] ui_elements = new UI_Element[3];


    public Menu(Game game) {
        this.keyboardInput = game.getKeyboardInput();
        this.mouseInput = game.getMouseInput();
        ui_elements[0] = new UI_Element(0,0,16,16,"CONTINUE",0);
        ui_elements[1] = new UI_Element(0,2,16,16,"OPTIONS",2);
        ui_elements[2] = new UI_Element(0,4,16,16,"EXIT",2);
    }

    public void renderMenu(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(Launcher.WIDTH / 2 - (10 * Game.UNITDIMENSION / 2 * scale), Launcher.HEIGHT / 2 - (6 * Game.UNITDIMENSION / 2 * scale));
        g2d.scale(scale, scale);
        for (int i = 0; i<ui_elements.length;i++) {
            ui_elements[i].render(g);
        }
        g2d.scale(1 / scale, 1 / scale);
        g2d.translate(-Launcher.WIDTH / 2 - (10 * Game.UNITDIMENSION / 2 * scale), -Launcher.HEIGHT / 2 - (4 * Game.UNITDIMENSION / 2 * scale));
    }

    public void moveMenuIndexUp() {
        if(selectedIndex>0) {
            ui_elements[selectedIndex].setSelected(false);
            selectedIndex--;
            ui_elements[selectedIndex].setSelected(true);
        }
    }
    public void moveMenuIndexDown() {
        if(selectedIndex<2) {
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
}
