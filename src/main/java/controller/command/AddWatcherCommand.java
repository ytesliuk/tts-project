package controller.command;

import controller.ServletUtility;
import controller.SessionAttributeRetention;
import model.entity.Task;
import model.service.TaskService;

import javax.servlet.http.HttpServletRequest;


public class AddWatcherCommand implements Command {
    @Override
    public String process(HttpServletRequest request) {
        Task task = (Task) request.getSession().getAttribute("task");
        long watcherId = Long.parseLong(request.getParameter("watcher"));

        new TaskService().addWatcher(task, watcherId);

        ServletUtility.cleanSession(request.getSession(), SessionAttributeRetention.FULL_REQUEST);

        return "redirect: /task-" + task.getId();
    }
}
