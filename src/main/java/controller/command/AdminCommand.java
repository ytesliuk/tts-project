package controller.command;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Yuliia Tesliuk
 */
public class AdminCommand implements Command{
    @Override
    public String process(HttpServletRequest request) {
        return "/WEB-INF/admin.jsp";
    }
}
