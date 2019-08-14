package by.epam.javatraining.restaurant.util;

import java.util.ResourceBundle;

public class Constant {
    private static ResourceBundle resourceBundle;

    //parameters
    public static final String PAR_COMMAND = "par.command";
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
    public static final String ATR_USERS = "atr.users";
    public static final String ATR_DISHES = "atr.dishes";
    public static final String ATR_ALL_ORDERS = "atr.all_orders";
    public static final String ATR_USER_ORDER = "atr.user_order";
    public static final String ATR_ORDER_DISHES = "atr.order_dishes";
    public static final String ATR_ORDER = "atr.order";
    public static final String ATR_LOCALE = "atr.locale";

    //logger
    public static final String LOG_SIGN_UP_START = "log.sign_up_start";
    public static final String LOG_SIGN_UP_LOGIN = "log.sign_up_login";
    public static final String LOG_SIGN_OUT_START = "log.sign_out_start";
    public static final String LOG_SIGN_IN_LOGIN = "log.sign_in_login";
    public static final String LOG_SIGN_IN_PASSWORD = "log.sign_in_password";
    public static final String LOG_CHECK_OUT_ADD = "log.check_out_add";
    public static final String LOG_CHECK_OUT_CLR = "log.check_out_clr";


    //pages
    public static final String PAGE_MAIN = "page.main";
    public static final String PAGE_LOGIN = "page.login";
    public static final String PAGE_INVALID_LOGIN = "page.invalid_login";
    public static final String PAGE_REGISTRATION = "page.registration";
    public static final String PAGE_INVALID_REGISTRATION = "page.invalid_registration";
    public static final String PAGE_MENU = "page.menu";
    public static final String PAGE_ACCOUNT = "page.account";
    public static final String PAGE_RESERVATION = "page.reservation";
    public static final String PAGE_CLIENT_ORDER = "page.client_order";
    public static final String PAGE_USERS_LIST = "page.users_list";
    public static final String PAGE_COURIER_ORDER = "page.courier_order";
    public static final String PAGE_ADD_DISH = "page.add_dish";
    public static final String PAGE_INVALID_ADD_DISH = "page.invalid_add_dish";

    //filter
    public static final String LOG_CHARSET_FILTER_INIT = "log.charset_filter_init";
    public static final String PAR_REQUEST_ENCODING = "par.request_encoding";
    public static final String FILTER_ENCODING = "filter.encoding";
    public static final String LOG_CHARSET_FILTER_DESTROY = "log.charset_filter_destroy";

    public static final String USER_DEFAULT_PHOTO = "user.default_photo";

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
