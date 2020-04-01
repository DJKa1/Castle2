package main_pack;
import States.ConsoleState;
import States.GameState;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class KeyboardInput implements KeyListener {
    protected boolean []keySwitch,keyF3,keys;
    public static boolean up,left,right,down,jump,esc,e,f3,g,enter;
    public static boolean f3_s,t_s;
    public static  boolean f3G;
    private Game game;
    private GameConsole gameConsole;



    public KeyboardInput(Game game){
        keys= new boolean[256];
        keySwitch=new boolean[256];
        keyF3=new boolean[256];
        this.game=game;

        gameConsole= game.getGameConsole();
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
        enter=keys[KeyEvent.VK_ENTER];

        //KeySwitch----------------------------------
        f3_s=keySwitch[KeyEvent.VK_F3];
        t_s=keySwitch[KeyEvent.VK_T];


        //KeyF3--------------------------------------
        f3G=keyF3[KeyEvent.VK_G];


    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()<=256) {
            keys[e.getKeyCode()] = true;

            if (keySwitch[e.getKeyCode()]) {
                keySwitch[e.getKeyCode()] = false;
            } else {
                keySwitch[e.getKeyCode()] = true;
            }


            //F3-Funktions--------------------------------
            if (f3) {
                if (keyF3[e.getKeyCode()]) {
                    keyF3[e.getKeyCode()] = false;
                } else {
                    keyF3[e.getKeyCode()] = true;
                }


            }

        }

        //ConsoleInput--------------------------------
        if (game.getactiveState().getClass()==ConsoleState.class) {
            if (e.getKeyChar() != '\uFFFF' && e.getKeyCode() != KeyEvent.VK_DELETE&& e.getKeyCode()!=KeyEvent.VK_BACK_SPACE&&e.getKeyCode() != KeyEvent.VK_ENTER){
                gameConsole.appendInput(e.getKeyChar());

            } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                gameConsole.send();
                game.deactivateConsole();
            }else if (e.getKeyCode()== KeyEvent.VK_BACK_SPACE){
                gameConsole.deletelastKey();
            }

        }else if(game.getactiveState().getClass()== GameState.class){
            if (e.getKeyCode()==KeyEvent.VK_F8){
                game.activateConsole();
            }
        }

    }
    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()<=256){
        keys[e.getKeyCode()] = false;
    }}
}
