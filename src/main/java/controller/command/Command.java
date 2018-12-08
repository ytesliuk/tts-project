package controller.command;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Yuliia Tesliuk
 */
public interface Command {

    String process(HttpServletRequest request);
}
