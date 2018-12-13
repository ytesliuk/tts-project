package com.javacourse.controller;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@WebListener
public class SessionListener implements HttpSessionListener {

    private final Logger logger = LoggerFactory.getLogger(SessionListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        logger.debug("Number of logged users before session destroy: " + ((Map<Long, HttpSession>) FrontServlet.getContext().getAttribute("loggedUsers")).size());
        ServletUtility.logOut(httpSessionEvent.getSession());
        logger.debug("Number of logged users after session destroy: " + ((Map<Long, HttpSession>) FrontServlet.getContext().getAttribute("loggedUsers")).size());
    }
}
