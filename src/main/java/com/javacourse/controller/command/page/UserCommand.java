package com.javacourse.controller.command.page;

import com.javacourse.controller.ServletUtility;
import com.javacourse.controller.command.Command;
import com.javacourse.model.entity.Task;
import com.javacourse.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yuliia Tesliuk
 */
public class UserCommand implements Command {

    @Override
    public String process(HttpServletRequest request) {
        long userId = ServletUtility.getUserId(request);

        request.setAttribute("taskListAssigned", setTaskListAssigned(userId));
        request.setAttribute("taskListInitiated", setTaskListInitiated(userId));
        request.setAttribute("taskListWatched", setTaskListWatched(userId));

        request.setAttribute("page","profile");

        return "/WEB-INF/user.jsp";
    }

    private List<Task> setTaskListAssigned(long userId) {
        return new UserService().getActiveTasksByOwner(userId);
    }

    private List<Task> setTaskListInitiated(long userId) {
        return new UserService().getActiveTasksByCreator(userId);
    }

    private List<Task> setTaskListWatched(long userId) {
        return new ArrayList<>(); //TODO watch list
    }
}
