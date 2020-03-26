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
    private Camera camera;
    public static Map map;
    private Player player;
    private final ID[] EXCLUDE=null;
    private F3Infopanel f3Infopanel;
    public static Texture texture;
    public ProjectilePool projectilePool;






    public GameState(Game game){
        super(game);
        creatureHandler=new CreatureHandler();
        map=new Map("testMap");
        camera = new Camera(0,0);
        projectilePool=new ProjectilePool();
        init();

    }

    //Getters


    public CreatureHandler getCreatureHandler() {
        return creatureHandler;
    }

    public ProjectileHandler getProjectileHandler() {
        return projectileHandler;
    }

    public Player getPlayer() {
        return player;
    }



    @Override
    public void init(){
        texture = new Texture();
        projectileHandler=new ProjectileHandler();
        player=new Player((float)3,3,projectileHandler, projectilePool);
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

        if (KeyboardInput.f3G){
            f3Infopanel.render(g);
        }




    }

}
