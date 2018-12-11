package controller.command.action;

import controller.command.Command;
import model.entity.*;
import model.service.TaskService;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

/**
 * @author Yuliia Tesliuk
 */
public class CommentCommand implements Command {
    @Override
    public String process(HttpServletRequest request) {
        Task task = (Task)request.getSession().getAttribute("task");

        saveComment(request, task);

        return "redirect: /task-" + task.getId();
    }

    private void saveComment(HttpServletRequest request, Task task) {
        TaskService ts = new TaskService();

        Comment comment = Comment.builder()
                .recorder((User) request.getSession().getAttribute("user"))
                .recordTime(Instant.now())
                .task(task)
                .comment(request.getParameter("comment"))
                //TODO quot comment
                .build();
        ts.saveTaskComment(comment);
    }
}
