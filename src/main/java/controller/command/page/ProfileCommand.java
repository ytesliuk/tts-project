package controller.command.page;

import controller.command.Command;
import model.entity.User;
import model.entity.User.Role;
import controller.ServletUtility;
import model.service.UserService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Yuliia Tesliuk
 */
public class ProfileCommand implements Command {

    @Override
    public String process(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");

        Role role = user.getRole();

        if(role == Role.ADMIN){
            return new AdminCommand().process(request);
        } else if(role == Role.USER){
            return new UserCommand().process(request);
        }

        return "redirect: /login";
    }
}
