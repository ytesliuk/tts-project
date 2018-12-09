package controller.command;

import model.entity.*;
import model.service.TaskService;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.util.*;

public class ChangeTaskCommand implements Command {
    private Task task;
    private TaskUpdate update;

    @Override
    public String process(HttpServletRequest request) {
        task = (Task)request.getSession().getAttribute("task");

        getTaskUpdateFromRequest(request);
        new TaskService().saveTaskUpdate(update);

        return "redirect: /task-" + task.getId();
    }

    private TaskUpdate getTaskUpdateFromRequest(HttpServletRequest request) {

        update = TaskUpdate.builder().update(task.getLastUpdate()).build();
        Optional.ofNullable(request.getParameter("comment")).ifPresent(update::setComment);
        Optional.ofNullable(request.getParameter("category")).ifPresent(update::setCategory);
        Optional.ofNullable(request.getParameter("status")).ifPresent(x -> update.setStatus(TaskUpdate.Status.valueOf(x)));
        Optional.ofNullable(request.getParameter("assignee")).ifPresent(x -> update.setOwner(
                ((List<User>)request.getSession().getAttribute("userList")).stream()
                .filter( (u)-> u.getId() == Long.parseLong(x))
                .findFirst().get()));
        Optional.ofNullable(request.getParameter("duration")).ifPresent(x -> update.setSpentTime(
                Duration.ofMinutes(Long.parseLong(x))));

        return update;
    }
}

