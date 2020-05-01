package ID_Lists;

public enum ProjectileID {
    Shotgunbolt,
    IceBall,
    IceShard,
    Plasmabolt,
    SniperBullet,
    Bounce;

    public static boolean containsElement(String eingabe) {
        for (ID t : ID.values()) {
            if (t.toString().toLowerCase().contains(eingabe.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}
