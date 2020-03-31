package States;

import Maps.Map;
import Tiles.Texture;
import entities.ID;
import entities.creatures.Camera;
import entities.creatures.Creature;
import entities.creatures.GreenSlime;
import entities.creatures.Player;
import entities.projectile.Plasmabolt;
import entities.projectile.Projectile;
import entities.projectile.ProjectilePool;
import graphics.F3Infopanel;
import main_pack.*;


import java.awt.*;
import java.util.Arrays;

public class GameState extends State{
    private CreatureHandler creatureHandler;
    private ProjectileHandler projectileHandler;
    private Player player;
    private F3Infopanel f3Infopanel;
    //-----------------------------
    //noch static access
    public static Map map;
    //------------------------------
    private Camera camera;
    public static Texture texture;

    public GameState(Game game){
        super(game);
        init();

    }

    //Getters
    public CreatureHandler getCreatureHandler() {
        return creatureHandler;
    }

    public Player getPlayer() {
        return player;
    }
    public ProjectileHandler getProjectileHandler() {
        return projectileHandler;
    }

    public Map getMap() { return map;}

    @Override
    public void init(){
        map=game.getMap();
        projectileHandler=game.getProjectileHandler();
        player=game.getPlayer();
        camera = game.getCamera();
        creatureHandler=game.getCreatureHandler();
        texture = new Texture();


        f3Infopanel=new F3Infopanel(this);

        //-----------------------------------------
        creatureHandler.addObject(player);
        creatureHandler.addObject(new GreenSlime(1,6));




    }


    @Override
    public void tick() {

        creatureHandler.tick();
        projectileHandler.tick();
        map.tick();
        f3Infopanel.tick();

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

        //Draw fixed
        Graphics2D gd2 = (Graphics2D) g;
        //Background
        g.setColor(new Color(10, 20, 30));
        g.fillRect(0, 0, Launcher.WIDTH, Launcher.HEIGHT);


        //Camera show
        //gd2.scale(camera.getSCALE(),camera.getSCALE());
        gd2.translate(camera.getX(), camera.getY()); //Cam start

        map.render(g);
        creatureHandler.render(g);
        projectileHandler.render(g);
        gd2.translate(-camera.getX(), -camera.getY());//Cam end

        if (KeyboardInput.f3_s){
            f3Infopanel.render(g);
        }




    }

}
