package by.epam.javatraining.restaurant.command;

import java.util.HashMap;
import java.util.Map;

public class CommandManager {
    public enum CommandType {
        UNKNOWN, SIGN_IN, SIGN_UP, SIGN_OUT, SHOW_MENU, DISH_ACTION, CHECK_OUT, SHOW_ORDER,
        REMOVE_ORDER, REMOVE_DISH, ADD_DISH, SHOW_USERS, CHANGE_ROLE, ACCEPT_ORDER
    }

    private static final Map<CommandType, Command> commandMap;

    static {
        commandMap = new HashMap<>();
        commandMap.put(CommandType.SIGN_UP, new SignUpCommand());
        commandMap.put(CommandType.SIGN_IN, new SignInCommand());
        commandMap.put(CommandType.SIGN_OUT, new SignOutCommand());
        commandMap.put(CommandType.SHOW_MENU, new ShowMenuCommand());
        commandMap.put(CommandType.DISH_ACTION, new DishActionCommand());
        commandMap.put(CommandType.CHECK_OUT, new CheckOutCommand());
        commandMap.put(CommandType.SHOW_ORDER, new ShowOrderCommand());
        commandMap.put(CommandType.REMOVE_ORDER, new RemoveOrderCommand());
        commandMap.put(CommandType.REMOVE_DISH, new RemoveDishCommand());
        commandMap.put(CommandType.ADD_DISH, new AddDishCommand());
        commandMap.put(CommandType.SHOW_USERS, new ShowUsersCommand());
        commandMap.put(CommandType.CHANGE_ROLE, new ChangeRoleCommand());
        commandMap.put(CommandType.ACCEPT_ORDER, new AcceptOrderCommand());
    }

    public static Command getCommand(String commandName) {
        CommandType commandType = CommandType.UNKNOWN;

        try {
            commandType = CommandType.valueOf(commandName.toUpperCase());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return commandMap.get(commandType);
    }
}
