package main_pack;

import Maps.Map;
import States.*;
import entities.creatures.Camera;
import entities.creatures.Creature;
import entities.creatures.Player;
import graphics.Window;
import java.awt.*;
import java.awt.image.BufferStrategy;


public class Game implements Runnable {



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

    public void init(){

        window =new Window(title,width,height);
        gameState=new GameState();
        menuState=new MenuState();

        keyboardInput=new KeyboardInput(this);
        window.getJFrame().addKeyListener(keyboardInput);

        creatureHandler=new CreatureHandler();
        map=new Map("testMap");

        State.setState(gameState);
        creatureHandler.addObject(new Player(100,100,"player"));
        camera = new Camera(0,0);
    }
    //momentate lösung
    public void changeState(){
        if (State.getState()==gameState){
            State.setState(menuState);
        }
    }

    public void run() {
        init();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;

            }
            if (running)
                render();

            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                // System.out.println("FPS:"+frames);
                frames = 0;
            }
        }
        stop();

    }

    private void tick() {
        if (State.getState()!=null){
            State.getState().tick();
        }
        keyboardInput.tick();
        creatureHandler.tick();
        map.tick();



        Creature tempPlayer=null;
        //System.out.println("Creatures: "+creatureHandler.creatures.size());
        for (int i = 0;i<creatureHandler.creatures.size();i++){
            //System.out.println(creatureHandler.creatures.get(i).getName());
            if(creatureHandler.creatures.get(i).getName()=="player") {
                tempPlayer = creatureHandler.creatures.get(i);
            }
        }

        camera.tick(tempPlayer);

    }

    private void render() {


        bs= window.getCanvas().getBufferStrategy();
        if (bs == null) {
            window.getCanvas().createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        //Draw fixed
        Graphics2D gd2 = (Graphics2D) g;

        //Background
        g.setColor(Color.black);
        g.fillRect(0, 0, Launcher.WIDTH, Launcher.HEIGHT);

        //Camera show

        gd2.translate(camera.getX(), camera.getY()); //Cam start



        //draw
        g.clearRect(0,0,width,height);
        g.setColor(Color.BLACK);
        g.fillRect(0,0,width,height);




        map.render(g);



        if (State.getState()!=null){
            State.getState().render(g);


        }
        creatureHandler.render(g);



        g.dispose();
        bs.show();

    }





}

