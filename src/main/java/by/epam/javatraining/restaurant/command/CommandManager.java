package by.epam.javatraining.restaurant.command;

import java.util.HashMap;
import java.util.Map;

public class CommandManager {
    public enum CommandType {
        UNKNOWN, SIGN_IN, SIGN_UP, SIGN_OUT, SHOW_MENU, ACCOUNT_ACTION, DISH_ACTION, CHECK_OUT, SHOW_ORDER,
        REMOVE_ORDER
    }

    private static final Map<CommandType, Command> commandMap;

    static {
        commandMap = new HashMap<>();
        commandMap.put(CommandType.SIGN_UP, new SignUpCommand());
        commandMap.put(CommandType.SIGN_IN, new SignInCommand());
        commandMap.put(CommandType.SIGN_OUT, new SignOutCommand());
        commandMap.put(CommandType.ACCOUNT_ACTION, new AccountActionCommand());
        commandMap.put(CommandType.SHOW_MENU, new ShowMenuCommand());
        commandMap.put(CommandType.DISH_ACTION, new DishActionCommand());
        commandMap.put(CommandType.CHECK_OUT, new CheckOutCommand());
        commandMap.put(CommandType.SHOW_ORDER, new ShowOrderCommand());
        commandMap.put(CommandType.REMOVE_ORDER, new RemoveOrderCommand());
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
