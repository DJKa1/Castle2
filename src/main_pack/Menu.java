package main_pack;

import java.awt.*;

public class Menu {
    protected Game game;
    private KeyboardInput keyboardInput;
    private MouseInput mouseInput;
    private int selectedIndex = 0;
    private double scale = 5;

    private double transX = Launcher.WIDTH / 2 - (10 * Game.UNITDIMENSION / 2 * scale);
    private double transY = Launcher.HEIGHT / 2 - (6 * Game.UNITDIMENSION / 2 * scale);

    private UI_Element[] ui_elements = new UI_Element[3];


    public Menu(Game game) {
        this.keyboardInput = game.getKeyboardInput();
        this.mouseInput = game.getMouseInput();
        ui_elements[0] = new UI_Element(0, 0, "CONTINUE", 0);
        ui_elements[1] = new UI_Element(0, 2, "OPTIONS", 2);
        ui_elements[2] = new UI_Element(0, 4, "EXIT", 2);
    }

    public void renderMenu(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(transX, transY);
        g2d.scale(scale, scale);
        for (int i = 0; i < ui_elements.length; i++) {
            ui_elements[i].render(g);
        }
        g2d.scale(1 / scale, 1 / scale);
        g2d.translate(-transX, -transY);
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
        Point point = new Point((int) ((mx - transX) / scale), (int) ((my - transY) / scale));
        for (int i = 0; i < ui_elements.length; i++) {
            if(ui_elements[i].getBox().contains(point)) {
                ui_elements[i].onClick();

            }
        }
    }
}
