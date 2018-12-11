package controller.command.page;

import controller.command.Command;

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
