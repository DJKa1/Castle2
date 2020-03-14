package main_pack;

import entities.creatures.Player;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInput implements MouseListener, MouseMotionListener {

    public static float mouseX,mouseY;
    public static boolean leftPressed,rightPressed;



    public MouseInput(){


    }


    @Override
    public void mouseClicked(MouseEvent mouseEvent) {


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



    }
}
