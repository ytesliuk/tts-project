package com.javacourse.controller.command.action;

import com.javacourse.controller.command.Command;
import com.javacourse.model.entity.*;
import com.javacourse.model.service.TaskService;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

/**
 * @author Yuliia Tesliuk
 */
public class SaveTaskCommand implements Command {

    @Override
    public String process(HttpServletRequest request) {
        Task task = saveTask(request);

        request.setAttribute("task", task);
        return "redirect: /task-" + task.getId();

    }

    private Task saveTask(HttpServletRequest request) {
        Instant now = Instant.now();

        Task task = Task.builder()
                .title(request.getParameter("title"))
                .createTime(now)
                .creator((User)request.getSession().getAttribute("user"))
                .description(request.getParameter("description"))
                .build();
        task.setLastUpdate(new TaskUpdateBuilder()
                .setCategory(TaskUpdate.Category.valueOf(request.getParameter("category")))
                .setStatus(TaskUpdate.Status.OPEN)
                .setRecordTime(now)
                .setComment(request.getParameter("comment"))
                .setTask(task)
                .setRecorder((User)request.getSession().getAttribute("user"))
                .build());

        new TaskService().createTask(task);

        return task;
    }
}
