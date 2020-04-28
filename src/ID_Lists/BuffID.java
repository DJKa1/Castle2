package ID_Lists;

public enum BuffID {
    Poison,
    HealCoolDown,
    iced;

    public static boolean containsElement(String eingabe) {
        for (BuffID t : BuffID.values()) {
            if (t.toString().toLowerCase().contains(eingabe.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}
