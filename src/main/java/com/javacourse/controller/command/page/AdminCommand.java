package com.javacourse.controller.command.page;

import com.javacourse.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Yuliia Tesliuk
 */
public class AdminCommand implements Command {
    @Override
    public String process(HttpServletRequest request) {
        request.setAttribute("page","profile");

        return "/WEB-INF/admin.jsp";
    }
}
