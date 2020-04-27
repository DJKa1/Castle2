package ID_Lists;

public enum QualityID {
    Primitiv,
    Ramshackle,
    Fine,
    Outstanding,
    Extraordinary;


    public static boolean containsElement(String eingabe) {
        for (QualityID t : QualityID.values()) {
            if (t.toString().toLowerCase().contains(eingabe.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}
