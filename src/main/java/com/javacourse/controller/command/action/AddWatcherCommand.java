package com.javacourse.controller.command.action;

import com.javacourse.controller.ServletUtility;
import com.javacourse.controller.command.Command;
import com.javacourse.controller.SessionAttributeRetention;
import com.javacourse.model.entity.Task;
import com.javacourse.model.service.TaskService;

import javax.servlet.http.HttpServletRequest;


public class AddWatcherCommand implements Command {
    @Override
    public String process(HttpServletRequest request) {
        Task task = (Task) request.getSession().getAttribute("task");
        addWatcher(request, task);

        ServletUtility.cleanSession(request.getSession(), SessionAttributeRetention.FULL_REQUEST);

        return "redirect: /task-" + task.getId();
    }

    private void addWatcher(HttpServletRequest request, Task task) {
        long watcherId = Long.parseLong(request.getParameter("watcher"));
        new TaskService().addWatcher(task, watcherId);
    }
}
