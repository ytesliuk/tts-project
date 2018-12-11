package controller.command.page;

import controller.command.Command;
import model.entity.TaskUpdate;

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
