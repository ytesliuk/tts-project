package com.javacourse.controller.command.action;

import com.javacourse.controller.command.Command;
import com.javacourse.model.entity.Task;
import com.javacourse.model.entity.TaskUpdate;
import com.javacourse.model.entity.User;
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
        task.setLastUpdate(TaskUpdate.builder()
                .status(TaskUpdate.Status.OPEN)
                .task(task)
                .category(TaskUpdate.Category.valueOf(request.getParameter("category")))
                .recorder((User)request.getSession().getAttribute("user"))
                .recordTime(now)
                .build());
        new TaskService().createTask(task);

        return task;
    }
}
