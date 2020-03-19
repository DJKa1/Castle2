package main_pack;

import Maps.Map;
import States.*;
import entities.ID;
import entities.creatures.Camera;
import entities.creatures.Creature;
import entities.creatures.Player;
import graphics.Window;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;


public class Game implements Runnable {

    public static int Frames = 0,Ticks = 0;
    public final static double SCALE = 4;
    public final static int UNITDIMENSION = 16;
    public final static int UNIT_SCALE = 64;


    private Thread thread;
    private boolean running = false;
    private String title="Castle";
    private int width,height;
    private State gameState,menuState,optionState;
    private BufferStrategy bs;
    private Graphics g;
    private Window window;
    private KeyboardInput keyboardInput;
    private CreatureHandler creatureHandler;
    private Map map;
    private MouseInput mouseInput;


    Camera camera;

    public Game(int width,int height) {
        this.width=width;
        this.height=height;
        this.start();



    }

    public synchronized void start() {
        if (running){
            return;
        }
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        if (running==false){
            return;
        }
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public CreatureHandler getCreatureHandler() {
        return creatureHandler;
    }

    public void init(){
        window =new Window(title,width,height);
        gameState=new GameState(this);
        mouseInput=new MouseInput();
        //window.getJFrame().addMouseListener(mouseInput);
        //window.getJFrame().addMouseMotionListener(mouseInput);
        window.getCanvas().addMouseListener(mouseInput);
        window.getCanvas().addMouseMotionListener(mouseInput);
        keyboardInput=new KeyboardInput();
        window.getJFrame().addKeyListener(keyboardInput);
        State.setState(gameState);



    }

    public Map getMap() {
        return map;
    }

    public void run() {
        init();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0,ticks=0;

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
                //System.out.println(String.format("UPS: %s, FPS: %s", ticks, frames));
                frames = 0;
                ticks = 0;
            }
        }
        stop();

    }

    public Camera getCamera() {
        return camera;
    }

    private void tick() {
        if (State.getState()!=null){
            State.getState().tick();
        }
        keyboardInput.tick();

    }

    private void render() {
        bs= window.getCanvas().getBufferStrategy();
        if (bs == null) {
            window.getCanvas().createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        if (State.getState()!=null){
            State.getState().render(g);
        }


        g.dispose();
        bs.show();

    }





}

