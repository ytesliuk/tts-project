package com.javacourse.controller.command.action;

import com.javacourse.controller.command.Command;
import com.javacourse.model.entity.*;
import com.javacourse.model.service.TaskService;

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

        TaskComment comment = new TaskCommentBuilder()
                .setRecorder((User) request.getSession().getAttribute("user"))
                .setRecordTime(Instant.now())
                .setTask(task)
                .setComment(request.getParameter("comment"))
                //TODO quot comment
                .build();

        ts.saveTaskComment(comment);
    }
}
