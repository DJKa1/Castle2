package main_pack;

import Handler.CreatureHandler;
import Handler.Effectshandler;
import Handler.ProjectileHandler;
import Maps.Map;
import States.*;
import graphics.Camera;
import entities.creatures.Player;
import graphics.Texture;
import graphics.Window;
import items.LootTableList;
import main_pack.menu.MainMenu;
import main_pack.menu.Menus;
import main_pack.menu.OptionsMenu;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Controller;
import org.lwjgl.input.Controllers;

import java.awt.*;
import java.awt.image.BufferStrategy;

import static main_pack.Launcher.GAMESCALE;



public class Game implements Runnable {

    public static int Frames = 0, Ticks = 0;
    public final static double SCALE = 8;
    public final static int UNITDIMENSION = 16;
    public final static int UNIT_SCALE = 128;
    public final static int TICKRATE = 60;
    private boolean running = false;
    private Thread thread;
    private String title = "Castle";
    private int width, height;
    //Handler
    private ProjectileHandler projectileHandler;
    private CreatureHandler creatureHandler;
    private Effectshandler effectshandler;
    //Input
    private MouseInput mouseInput;
    private KeyboardInput keyboardInput;
    private static ControllerInput controllerInput;
    //States
    public static State gameState, menuState, optionState, consoleState, invenstoryState,objectiveInventoryState;
    //Graphics
    public static Texture texture;
    private BufferStrategy bs;
    private Window window;
    public static Graphics g;
    //GameStuff
    private Player player;
    private GameConsole gameConsole;
    public Menus menu[];
    private Camera camera;
    private Map map;
    private LootTableList lootTableList;


    public Game(int width, int height) {
        this.width = width;
        this.height = height;
        this.start();
    }


    //Init

    public void init() {
        //Controller
        try {
            Controllers.create();
        } catch (LWJGLException e) {
        }
        for (int i = 0; i < Controllers.getControllerCount(); i++) {
            Controller tempController = Controllers.getController(i);
            if (tempController.getName().equalsIgnoreCase("Wireless Controller")) {
                System.out.println("Controller found");
                KeyboardInput.Keyboard = false;
                controllerInput = new ControllerInput(this, tempController);
            }
        }
        //Graphics
        texture = new Texture();
        camera = new Camera(0, 0);

        //Handler
        projectileHandler = new ProjectileHandler();
        creatureHandler = new CreatureHandler();
        effectshandler = new Effectshandler();

        //MapLoad----------------------------------------------------------------
        map = new Map("WaveMap");
        lootTableList=new LootTableList();

        player = new Player(32, 32, this);
        camera.setX(-player.getPixelPosition(player.getX()) + Launcher.WIDTH / 2 - UNIT_SCALE / 2);
        camera.setY(-player.getPixelPosition(player.getY()) + Launcher.HEIGHT / 2 - UNIT_SCALE / 2);


        //GameState Classes
        gameConsole = new GameConsole(this);
        menu = new Menus[2];
        menu[0] = new MainMenu();
        menu[1] = new OptionsMenu();

        //States
        gameState = new GameState(this);
        consoleState = new ConsoleState(this);
        menuState = new MenuState(this);
        invenstoryState = new InventoryState(this);
        objectiveInventoryState = new ObjectInventoryState(this);
        State.setState(gameState);

        //Input
        keyboardInput = new KeyboardInput(this);
        mouseInput = new MouseInput(this);

        //Window
        window = new Window(title, width, height, this);
        window.getCanvas().addMouseListener(mouseInput);
        window.getCanvas().addMouseMotionListener(mouseInput);
        window.getJFrame().addKeyListener(keyboardInput);
    }



    //Getters && Setters

    public Map getMap() {
        return map;
    }

    public State getactiveState() {
        return State.getState();
    }

    public Player getPlayer() {
        return player;
    }

    public ProjectileHandler getProjectileHandler() {
        return projectileHandler;
    }

    public CreatureHandler getCreatureHandler() {
        return creatureHandler;
    }

    public Effectshandler getEffectshandler() {
        return effectshandler;
    }

    public Camera getCamera() {
        return camera;
    }

    public KeyboardInput getKeyboardInput() {
        return keyboardInput;
    }

    public MouseInput getMouseInput() {
        return mouseInput;
    }

    public ControllerInput getControllerInput() {
        return controllerInput;
    }

    public GameConsole getGameConsole() {
        return gameConsole;
    }

    public GameState getGameState() {
        return (GameState) gameState;
    }

    public MenuState getMenustate() {return (MenuState) menuState;}

    public void activateConsole() {
        State.setState(consoleState);
    }

    public void deactivateConsole() {
        State.setState((gameState));
    }

    //Game Loop
    public synchronized void start() {
        if (running) {
            return;
        }
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        if (running == false) {
            return;
        }
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void run() {
        init();
        long lastTime = System.nanoTime();
        double amountOfTicks = TICKRATE;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0, ticks = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                ticks++;
                delta--;

            }
            if (running)
                render();

            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                Frames = frames;
                Ticks = ticks;
                frames = 0;
                ticks = 0;
            }
        }
        stop();
    }


    //tick && render
    private void tick() {
        if (State.getState() != null) {
            State.getState().tick();
        }
        keyboardInput.tick();
        if (controllerInput != null && !KeyboardInput.Keyboard) {
            controllerInput.tick();
        }
    }

    private void render() {
        bs = window.getCanvas().getBufferStrategy();
        if (bs == null) {
            window.getCanvas().createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;
        g2d.scale(GAMESCALE,GAMESCALE);
        if (State.getState() != null) {
            State.getState().render(g);
        }
        g.dispose();
        bs.show();

    }


}

