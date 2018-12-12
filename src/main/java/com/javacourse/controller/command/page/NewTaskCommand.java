package com.javacourse.controller.command.page;

import com.javacourse.controller.command.Command;
import com.javacourse.model.entity.TaskUpdate;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Yuliia Tesliuk
 */
public class NewTaskCommand implements Command {
    @Override
    public String process(HttpServletRequest request) {

        request.setAttribute("page","new_task");
        request.setAttribute("categories", TaskUpdate.Category.values());

        return "/WEB-INF/newTask.jsp";
    }
}
