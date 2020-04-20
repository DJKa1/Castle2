package ID_Lists;

public enum ItemID {
    testWeapon,
    IceStorm,
    Shotgun;



    public static boolean containsElement(String eingabe) {
        for (ItemID t : ItemID.values()) {
            if (t.toString().toLowerCase().contains(eingabe.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}