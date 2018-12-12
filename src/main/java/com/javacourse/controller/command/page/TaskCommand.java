package com.javacourse.controller.command.page;

import com.javacourse.controller.command.Command;
import com.javacourse.model.entity.Comment;
import com.javacourse.model.entity.Task;
import com.javacourse.model.entity.TaskUpdate;
import com.javacourse.model.service.TaskService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Yuliia Tesliuk
 */
public class TaskCommand implements Command {
    private TaskService taskService = new TaskService();

    @Override
    public String process(HttpServletRequest request) {
        Task task = setTask(request);
        request.getSession().setAttribute("task", task);

        List<Comment> comments = setComments(request, task);
        request.setAttribute("comments", comments);
        request.setAttribute("statuses", TaskUpdate.Status.values());
        request.setAttribute("categories", TaskUpdate.Category.values());

        return "/WEB-INF/task.jsp";
    }

    private Task setTask(HttpServletRequest request) {
        long id = Long.parseLong(request.getRequestURI().replaceAll(".*/task-",""));
        return taskService.findTask(id);
    }


    private List<Comment> setComments(HttpServletRequest request, Task task) {
        return taskService.getTaskComments(task);
    }

}
