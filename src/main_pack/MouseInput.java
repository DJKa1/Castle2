package main_pack;



import States.MenuState;
import States.State;

import java.awt.event.*;

public class MouseInput implements MouseListener, MouseMotionListener , MouseWheelListener {

    public static float mouseX,mouseY;
    public static boolean leftPressed,rightPressed;
    public static int mouseWheelPos;
    private Game game;
    public MouseInput(Game game){
        this.game = game;
    }


    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if(mouseEvent.getButton()==MouseEvent.BUTTON1&& game.getactiveState().getClass()== MenuState.class) {
            game.getMenu().onMouseClick(mouseEvent.getX(),mouseEvent.getY());
        }

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseEvent.BUTTON1) {
            leftPressed=true;
        }

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseEvent.BUTTON1) {
            leftPressed=false;
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
        mouseX=mouseEvent.getX()-Launcher.WIDTH/2;
        mouseY=mouseEvent.getY()-Launcher.HEIGHT/2;

        //System.out.println(mouseX+"  "+mouseY);

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        mouseX=mouseEvent.getX()-Launcher.WIDTH/2;
        mouseY=mouseEvent.getY()-Launcher.HEIGHT/2;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent mouseWheelEvent) {
        mouseWheelPos+=mouseWheelEvent.getWheelRotation();
        if (mouseWheelPos<0){
            mouseWheelPos=0;
        }

    }
}
