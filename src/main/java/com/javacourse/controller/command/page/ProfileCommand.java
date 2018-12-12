package com.javacourse.controller.command.page;

import com.javacourse.controller.command.Command;
import com.javacourse.model.entity.User;
import com.javacourse.model.entity.User.Role;
import com.javacourse.controller.ServletUtility;

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
