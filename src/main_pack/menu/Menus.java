package main_pack.menu;

import graphics.Texture;
import main_pack.Launcher;
import main_pack.UI_Element;

import java.awt.*;

public abstract class Menus {
    protected int selectedIndex = 0;

    protected UI_Element[] ui_elements;


    public void renderMenu(Graphics g) {
        for (UI_Element ui_element : ui_elements) {
            ui_element.render(g);
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
