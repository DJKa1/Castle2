package main_pack;




import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInput implements KeyListener {

    protected boolean []keys;
    public static boolean up,left,right,down,jump,esc,e;
    protected Game game;

    
    public KeyboardInput(Game game){
        keys= new boolean[256];
        this.game=game;
    }

    public void tick (){

        up=keys[KeyEvent.VK_W];
        left=keys[KeyEvent.VK_A];
        right=keys[KeyEvent.VK_D];
        down=keys[KeyEvent.VK_S];
        jump=keys[KeyEvent.VK_SPACE];
        esc=keys[KeyEvent.VK_ESCAPE];
        e=keys[KeyEvent.VK_E];

    }


    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()]=true;





    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()]=false;

    }
}
