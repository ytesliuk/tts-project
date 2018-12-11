package controller.command.page;

import controller.ServletUtility;
import controller.command.Command;
import model.entity.Task;
import model.service.UserService;

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
