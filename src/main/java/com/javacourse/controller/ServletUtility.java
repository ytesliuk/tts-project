package com.javacourse.controller;

import com.javacourse.controller.SessionAttributeRetention;
import com.javacourse.model.entity.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Yuliia Tesliuk
 */
public class ServletUtility {
    private static ServletContext context = FrontServlet.getContext();

    public static void logIn(HttpServletRequest request, User user) {
        Map<Long,HttpSession> loggedUsers = getLoggedUsers();
        long userId = user.getId();

        if(loggedUsers.containsKey(userId)){
            loggedUsers.get(userId).invalidate();
        }

        loggedUsers.put(userId,request.getSession());
        setLoggedUsers(loggedUsers);

        sessionSetup(request, user);
    }

    public static void logOut(HttpSession session) {
        Map<Long,HttpSession> loggedUsers = getLoggedUsers();
        loggedUsers.remove(getUserId(session));
        setLoggedUsers(loggedUsers);
        session.removeAttribute("userId");
    }

    private static void sessionSetup(HttpServletRequest request, User user) {
        HttpSession session = request.getSession();
        session.setAttribute("userId", user.getId());
        Map<String, SessionAttributeRetention> sessionAttributes = new HashMap<>();
        session.setAttribute("sessionAttributes", sessionAttributes);
    }

    private static Map<Long,HttpSession> getLoggedUsers() {
        return (Map<Long, HttpSession>) context.getAttribute("loggedUsers");
    }
    private static void setLoggedUsers(Map<Long,HttpSession> loggedUsers) {
        context.setAttribute("loggedUsers", loggedUsers);
    }

    public static long getUserId(HttpServletRequest request) {
        return (long)(Optional.ofNullable(request.getSession().getAttribute("userId")).orElse(0L));
    }
    public static long getUserId(HttpSession session) {
        return (long)(Optional.ofNullable(session.getAttribute("userId")).orElse(0L));
    }

    public static void setSessionAttribute(HttpSession session, String attrName, Object attr, SessionAttributeRetention retention){
        session.setAttribute(attrName, attr);
        HashMap<String, SessionAttributeRetention> sessionAttributes = (HashMap<String, SessionAttributeRetention>)session.getAttribute("sessionAttributes");
        sessionAttributes.put(attrName, retention);
        session.setAttribute("sessionAttributes", sessionAttributes);

    }

    public static void cleanSession(HttpSession session, SessionAttributeRetention retention) {
        HashMap<String, SessionAttributeRetention> sessionAttributes = (HashMap<String, SessionAttributeRetention>) session.getAttribute("sessionAttributes");

        for(String s : sessionAttributes.keySet()) {
            if(sessionAttributes.get(s).equals(retention)){
                session.removeAttribute(s);
                sessionAttributes.remove(s);
            }
        }

        session.setAttribute("sessionAttributes", sessionAttributes);
    }
}
