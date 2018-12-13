package com.javacourse.controller.command.page;

import com.javacourse.controller.SessionAttributeRetention;
import com.javacourse.controller.command.Command;
import com.javacourse.model.entity.User;
import com.javacourse.controller.ServletUtility;
import com.javacourse.model.service.SecurityService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Yuliia Tesliuk
 */
public class LoginCommand implements Command {
    @Override
    public String process(HttpServletRequest request) {
        forceLogOut(request);

        User user = authorization(request);

        logIn(request, user);

        request.getSession().setAttribute("user", user);

        return "redirect: /profile";
    }

    /**
     * Logging out a user which is currently logged in under the session if any.
     * @param request needed to retrieve the session.
     */
    private void logIn(HttpServletRequest request, User user) {
        Map<Long, HttpSession> loggedUsers = ServletUtility.getLoggedUsers();
        long userId = user.getId();

        if(loggedUsers.containsKey(userId)){
            loggedUsers.get(userId).invalidate();
        }

        loggedUsers.put(userId,request.getSession());
        ServletUtility.setLoggedUsers(loggedUsers);

        sessionSetup(request, user);
    }

    private static void sessionSetup(HttpServletRequest request, User user) {
        HttpSession session = request.getSession();
        session.setAttribute("userId", user.getId());
        Map<String, SessionAttributeRetention> sessionAttributes = new HashMap<>();
        session.setAttribute("sessionAttributes", sessionAttributes);
    }

    private void forceLogOut(HttpServletRequest request) {
        if(ServletUtility.getUserId(request) != 0){
            ServletUtility.logOut(request.getSession());
        }
    }

    private User authorization(HttpServletRequest request) {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        return new SecurityService().userValidation(name, password);
    }

}
