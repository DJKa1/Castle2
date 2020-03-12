package States;

import Maps.Map;
import entities.ID;
import entities.creatures.Camera;
import entities.creatures.Creature;
import entities.creatures.Player;
import main_pack.CreatureHandler;
import main_pack.Game;
import main_pack.Launcher;


import java.awt.*;
import java.util.Arrays;

public class GameState extends State{
    private CreatureHandler creatureHandler;
    private Camera camera;
    private Map map;
    private final ID[] EXCLUDE=null;



    public GameState(Game game){
        super(game);
        creatureHandler=new CreatureHandler();
        map=new Map("testMap");
        camera = new Camera(0,0);
        init();





    }

    @Override
    public void init(){
        creatureHandler.addObject(new Player(100,600));
    }


    @Override
    public void tick() {

        creatureHandler.tick();
        map.tick();

        Creature tempPlayer=null;
        //System.out.println("Creatures: "+creatureHandler.creatures.size());
        for (int i = 0;i<creatureHandler.creatures.size();i++){
            //System.out.println(creatureHandler.creatures.get(i).getName());
            if(creatureHandler.creatures.get(i).getId()== ID.Player) {
                tempPlayer = creatureHandler.creatures.get(i);
            }
        }

        camera.tick(tempPlayer);


        /*
        for(Creature k:creatureHandler.creatures){
            if (!Arrays.stream(EXCLUDE).anyMatch(k.getId()::equals)){
                k.checkCollision();
            }


        }


        */

    }

    @Override
    public void render(Graphics g) {



        //Draw fixed
        Graphics2D gd2 = (Graphics2D) g;
        g.setColor(Color.black);
        g.fillRect(0, 0, Launcher.WIDTH, Launcher.HEIGHT);
        //Camera show

        gd2.translate(camera.getX(), camera.getY()); //Cam start
        map.render(g);
        creatureHandler.render(g);


    }
}
