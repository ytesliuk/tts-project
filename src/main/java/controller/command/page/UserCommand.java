package controller.command.page;

import controller.ServletUtility;
import controller.command.Command;
import model.entity.Task;
import model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Yuliia Tesliuk
 */
public class UserCommand implements Command {
    private List<Task> taskListAssigned;
    private List<Task> taskListInitiated;
    private List<Task> createdTask;


    @Override
    public String process(HttpServletRequest request) {
        setTaskLists(request);

        request.setAttribute("taskListAssigned", taskListAssigned);
        request.setAttribute("taskListInitiated", taskListInitiated);
        request.setAttribute("page","profile");

        return "/WEB-INF/user.jsp";
    }

    private void setTaskLists(HttpServletRequest request) {
        long userId = ServletUtility.getUserId(request);
        taskListAssigned = new UserService().getActiveTasksByOwner(userId);
        taskListInitiated = new UserService().getActiveTasksByCreator(userId);
    }
}
