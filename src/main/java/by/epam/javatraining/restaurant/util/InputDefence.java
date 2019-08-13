package by.epam.javatraining.restaurant.util;

public class InputDefence {
    public static String scriptPrevention(String str) {
        if (str != null) {
            str = str.replace("/&/g", "&amp;")
                    .replace("/</g", "&lt;")
                    .replace("/>/g", "&gt;");
        }
        return str;
    }
}
