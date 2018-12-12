package com.javacourse.controller.command.page;

import com.javacourse.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import com.javacourse.controller.ServletUtility;
import com.javacourse.model.entity.Task;
import com.javacourse.model.service.UserService;
import java.util.*;

public class TaskListCommand implements Command {
    @Override
    public String process(HttpServletRequest request) {

        request.setAttribute("taskList", setTaskLists(request));

        return "/WEB-INF/taskList.jsp";
    }

    private List<Task> setTaskLists(HttpServletRequest request) {

        long userId = ServletUtility.getUserId(request);
        String listType = request.getParameter("tl");
        if(listType.equals("initiated")){
            return new UserService().getActiveTasksByCreator(userId);
        } else if(listType.equals("assigned")){
            return new UserService().getActiveTasksByOwner(userId);
        } else if(listType.equals("watched")){
            return new UserService().getActiveTasksByCreator(userId);
        }
        return new ArrayList<>();
    }
}
