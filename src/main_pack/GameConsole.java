package main_pack;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class GameConsole {
    protected Game game;
    protected KeyboardInput keyboardInput;
    private Color consoleColor=Color.white;
    private Method[] vallidMethods;
    private StringBuilder input;
    private String [] chatlog=new String[256];
    private Graphics g;
    private final Rectangle chatbox=new Rectangle(0,(Launcher.HEIGHT*8)/10,(Launcher.WIDTH*6)/10,Launcher.HEIGHT);
    private final int sx=(int) (chatbox.getX()+(chatbox.getWidth()/20)),sy=(int)(chatbox.getHeight()-(chatbox.getHeight()/20)),dy= (int) ((chatbox.getHeight()-chatbox.getY())/10);

    public GameConsole(Game game){
        this.game=game;
        keyboardInput=game.getKeyboardInput();
        vallidMethods=this.getClass().getDeclaredMethods();
        input=new StringBuilder();
    }
    public void appendInput(char c){
        input=input.append(c);
    }
    public void setInput(String str){
        input.delete(0,input.length());
        input.append(str);
    }
    public void send()  {
        String currentInput=input.toString();
        input.delete(0,input.length());
        //subStrings--------------------
        if (currentInput.startsWith("/")){
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
        String[] para;

        if (command.contains(" ")){
            String sub=null;
            sub=command.substring(1+ command.indexOf(" "));
            command=command.substring(1,command.indexOf(" "));
            para=sub.split(" ");
        }else {
            para=null;
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
        g.setColor(Color.BLACK);
        g.fillRect((int)chatbox.getX(),(int)chatbox.getY(),(int)chatbox.getWidth(),(int)chatbox.getHeight());
        g.setColor(consoleColor);
        g.drawString(input.toString(),sx,sy);
        for (int i=0 ;i<chatlog.length;i++){
            if (chatlog[i]!=null){
                g.drawString(chatlog[i],sx,sy-(dy*(i+1)));
            }
        }
    }
    public void getPlayerPosition(String str){
        setInput("X:"+game.getPlayer().getX()+" Y:"+ game.getPlayer().getY());
        send();
    }


}