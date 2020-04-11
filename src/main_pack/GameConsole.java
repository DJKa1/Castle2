package main_pack;
import ID_Lists.BuffID;
import com.sun.jdi.IntegerValue;
import entities.creatures.GreenSlime;
import entities.creatures.Player;
import ID_Lists.ItemID;

import javax.print.DocFlavor;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class GameConsole {
    protected Game game;
    protected KeyboardInput keyboardInput;
    private Color consoleColor=Color.white;
    private int fontsize=14;
    private Font consoleFont=new Font("Arial",fontsize,fontsize);
    private Method[] vallidMethods;
    private StringBuilder input;
    private String [] chatlog=new String[9];
    private Graphics g;
    private final Rectangle chatbox=new Rectangle(0,(Launcher.HEIGHT*8)/10,(Launcher.WIDTH*6)/10,Launcher.HEIGHT);
    private final int sx=(int) (chatbox.getX()+(chatbox.getWidth()/20)),sy=(int)(chatbox.getHeight()-(chatbox.getHeight()/20)),dy= (int) ((chatbox.getHeight()-chatbox.getY())/10);
    private Player player;

    public GameConsole(Game game){
        this.game=game;
        keyboardInput=game.getKeyboardInput();
        vallidMethods=this.getClass().getDeclaredMethods();
        input=new StringBuilder();
        player=game.getPlayer();
    }

    public Color getConsoleColor(){
        return consoleColor;
    }
    public Font getConsoleFont(){
        return consoleFont;
    }
    public void appendInput(char c){
        input=input.append(c);
    }
    public void deletelastKey(){
        input.deleteCharAt(input.length()-1);
    }
    public void setInput(String str){
        input.delete(0,input.length());
        input.append(str);
    }
    public void clearInput() {
        input.delete(0,input.length());
    }
    public void send()  {
        String currentInput=input.toString();
        input.delete(0,input.length());
        //subStrings--------------------
        if (currentInput.startsWith("/")&&Launcher.enablecheats){
            executeCommand(currentInput);

        }else{
            for (int i =0; i<chatlog.length-1;i++){
                int l =chatlog.length-1;
                chatlog[l-i]=chatlog[l-1-i];
            }
            chatlog[0]= currentInput;
        }
    }
    public void executeCommand(String command){
        String[] para ;

        if (command.contains(" ")){
            String sub=null;
            sub=command.substring(1+ command.indexOf(" "));
            command=command.substring(1,command.indexOf(" "));
            para=sub.split(" ");
        }else {
            command=command.substring(1);
            para=new String[0];
        }

        for (Method m :vallidMethods){

            if (command.equals(m.getName())){
                if(m.getParameterCount()==para.length){
                    try {
                        m.invoke(this,para);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }

                }else {
                    setInput("Wrong number of Arguments given");
                    send();
                }
            }
        }
    }
    public void render(Graphics g){
        g.setFont(consoleFont);
        g.setColor(Color.BLACK);
        g.fillRect((int)chatbox.getX(),(int)chatbox.getY(),(int)chatbox.getWidth(),(int)chatbox.getHeight());
        g.setColor(consoleColor);
        g.drawString(input.toString(),sx,sy);

    }

    public void renderLog(Graphics g){
        g.setFont(consoleFont);
        g.setColor(consoleColor);
        for (int i=0 ;i<chatlog.length;i++){
            if (chatlog[i]!=null){
                g.drawString(chatlog[i],sx,sy-(dy*(i+1)));
            }
        }
    }

    //Commands--------------------------------------------------------------------
    public void getPlayerPosition(){
        setInput("Player is at X:"+player.getX()+"and Y:"+ player.getY());
        send();
    }

    public void tp(String x, String y){
        try {
            player.setX(Float.valueOf(x));
            player.setY(Float.valueOf(y));
            setInput("/getPlayerPosition");
        }catch (IllegalArgumentException e){
            setInput("Arguments must be float float");

        }
        send();
    }

    public void activateHackerman(){
        consoleColor=Color.GREEN;
        setInput("YOU NOW POSSES THE POWER OF THE INTERNET");
        send();
    }

    public void give(String id)  {
        if (ItemID.containsElement(id)){
            player.getInventory().addItembyID(id);
            setInput(id + " added to Inventory");
        }else{
            setInput("No Valid Item Id");
        }
        send();
    }


    public void clear(){
        setInput(String.valueOf(player.getInventory().getItemCount())+ " Items cleared");
        player.getInventory().clearInventory();
        send();
    }

    public void spawn(String id ,String xpos , String ypos){
        try {
            switch (id){
                default:setInput("No valid MobID");
                case "GreenSlime": game.getCreatureHandler().addObject(new GreenSlime(Float.valueOf(xpos),Float.valueOf(ypos),game.getCreatureHandler(),game.getProjectileHandler()));break;

            }
        }catch (IllegalArgumentException e){
            setInput("Arguments must be String float float");
        }
        send();
    }
    public void sysout(String msg) {
        System.out.println(msg);
    }

    public void giveEffect(String id ,String sec, String lvl){
        try {
            if (BuffID.containsElement(id)) {
                player.addBuffbyID(id, Integer.valueOf(sec) * 60, Integer.valueOf(lvl));
                setInput(String.format("%s Effect active for %d seconds at strength %d", id, Integer.valueOf(sec), Integer.valueOf(lvl)));
            } else {
                setInput("No Valid Effect Id");
            }
        }catch (Exception e){
            setInput("Arguments must be String int int ");
        }
        send();
    }
}