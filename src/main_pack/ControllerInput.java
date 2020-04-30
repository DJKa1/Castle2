package main_pack;


import States.GameState;
import States.MenuState;
import States.State;
import entities.Vector2D;
import org.lwjgl.input.Controller;

public class ControllerInput {
    private static Controller controller;
    public boolean pressed = false;
    public static boolean[] buttonsState;

    private Game game;

    public ControllerInput(Game game, Controller controller) {
        this.game = game;
        ControllerInput.controller = controller;
        buttonsState = new boolean[controller.getButtonCount() + 4];
    }

    public void tick() {
        controller.poll();
        if (game.getactiveState().getClass()== GameState.class) {
            KeyboardInput.AxiesLX = (float)getLeftAxisX();
            KeyboardInput.AxiesLY = (float)getLeftAxisY();


            if ((float)getRightAxisX()!=0&&(float)getRightAxisY()!=0) {
                Vector2D aim = new Vector2D((float)getRightAxisX(),(float)getRightAxisY());
                aim.normalize();

                MouseInput.mouseX = (float) aim.x;
                MouseInput.mouseY = (float) aim.y;
            }

            if (controller.isButtonPressed(9)) {
                State.setState(Game.menuState);
                return;
            }

        } else if (game.getactiveState().getClass()== MenuState.class) {
            if (controller.isButtonPressed(9)) {

            }
        }

        //update buttonstate array
        for (int i = 0; i < controller.getButtonCount() - 4; i++) {
            if (i < controller.getButtonCount() - 4) {
                buttonsState[i] = controller.isButtonPressed(i);
            }
        }
        buttonsState[14] = PoVUp();
        buttonsState[15] = PoVRight();
        buttonsState[16] = PoVDown();
        buttonsState[17] = PoVLeft();


        //------------------------------------------------------
    }

    public boolean clicked(int buttonindex) {
        return controller.isButtonPressed(buttonindex) && buttonsState[buttonindex] == false;
    }

    public boolean clickedPoV(boolean button, int buttonindex) {
        return button && buttonsState[buttonindex] == false;
    }

    public boolean PoVUp() {
        return controller.getPovY() == -1;
    }

    public boolean PoVDown() {
        return controller.getPovY() == 1;
    }

    public boolean PoVRight() {
        return controller.getPovX() == 1;
    }

    public boolean PoVLeft() {
        return controller.getPovX() == -1;
    }

    public double getLeftAxisX() {
        return controller.getAxisValue(3);
    }

    public double getLeftAxisY() {
        return controller.getAxisValue(2);
    }

    public double getRightAxisX() {
        return controller.getAxisValue(1);
    }

    public double getRightAxisY() {
        return controller.getAxisValue(0);
    }

    public double getR2() {
        return controller.getAxisValue(4);
    }

    public boolean SquareisPressed() {
        return controller.isButtonPressed(0);
    }
    public boolean XisPressed() {
        return controller.isButtonPressed(1);
    }
    public boolean CircleisPressed() {
        return controller.isButtonPressed(2);
    }
    public boolean TriangleisPressed() {
        return controller.isButtonPressed(3);
    }
    public boolean L1isPressed() {
        return controller.isButtonPressed(4);
    }
    public static boolean R1isPressed() {
        return controller.isButtonPressed(5);
    }
    public boolean L2isPressed() {
        return controller.isButtonPressed(6);
    }
    public boolean R2isPressed() {
        return controller.isButtonPressed(7);
    }
    public boolean ShareisPressed() {
        return controller.isButtonPressed(8);
    }
    public boolean OptionsisPressed() {
        return controller.isButtonPressed(9);
    }
    public boolean L3isPressed() {
        return controller.isButtonPressed(10);
    }
    public boolean R3isPressed() {
        return controller.isButtonPressed(11);
    }
    public boolean PSisPressed() {
        return controller.isButtonPressed(12);
    }
    public boolean PadisPressed() {
        return controller.isButtonPressed(13);
    }

}
