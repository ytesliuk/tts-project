package controller.command;

import model.entity.Comment;
import model.entity.Task;
import model.service.TaskService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Yuliia Tesliuk
 */
public class TaskCommand implements Command {
    private Task task;
    private List<Comment> comments;
    private TaskService taskService = new TaskService();

    @Override
    public String process(HttpServletRequest request) {
        setTask(request);
        request.getSession().setAttribute("task", task);
        request.setAttribute("comments", comments);

        return "/WEB-INF/task.jsp";
    }

    private void setTask(HttpServletRequest request) {
        long id = Long.parseLong(request.getRequestURI().replaceAll(".*/tts/task-",""));
        task = taskService.findTask(id);
        comments = taskService.getTaskComments(task);
    }
}
