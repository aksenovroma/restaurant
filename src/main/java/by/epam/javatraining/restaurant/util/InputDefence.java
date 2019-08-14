package by.epam.javatraining.restaurant.util;

public class InputDefence {
    public static String scriptPrevention(String str) {
        if (str != null) {
            str = str.replace("&", "&amp")
                    .replace("<", "&lt")
                    .replace(">", "&gt");
        }
        return str;
    }
}
