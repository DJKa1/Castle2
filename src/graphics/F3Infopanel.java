package graphics;
import States.GameState;
import main_pack.Game;
import main_pack.Launcher;

import java.awt.*;


public class F3Infopanel {
    private GameState gameState;
    private String[] information=new String[64];
    private int sx=10,sy=20,fontsize=10;
    private Font font=new Font("Arial",fontsize,fontsize);

    public F3Infopanel(GameState gameState){
        this.gameState=gameState;
    }
    public void updateInfo(){
        information[0]="Running Castel2 "+Launcher.VERSION+ " FPS:"+Game.Frames +" UPS:"+Game.Ticks;
        information[1]=Launcher.WIDTH+"X"+Launcher.HEIGHT;
        information[2]="Player X:"+ gameState.getPlayer().getX() +" Y:" +gameState.getPlayer().getY();
        information[3]="CreatureCount:"+ gameState.getCreatureHandler().getCreatureCount();
        information[4]="ProjectileCount:"+ gameState.getProjectileHandler().getProjectileCount();
        information[5]=null;
    }

    public void tick(){
        updateInfo();
    }


    public void render(Graphics g){
        g.setColor(Color.PINK);
        g.setFont(font);
         for ( int i=0;i<information.length;i++){
             if(information[i]!=null){
                 g.drawString(information[i],sx,sy+i*fontsize);

             }

        }

    }
}
