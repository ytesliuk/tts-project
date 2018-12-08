package controller.command;

import model.entity.User;
import model.entity.User.Role;
import controller.ServletUtility;
import model.service.SecurityService;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author Yuliia Tesliuk
 */
public class LoginCommand implements Command{
    @Override
    public String process(HttpServletRequest request) {
        forceLogOut(request);

        String name = request.getParameter("name");
        String password = request.getParameter("password");

        User user = authorization(name, password);
        ServletUtility.logIn(request, user);

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

    private User authorization(String name, String password) {
        return new SecurityService().userValidation(name, password);
    }

}
