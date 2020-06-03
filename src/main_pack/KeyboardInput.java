package main_pack;

import Inventory.Inventory;
import States.*;
import entities.creatures.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInput implements KeyListener {
    public static boolean up, left, right, down, jump, esc, e, f3, g, enter, f, x;
    public static boolean impX, prevPressedX;
    public static boolean f3_s, t_s, e_s;
    public static boolean f3G;
    public static boolean Keyboard = true;
    public static float AxiesLX, AxiesLY;
    protected boolean[] keySwitch, keyF3, keys;
    private Game game;
    private GameConsole gameConsole;
    private Player player;


    public KeyboardInput(Game game) {
        keys = new boolean[256];
        keySwitch = new boolean[256];
        keyF3 = new boolean[256];
        this.game = game;
        player = game.getPlayer();
        gameConsole = game.getGameConsole();
    }

    public void tick() {

        //Keys--------------------------------------
        up = keys[KeyEvent.VK_W];
        left = keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_D];
        down = keys[KeyEvent.VK_S];
        jump = keys[KeyEvent.VK_SPACE];
        esc = keys[KeyEvent.VK_ESCAPE];
        e = keys[KeyEvent.VK_E];
        f3 = keys[KeyEvent.VK_F3];
        g = keys[KeyEvent.VK_G];
        f = keys[KeyEvent.VK_F];
        enter = keys[KeyEvent.VK_ENTER];
        x = keys[KeyEvent.VK_X];

        //Impkeys
        impX = false;
        if (!x) {
            prevPressedX = false;
        }
        if (!prevPressedX) {
            if (x) {
                impX = true;

                prevPressedX = true;
            }
        }


        if (Keyboard) {
            AxiesLX = boolToInt(KeyboardInput.right) - boolToInt(KeyboardInput.left);
            AxiesLY = boolToInt(KeyboardInput.down) - boolToInt(KeyboardInput.up);
        }

        //KeySwitch----------------------------------
        f3_s = keySwitch[KeyEvent.VK_F3];
        t_s = keySwitch[KeyEvent.VK_T];
        e_s = keySwitch[KeyEvent.VK_E];


        //KeyF3--------------------------------------
        f3G = keyF3[KeyEvent.VK_G];
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() <= 256) {
            //GameState-------------------------------------
            if (game.getactiveState().getClass() == GameState.class) {
                keys[e.getKeyCode()] = true;

                keySwitch[e.getKeyCode()] = !keySwitch[e.getKeyCode()];

                //F3-Funktions--------------------------------
                if (f3) {
                    keyF3[e.getKeyCode()] = !keyF3[e.getKeyCode()];
                }

                //InventoryManagment---------------------------------
                if (String.valueOf(e.getKeyChar()).matches("[0-9]")) {
                    player.getInventory().setActiveSlot(Integer.valueOf(e.getKeyChar()) - 49);
                    game.getMouseInput().mouseWheelPos = Integer.valueOf(e.getKeyChar()) - 49;
                }


                //StateSwitch-------------------------------
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    game.menu[0].setMenuIndex(0);
                    State.setState(Game.menuState);
                } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    State.setState((Game.consoleState));
                } else if (e.getKeyCode() == KeyEvent.VK_E) {
                    State.setState(Game.invenstoryState);
                }

                return;
            }


            //Menustate-------------------------------------------------------
            if (game.getactiveState().getClass() == MenuState.class) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    State.setState(Game.gameState);
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    game.menu[game.getMenustate().menuindex].moveMenuIndexUp();
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    game.menu[game.getMenustate().menuindex].moveMenuIndexDown();
                } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    game.menu[game.getMenustate().menuindex].click();
                }

                //StateSwitch---------------------------
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    State.setState(Game.gameState);

                }
                return;
            }

            //ConsoleInput--------------------------------
            if (game.getactiveState().getClass() == ConsoleState.class) {
                if (e.getKeyChar() != '\uFFFF' && e.getKeyCode() != KeyEvent.VK_DELETE && e.getKeyCode() != KeyEvent.VK_BACK_SPACE && e.getKeyCode() != KeyEvent.VK_ENTER && e.getKeyCode() != KeyEvent.VK_ESCAPE) {
                    gameConsole.appendInput(e.getKeyChar());
                } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    gameConsole.send();
                    game.deactivateConsole();
                } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    gameConsole.deletelastKey();
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    gameConsole.clearInput();
                    game.deactivateConsole();
                }
                return;
            }

            //InventoryState--------------------------------------
            if (game.getactiveState().getClass() == InventoryState.class) {
                if (String.valueOf(e.getKeyChar()).matches("[0-9]")) {
                }
                //StateSwitch--------------------
                if (e.getKeyCode() == KeyEvent.VK_E) {
                    Inventory inventory = game.getPlayer().getInventory();
                    boolean on = false;
                    for (int i = 0; i < inventory.slots.length; i++) {
                        if (inventory.slots[i].inBounds(MouseInput.mouseX, MouseInput.mouseY) && inventory.slots[i].item != null) {
                            if (inventory.slots[i].item.isUseableInInventory()) {
                                inventory.slots[i].item.use(game.getPlayer());
                                on = true;
                            }
                        }

                    }
                    if (!on) {
                        State.setState(Game.gameState);
                    }
                }


            }


        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() <= 256) {
            keys[e.getKeyCode()] = false;
        }
    }

    private int boolToInt(boolean b) {
        return b ? 1 : 0;
    }

    private float deadzone(float data, float deadzone) {
        float temp = Math.abs(data);
        if (temp > deadzone) {
            return data;
        }
        return 0;
    }
}
