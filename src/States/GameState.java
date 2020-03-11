package States;

import Maps.Map;
import entities.ID;
import entities.creatures.Camera;
import entities.creatures.Creature;
import main_pack.CreatureHandler;
import main_pack.Game;


import java.awt.*;

public class GameState extends State{
    CreatureHandler creatureHandler;
    Camera camera;
    Map map;



    public GameState(Game game){
        super(game);

    }

    @Override
    public void init(){
        this.creatureHandler=game.getCreatureHandler();
        this.camera=game.getCamera();
        this.map=game.getMap();



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




    }

    @Override
    public void render(Graphics g) {

        map.render(g);
        creatureHandler.render(g);



    }
}
