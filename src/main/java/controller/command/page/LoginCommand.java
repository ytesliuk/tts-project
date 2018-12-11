package controller.command.page;

import controller.command.Command;
import model.entity.User;
import model.entity.User.Role;
import controller.ServletUtility;
import model.service.SecurityService;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author Yuliia Tesliuk
 */
public class LoginCommand implements Command {
    @Override
    public String process(HttpServletRequest request) {
        forceLogOut(request);

        User user = authorization(request);

        ServletUtility.logIn(request, user);

        request.getSession().setAttribute("user", user);

        return "redirect: /profile";
    }

    /**
     * Logging out a user which is currently logged in under the session if any.
     * @param request needed to retrieve the session.
     */
    private void forceLogOut(HttpServletRequest request) {
        if(Objects.nonNull(ServletUtility.getUserName(request))){
            ServletUtility.logOut(request.getSession());
        }
    }

    private User authorization(HttpServletRequest request) {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        return new SecurityService().userValidation(name, password);
    }

}
