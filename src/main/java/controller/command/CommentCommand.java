package controller.command;

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
        TaskService ts = new TaskService();
        Task task = (Task)request.getSession().getAttribute("task");

        Comment comment = Comment.builder()
                .recorder((User) request.getSession().getAttribute("user"))
                .recordTime(Instant.now())
                .task(task)
                .comment(request.getParameter("comment"))
                //TODO quot comment
                .build();
        ts.saveTaskComment(comment);
        return "redirect: /task-" + task.getId();
    }
}
