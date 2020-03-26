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


    @Override
    public void init(){
        texture = new Texture();
        projectileHandler=new ProjectileHandler();
        player=new Player((float)3,3,projectileHandler, projectilePool);
        creatureHandler.addObject(player);
        creatureHandler.addObject(new GreenSlime(1,6));




    }


    @Override
    public void tick() {

        creatureHandler.tick();
        projectileHandler.tick();
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
        //gd2.scale(1d/(double) camera.getSCALE(),1d/(double)camera.getSCALE());
        //FPS and UPS
        g.setColor(Color.BLACK);
        g.fillRect(0,0,150,20);
        g.setColor(Color.white);
        g.drawString(String.format("FPS: %s, UPS: %s",Game.Frames,Game.Ticks),10,15);




    }

}
