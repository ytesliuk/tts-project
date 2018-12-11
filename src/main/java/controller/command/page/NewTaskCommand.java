package controller.command.page;

import controller.command.Command;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Yuliia Tesliuk
 */
public class NewTaskCommand implements Command {
    @Override
    public String process(HttpServletRequest request) {

        request.setAttribute("page","new_task");

        return "/WEB-INF/newTask.jsp";
    }
}
