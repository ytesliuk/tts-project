package controller.command;


import model.entity.Task;
import model.entity.User;
import model.service.TaskService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class SearchUserCommand implements Command{
    private  List<User> searchResult;

    @Override
    public String process(HttpServletRequest request) {
        search(request);
        request.getSession().setAttribute("userList", searchResult);

        return "redirect: /task-" + ((Task) request.getSession().getAttribute("task")).getId();
    }

    private void search(HttpServletRequest request) {
        String searchValue;
        String criteria = request.getParameter("searchCriteria");
        if(criteria.equals("byDepartment")){
            searchValue = request.getParameter("department");
            searchResult = new TaskService().getUsersByDepartment(User.Department.valueOf(searchValue));
        } else {
            searchValue = request.getParameter("name");
            searchResult = new TaskService().getUsersByName(searchValue, true);
        }
    }
}
