package controller;

import controller.command.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Yuliia Tesliuk
 */

@WebServlet("/")
public class FrontServlet extends HttpServlet {
    private Map<String, Command> commands = new ConcurrentHashMap<>();
    private static ServletContext context;

    @Override
    public void init(ServletConfig servletConfig){
        context = servletConfig.getServletContext();

        Map<String,HttpSession> loggedUsers = new ConcurrentHashMap<>();
        context.setAttribute("loggedUsers", loggedUsers);

        commands.put("login", new LoginCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("profile", new ProfileCommand());
        commands.put("task", new TaskCommand());
        commands.put("comment", new CommentCommand());
        commands.put("new_task", new NewTaskCommand());
        commands.put("create", new SaveTaskCommand());
        commands.put("searchUser",new SearchUserCommand());
        //TODO: add new commands to map
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    public static ServletContext getContext() {
        return context;
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Command command = getCommand(request);
        String page = command.process(request);

        if (page.contains("redirect: ")) {
            response.sendRedirect(request.getContextPath() + page.replaceAll("redirect: ", ""));
        } else {
            request.getRequestDispatcher(page).forward(request,response);
        }
    }

    private Command getCommand(HttpServletRequest request){

        String path = request.getRequestURI();
        path = path.replaceAll(".*/tts/" , "");
        if (path.matches(".*task-[0-9]*")){
            path = "task";
        }

        return commands.getOrDefault(path, (r)->"/index.jsp");
    }
}
