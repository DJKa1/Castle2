package States;

import Handler.CreatureHandler;
import Handler.Effectshandler;
import Handler.ProjectileHandler;
import Maps.Map;
import PlayerGui.PlayerGUI;
import entities.creatures.Spawner;
import entities.creatures.*;
import graphics.Camera;
import graphics.Texture;
import ID_Lists.ID;
import graphics.F3Infopanel;
import main_pack.*;


import java.awt.*;

public class GameState extends State{
    private CreatureHandler creatureHandler;
    private ProjectileHandler projectileHandler;
    private Effectshandler effectshandler;
    private Player player;
    private PlayerGUI playerGUI;
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
        effectshandler = game.getEffectshandler();
        player= game.getPlayer();
        playerGUI = new PlayerGUI(player);
        camera = game.getCamera();
        creatureHandler=game.getCreatureHandler();
        texture = new Texture();
        f3Infopanel=new F3Infopanel(this);
        //------------------------------------------------------------------

        creatureHandler.addObject(new Spawner(30,30,game));
        creatureHandler.addObject(new Slotmachine(36,36,game));
        creatureHandler.addObject(player);
        /*
        Dúath_láma g =new Dúath_láma(10,3,game);
        creatureHandler.addObject(g);
        Dúath_láma c=new Dúath_láma(15,12,game);
        creatureHandler.addObject(c);

        for ( int i= 0 ; i< 50 ; i++){
            Creature c= new GreenSlime((int)(Math.random()*14),(int)(Math.random()*14),game);
            creatureHandler.addObject(c);
            c.setCurrentTarget(g);
        }

         */


    }
    @Override
    public void tick() {
        creatureHandler.tick();
        projectileHandler.tick();
        effectshandler.tick();
        map.tick();
        f3Infopanel.tick();

        Creature tempPlayer=null;
        for (int i = 0; i< CreatureHandler.creatures.size(); i++){
            if(CreatureHandler.creatures.get(i).getId()== ID.Player) {
                tempPlayer = CreatureHandler.creatures.get(i);
            }
        }

        camera.tick(tempPlayer);
    }
    @Override
    public void render(Graphics g) {
        //Draw fixed
        Graphics2D gd2 = (Graphics2D) g;
        //Background
        g.setColor(new Color(21, 10, 31));
        g.fillRect(0, 0, Launcher.WIDTH, Launcher.HEIGHT);

        //Cam start-------------------------------------------------------
        gd2.translate(camera.getX(), camera.getY());

        map.render(g);
        creatureHandler.render(g);
        projectileHandler.render(g);
        effectshandler.render(g);

        gd2.translate(-camera.getX(), -camera.getY());
        //Cam end--------------------------------------------------------------

        if (KeyboardInput.f3_s) {
            f3Infopanel.render(g);
        }
        game.getGameConsole().renderLog(g);
        player.getInventory().getHotbar().render(gd2);
        playerGUI.render(g);

    }

}
