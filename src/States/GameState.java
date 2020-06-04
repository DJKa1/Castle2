package States;

import Handler.CreatureHandler;
import Handler.Effectshandler;
import Handler.ProjectileHandler;
import Maps.Map;
import PlayerGui.PlayerGUI;
import Sound.Sound;
import entities.creatures.*;
import entities.creatures.shop.Shop;
import entities.creatures.slotmachine.Slotmachine;
import entities.groundEffect.PoisonGroundEffect;
import graphics.Camera;
import graphics.Texture;
import ID_Lists.ID;
import graphics.F3Infopanel;
import main_pack.*;


import java.awt.*;
import java.util.LinkedList;

public class GameState extends State{
    private CreatureHandler creatureHandler;
    private ProjectileHandler projectileHandler;
    private Effectshandler effectshandler;
    private LinkedList<Spawner>spawnerList;
    private Player player;
    private PlayerGUI playerGUI;
    private F3Infopanel f3Infopanel;
    //Wave-System ----------------------
    private int wave=1;
    public double xp;
    //-----------------------------
    // noch static access
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

    public int getWave(){
        return wave;
    }

    public void addXp(double v ){
        xp+=v;
    }

    private void setWave(int v){
        wave=v;
    }

    private void increaseWave(){
        wave++;
        for (Spawner s: spawnerList){
            s.increaselvl(1);
        }
        Sound.playSound("LevelUp");
    }
    @Override
    public void init(){
        map=game.getMap();
        projectileHandler=game.getProjectileHandler();
        effectshandler = game.getEffectshandler();
        player= game.getPlayer();
        playerGUI = new PlayerGUI(player);
        camera = game.getCamera();
        creatureHandler=game.getCreatureHandler();
        spawnerList=new LinkedList<>();
        texture = new Texture();
        f3Infopanel=new F3Infopanel(this);
        //------------------------------------------------------------------

        Spawner spawner1=new Spawner(25.5f,20,1,new GreenSlime(0,0,25,game),game);
        creatureHandler.addObject(spawner1);
        spawnerList.add(spawner1);
        creatureHandler.addObject(new Shop(24,30,game));
        creatureHandler.addObject(new Slotmachine(36,31,game));
        creatureHandler.addObject(player);

        creatureHandler.addObject(new PoisonGroundEffect(30,30,game));

        /*
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


        if (xp>= getXpToWave(wave)){
            increaseWave();
        }
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

    public int getXpToWave(int w) {
        return (int)((Math.pow(w-50d,3d)/10000d + w*0.5d+12.5d)*1000d);
    }


}
