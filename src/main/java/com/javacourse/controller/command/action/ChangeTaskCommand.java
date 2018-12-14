package com.javacourse.controller.command.action;

import com.javacourse.controller.ServletUtility;
import com.javacourse.controller.SessionListener;
import com.javacourse.controller.command.*;
import com.javacourse.controller.SessionAttributeRetention;
import com.javacourse.model.entity.*;
import com.javacourse.model.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class ChangeTaskCommand implements Command {
    private final Logger logger = LoggerFactory.getLogger(ChangeTaskCommand.class);


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
        logger.debug("Request for update: category - {}, status - {}, assigneeID - {}, duration - {}, comment - {}",
                request.getParameter("category"), request.getParameter("status"), request.getParameter("assignee"),
                request.getParameter("duration"), request.getParameter("comment"));

        TaskUpdateBuilder builder = new TaskUpdateBuilder();
        builder.setCategory(TaskUpdate.Category.valueOf(request.getParameter("category")))
                .setStatus(TaskUpdate.Status.valueOf(request.getParameter("status")))
                .setOwner(task.getLastUpdate().getOwner())
                .setTask(task)
                .setRecorder((User)request.getSession().getAttribute("user"))
                .setRecordTime(Instant.now());


        Optional.ofNullable(request.getParameter("comment")).ifPresent(x -> builder.setComment(
                (x.length() > 0) ? x : null));
        Optional.ofNullable(request.getParameter("assignee")).ifPresent(x -> builder.setOwner(
                ((List<User>)request.getSession().getAttribute("userListAssign")).stream()
                        .filter( u-> u.getId() == Long.parseLong(x))
                        .findFirst().get()));
        Optional.ofNullable(request.getParameter("duration")).ifPresent(x -> builder.setSpentTime(
                (x.length() > 0) ? Duration.ofMinutes(Long.parseLong(x)) : null));

        return builder.build();
    }
}

