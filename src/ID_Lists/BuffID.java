package ID_Lists;

import Buffs.Buff;

public enum BuffID {
    Poison;

    public static boolean containsElement(String eingabe) {
        for (BuffID t : BuffID.values()) {
            if (t.toString().toLowerCase().contains(eingabe.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}
