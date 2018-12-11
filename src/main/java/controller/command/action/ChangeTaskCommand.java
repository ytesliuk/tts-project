package controller.command.action;

import controller.*;
import controller.command.*;
import controller.SessionAttributeRetention;
import model.entity.*;
import model.service.TaskService;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.util.*;

public class ChangeTaskCommand implements Command {

    @Override
    public String process(HttpServletRequest request) {
        Task task = (Task)request.getSession().getAttribute("task");

        changeTask(request, task);

        ServletUtility.cleanSession(request.getSession(), SessionAttributeRetention.FULL_REQUEST);

        return "redirect: /task-" + task.getId();
    }

    private void changeTask(HttpServletRequest request, Task task) {
        TaskUpdate update = getTaskUpdateFromRequest(request, task);
        task.setLastUpdate(update);
        new TaskService().saveTaskUpdate(update);
    }

    private TaskUpdate getTaskUpdateFromRequest(HttpServletRequest request, Task task) {

        TaskUpdate update = new TaskUpdate(task.getLastUpdate());
        Optional.ofNullable(request.getParameter("comment")).ifPresent(update::setComment);
        Optional.ofNullable(request.getParameter("category")).ifPresent(update::setCategory);
        Optional.ofNullable(request.getParameter("status")).ifPresent(x -> update.setStatus(TaskUpdate.Status.valueOf(x)));
        Optional.ofNullable(request.getParameter("assignee")).ifPresent(x -> update.setOwner(
                ((List<User>)request.getSession().getAttribute("userList")).stream()
                        .filter( (u)-> u.getId() == Long.parseLong(x))
                        .findFirst().get()));
        Optional.ofNullable(request.getParameter("duration")).ifPresent(x -> update.setSpentTime(
                (x.length() > 0) ? Duration.ofMinutes(Long.parseLong(x)) : null));

        return update;
    }
}

