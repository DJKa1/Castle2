package ID_Lists;

public enum ItemID {
    testWeapon,
    HealPotion,
    IceStorm,
    Shotgun,
    SniperAmmo,
    ShotgunAmmo,
    FabricatedSniper,
    EchoStaff,
    Boots,
    Helmet,
    ChestPlate,
    OutstandingLootCreate,
    MoldyBox,
    MagicLootCreate;



    public static boolean containsElement(String eingabe) {
        for (ItemID t : ItemID.values()) {
            if (t.toString().toLowerCase().contains(eingabe.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}