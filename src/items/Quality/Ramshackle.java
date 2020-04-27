package items.Quality;

import java.awt.*;

public class Ramshackle extends Quality {


    public Ramshackle(){
        super();
        color= Color.GREEN;
        minD=1;
        maxD=1.4f;
        dmg=rollDmg();
    }
}
