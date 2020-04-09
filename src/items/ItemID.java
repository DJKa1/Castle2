package items;

public enum ItemID {
    testWeapon;


    public static boolean containsElement(String eingabe) {
        for (ItemID t : ItemID.values()) {
            if (t.toString().toLowerCase().contains(eingabe.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}