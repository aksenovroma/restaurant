package by.epam.javatraining.restaurant.util;

import java.util.ResourceBundle;

public class Constant {
    private static ResourceBundle resourceBundle;

    //parameters
    public static final String PAR_NAME = "par.name";
    public static final String PAR_LOGIN = "par.login";
    public static final String PAR_PASSWORD = "par.password";
    public static final String PAR_REMOVE_ORDER = "par.remove_order";
    public static final String PAR_ADD_ACTION = "par.add_action";
    public static final String PAR_REMOVE_ACTION = "par.remove_action";
    public static final String PAR_RES_ACTION = "par.res_action";
    public static final String PAR_REMOVE_DISH_ACTION = "par.remove_dish_action";
    public static final String PAR_CLR_ACTION = "par.clr_action";
    public static final String PAR_ADDRESS = "par.address";
    public static final String PAR_USER_ROLE_CLIENT = "par.user_role_client";
    public static final String PAR_USER_ROLE_COURIER = "par.user_role_courier";
    public static final String PAR_LOCALIZATION = "par.localization";
    public static final String PAR_CATEGORY = "par.category";
    public static final String PAR_WEIGHT = "par.weight";
    public static final String PAR_PRICE = "par.price";
    public static final String PAR_IMAGE_URL = "par.image_url";
    public static final String PAR_DESCRIPTION = "par.description";
    public static final String PAR_ACCEPT_ORDER = "par.accept_order";

    //attributes
    public static final String ATR_ID_USER = "atr.iduser";
    public static final String ATR_USER_NAME = "atr.username";
    public static final String ATR_LOGIN = "atr.login";
    public static final String ATR_PASSWORD = "atr.password";
    public static final String ATR_USER_PHOTO = "atr.userphoto";
    public static final String ATR_ROLE = "atr.role";
    




    static {
        resourceBundle = ResourceBundle.getBundle("constant");
    }

    public static String getConst(String key) {
        if (key != null) {
            return resourceBundle.getString(key);
        }
        return null;
    }
}
