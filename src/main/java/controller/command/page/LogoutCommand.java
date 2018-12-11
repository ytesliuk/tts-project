package controller.command.page;

import controller.ServletUtility;
import controller.command.Command;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Yuliia Tesliuk
 */
public class LogoutCommand implements Command {
    @Override
    public String process(HttpServletRequest request) {

        ServletUtility.logOut(request.getSession());
        return "redirect: /";

    }
}
