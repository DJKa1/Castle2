package items.Quality;

import java.awt.*;

public class Fine extends Quality {

    public Fine(){
        super();
        color= Color.WHITE;
        minD=1.4f;
        maxD=1.8f;
        dmg=rollDmg();
    }
}
