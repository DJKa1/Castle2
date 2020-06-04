package ID_Lists;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public enum ID {
    //Creatures
    Player(),
    GreenSlime(),
    Dúath_láma(),
    Spawner(),
    Slotmachine(),
    Shop(),
    PoisonGroundEffect();




    public static boolean containsElement(String eingabe) {
        for (ID t : ID.values()) {
            if (t.toString().toLowerCase().contains(eingabe.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public static ID[] getValues(ID exept){
        LinkedList<ID> l = new LinkedList<>();
        for (ID t : ID.values()) {
            if (t!= exept){
                l.add(t);
            }
        }
        return null;
    }
}