package controller.command.action;


import controller.ServletUtility;
import controller.SessionAttributeRetention;
import controller.command.Command;
import model.entity.*;
import model.service.TaskService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class SearchUserCommand implements Command {

    @Override
    public String process(HttpServletRequest request) {
        String searchList = request.getParameter("searchList");
        List<User> searchResult = search(request);

        ServletUtility.setSessionAttribute(request.getSession(),searchList, searchResult,
                SessionAttributeRetention.FULL_REQUEST);

        return "redirect: /task-" + ((Task) request.getSession().getAttribute("task")).getId();
    }

    private List<User> search(HttpServletRequest request) {
        List<User> searchResult;
        String searchValue;

        String criteria = request.getParameter("searchCriteria");
        if(criteria.equals("byDepartment")){
            searchValue = request.getParameter("department");
            searchResult = new TaskService().getUsersByDepartment(User.Department.valueOf(searchValue));
        } else {
            searchValue = request.getParameter("name");
            searchResult = new TaskService().getUsersByName(searchValue, true);
        }
        return  searchResult;
    }
}
