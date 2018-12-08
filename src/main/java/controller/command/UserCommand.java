package controller.command;

import controller.ServletUtility;
import model.entity.Task;
import model.service.TaskService;
import model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Yuliia Tesliuk
 */
public class UserCommand implements Command {
    private List<Task> assignedTask;
    private List<Task> createdTask;

    @Override
    public String process(HttpServletRequest request) {
        setTaskLists(request);

        request.setAttribute("assignedTask", assignedTask);
        request.setAttribute("createdTask", createdTask);

        return "/WEB-INF/user.jsp";
    }

    private void setTaskLists(HttpServletRequest request) {
        long userId = ServletUtility.getUserId(request);
        assignedTask = new UserService().getActiveTasksByOwner(userId);
        createdTask = new UserService().getActiveTasksByCreator(userId);
    }
}
