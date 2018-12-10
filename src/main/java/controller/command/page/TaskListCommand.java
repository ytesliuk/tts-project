package controller.command.page;

import controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class TaskListCommand implements Command {
    @Override
    public String process(HttpServletRequest request) {
        return "/WEB-INF/taskList.jsp";
    }
}
