package by.epam.javatraining.restaurant.controller;

import by.epam.javatraining.restaurant.command.Command;
import by.epam.javatraining.restaurant.command.CommandManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.javatraining.restaurant.util.Constant.*;

public class Controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) {
        String commandName = request.getParameter(getConst(PAR_COMMAND));

        if (commandName != null) {
            Command command = CommandManager.getCommand(commandName);

            String page = command.execute(request);

            RequestDispatcher dispatcher = request.getRequestDispatcher(page);

            try {
                dispatcher.forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
