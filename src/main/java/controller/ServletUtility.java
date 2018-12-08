package controller;

import model.entity.User;
import model.entity.User.Role;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

/**
 * @author Yuliia Tesliuk
 */
public class ServletUtility {
    private static ServletContext context = FrontServlet.getContext();

    public static void logIn(HttpServletRequest request, User user) {
        Map<String,HttpSession> loggedUsers = getLoggedUsers();
        String login = user.getLogin();

        if(loggedUsers.containsKey(login)){
            loggedUsers.get(login).invalidate();
        }

        loggedUsers.put(login,request.getSession());
        setLoggedUsers(loggedUsers);

        sessionSetup(request, user);
    }

    public static void logOut(HttpSession session) {
        Map<String,HttpSession> loggedUsers = getLoggedUsers();
        loggedUsers.remove(getUserName(session));
        setLoggedUsers(loggedUsers);
        session.removeAttribute("userName");
        session.removeAttribute("userRole");
        session.removeAttribute("userId");
    }

    private static void sessionSetup(HttpServletRequest request, User user) {
        HttpSession session = request.getSession();
        session.setAttribute("userName", user.getLogin());
        session.setAttribute("userRole", user.getRole());
        session.setAttribute("userId", user.getId());
    }

    private static Map<String,HttpSession> getLoggedUsers() {
        return (Map<String, HttpSession>) context.getAttribute("loggedUsers");
    }
    private static void setLoggedUsers(Map<String,HttpSession> loggedUsers) {
        context.setAttribute("loggedUsers", loggedUsers);
    }

    public static String getUserName(HttpServletRequest request) {
        return (String) request.getSession().getAttribute("userName");
    }

    public static String getUserName(HttpSession session) {
        return (String) session.getAttribute("userName");
    }

    public static Role getUserRole(HttpServletRequest request) {
        return (Role) request.getSession().getAttribute("userRole");
    }

    public static long getUserId(HttpServletRequest request) {
        return (long) request.getSession().getAttribute("userId");
    }
}
