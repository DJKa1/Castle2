package main_pack;

public class Menu {
    private Game game;
    private KeyboardInput keyboardInput;
    private MouseInput mouseInput;


    public Menu(Game game) {
        this.keyboardInput = game.getKeyboardInput();
        this.mouseInput = game.getMouseInput();
    }

}
