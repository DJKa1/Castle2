package main_pack;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class KeyboardInput implements KeyListener {
    protected boolean []keys,keySwitch,keyF3;
    public static boolean up,left,right,down,jump,esc,e,f3,g;
    public static boolean f3_s;
    public static  boolean f3G;
    protected Game game;

    public KeyboardInput(Game game){
        keys= new boolean[256];
        keySwitch=new boolean[256];
        keyF3=new boolean[256];
        this.game=game;
    }
    public void tick (){

        //Keys--------------------------------------
        up=keys[KeyEvent.VK_W];
        left=keys[KeyEvent.VK_A];
        right=keys[KeyEvent.VK_D];
        down=keys[KeyEvent.VK_S];
        jump=keys[KeyEvent.VK_SPACE];
        esc=keys[KeyEvent.VK_ESCAPE];
        e=keys[KeyEvent.VK_E];
        f3=keys[KeyEvent.VK_F3];
        g=keys[KeyEvent.VK_G];

        //KeySwitch----------------------------------
        f3_s=keySwitch[KeyEvent.VK_F3];




        //KeyF3--------------------------------------
        f3G=keyF3[KeyEvent.VK_G];


    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()]=true;

        if(keySwitch[e.getKeyCode()]){
            keySwitch[e.getKeyCode()]=false;
        }else { keySwitch[e.getKeyCode()]=true; }


        //F3-Funktions--------------------------------
        if (f3){
            if(keyF3[e.getKeyCode()]){
                keyF3[e.getKeyCode()]=false;
            }else {keyF3[e.getKeyCode()]=true; }


        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }
}
