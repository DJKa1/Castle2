package main_pack;

import Handler.CreatureHandler;
import Handler.ProjectileHandler;
import Maps.Map;
import States.*;
import entities.creatures.Camera;
import entities.creatures.Player;
import graphics.Texture;
import graphics.Window;

import java.awt.*;
import java.awt.image.BufferStrategy;


public class Game implements Runnable {

    public static int Frames = 0, Ticks = 0;
    public final static double SCALE = 8;
    public final static int UNITDIMENSION = 16;
    public final static int UNIT_SCALE = 128;
    private Player player;
    private ProjectileHandler projectileHandler;
    private Thread thread;
    public static Texture texture;
    private boolean running = false;
    private String title = "Castle";
    private int width, height;
    public static State gameState, menuState, optionState, consoleState,invenstoryState;
    private BufferStrategy bs;
    private Graphics g;
    private Window window;
    private KeyboardInput keyboardInput;
    private CreatureHandler creatureHandler;

    private GameConsole gameConsole;
    private Menu menu;

    private MouseInput mouseInput;

    private Camera camera;
    private Map map;


    public Game(int width, int height) {
        this.width = width;
        this.height = height;
        this.start();


    }


    //Init

    public void init() {

        texture = new Texture();
        camera = new Camera(0, 0);

        //Handler
        projectileHandler = new ProjectileHandler();
        creatureHandler = new CreatureHandler();

        player = new Player(1, 3, projectileHandler, creatureHandler);

        //MapLoad----------------------------------------------------------------
        map = new Map("FirstLevel");

        //GameState Classes
        gameConsole = new GameConsole(this);
        menu = new Menu(this);

        //States
        gameState = new GameState(this);
        consoleState = new ConsoleState(this);
        menuState = new MenuState(this);
        invenstoryState=new  InventoryState(this);
        State.setState(gameState);

        //Input
        keyboardInput = new KeyboardInput(this);
        mouseInput = new MouseInput(this);

        window = new Window(title,width,height,this);
        window.fullscreen();
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

    public Camera getCamera() {
        return camera;
    }

    public KeyboardInput getKeyboardInput() {
        return keyboardInput;
    }

    public MouseInput getMouseInput() {
        return mouseInput;
    }

    public GameConsole getGameConsole() {
        return gameConsole;
    }
    public Menu getMenu() {
        return menu;
    }

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
        double amountOfTicks = 60.0;
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

    }

    private void render() {
        bs = window.getCanvas().getBufferStrategy();
        if (bs == null) {
            window.getCanvas().createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        if (State.getState() != null) {
            State.getState().render(g);
        }
        g.dispose();
        bs.show();

    }


}

