package controller.filter;


import controller.util.PropertiesLoader;
import model.service.*;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

/**
 * @author Yuliia Tesliuk
 */
@WebFilter("/*")
public class AuthFilter implements Filter {
    private String path;
    private HttpSession session;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        path = req.getRequestURI();
        session = req.getSession();

        if( ! generalAccessCheckIsPassed()){
            resp.sendRedirect(req.getContextPath());
            return;
        }

        if(path.contains("task-")) {
            taskAccessCheck();
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean generalAccessCheckIsPassed() {
        Properties pr = new PropertiesLoader().getLoadedProperties("generalAccessPages.properties");
        boolean isGeneralPath = Boolean.parseBoolean(pr.getProperty(path));
        if( !isGeneralPath && Objects.isNull(session.getAttribute("userId"))){
            return false;
        }
        return true;
    }

    private void taskAccessCheck() {
        long taskId = Long.parseLong(path.replaceAll(".*task-" , ""));
        long userId = (long) session.getAttribute("userId");
        if ( !new SecurityService().checkTaskAccess(taskId, userId)) {
            throw new AccessForbiddenException("You do not have permission to view this page.");
        }
    }

    @Override
    public void destroy() {

    }
}
