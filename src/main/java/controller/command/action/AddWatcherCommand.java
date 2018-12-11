package controller.command.action;

import controller.ServletUtility;
import controller.command.Command;
import controller.SessionAttributeRetention;
import model.entity.Task;
import model.service.TaskService;

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
