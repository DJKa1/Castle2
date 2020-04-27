package main_pack;



import States.GameState;
import States.InventoryState;
import States.MenuState;

import items.Item;


import java.awt.event.*;

public class MouseInput implements MouseListener, MouseMotionListener , MouseWheelListener {

    public static float mouseX,mouseY;
    public static boolean leftPressed,rightPressed;
    public static int mouseWheelPos;
    private Game game;

    public static Item holdItem = null;

    public MouseInput(Game game){
        this.game = game;
    }


    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if(mouseEvent.getButton()==MouseEvent.BUTTON1 && game.getactiveState().getClass()== MenuState.class) {
            game.getMenu().onMouseClick(mouseEvent.getX(),mouseEvent.getY());
        }

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        if (game.getactiveState().getClass()==GameState.class) {
            if (mouseEvent.getButton() == MouseEvent.BUTTON1) {
                leftPressed = true;
            }
        } else if(game.getactiveState().getClass()== InventoryState.class) {
            mouseX = mouseEvent.getX();
            mouseY = mouseEvent.getY();
            Item tempItem = game.getPlayer().getInventory().grabItem(mouseEvent.getX(),mouseEvent.getY());
            if (tempItem != null) {

                holdItem = tempItem;
            }

        }

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

        if (game.getactiveState().getClass()==GameState.class) {
            if (mouseEvent.getButton() == MouseEvent.BUTTON1) {
                leftPressed = false;
            }
        } else if(game.getactiveState().getClass()== InventoryState.class) {
            if (holdItem != null) {
                game.getPlayer().getInventory().putItem(mouseEvent.getX(),mouseEvent.getY());
                holdItem = null;
            }

        }

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        if (game.getactiveState().getClass() == GameState.class) {
            if (KeyboardInput.Keyboard) {
                mouseX=mouseEvent.getX()-Launcher.WIDTH/2;
                mouseY=mouseEvent.getY()-Launcher.HEIGHT/2;
            }
        } else if (game.getactiveState().getClass()== InventoryState.class) {
            mouseX=mouseEvent.getX();
            mouseY=mouseEvent.getY();
        }

        //System.out.println(mouseX+"  "+mouseY);

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        if (game.getactiveState().getClass() == GameState.class) {
            if (KeyboardInput.Keyboard) {
                mouseX=mouseEvent.getX()-Launcher.WIDTH/2;
                mouseY=mouseEvent.getY()-Launcher.HEIGHT/2;
            }
        } if(game.getactiveState().getClass() == MenuState.class) {
            game.getMenu().onMouseHover(mouseEvent.getX(),mouseEvent.getY());
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent mouseWheelEvent) {
        mouseWheelPos+=mouseWheelEvent.getWheelRotation();
        if (mouseWheelPos<0){
            mouseWheelPos=0;
        }

    }
}
