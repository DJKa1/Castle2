package ID_Lists;



public enum ID {
    //Creatures
    Player(),
    GreenSlime(),
    Dúath_láma(),
    Spawner(),
    Slotmachine();




    public static boolean containsElement(String eingabe) {
        for (ID t : ID.values()) {
            if (t.toString().toLowerCase().contains(eingabe.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}
