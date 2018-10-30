package com.epam.library.action;

import com.epam.library.DAO.UserDAO;
import com.epam.library.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static com.epam.library.constants.ActionConstants.*;

public class AuthorizationAction implements Action {
    private final static Logger EXCEPTION_LOG = LogManager.getLogger(EXCEPTION_LOG_NAME);


    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int adminRoleID = 1;
        int userRoleID = 2;
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        UserDAO userDAO = new UserDAO();
        try {
            User user = userDAO.getUser(login);

            RequestDispatcher dispatcher;
            if (user != null && checkPassword(password, user.getPassword())) {
                switch (user.getRole()) {
                    case "admin":
                        request.getSession().setAttribute(USER_ROLE_ID, adminRoleID);
                        request.getSession().setAttribute(USER_ID, user.getId());
                        break;
                    case "user":
                        request.getSession().setAttribute(USER_ROLE_ID, userRoleID);
                        request.getSession().setAttribute(USER_ID, user.getId());
                        break;
                }
                request.getSession().setAttribute("userName", user.getFirstName());
                GoToMainPage goToMainPage = new GoToMainPage();
                goToMainPage.execute(request, response);
            } else {
                request.setAttribute(EXCEPTION, "authorizationException");
                dispatcher = request.getRequestDispatcher(ERROR_PAGE);
                dispatcher.forward(request, response);
            }
        } catch (SQLException e) {
            EXCEPTION_LOG.info("Exception in DAOMethod 'getUser'\n" + e);
        }
    }

    private static boolean checkPassword(String password, String hashFromBD) {
        boolean passwordVerified = false;
        if (hashFromBD == null) {
            throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");
        } else {
            passwordVerified = BCrypt.checkpw(password, hashFromBD);
        }
        return (passwordVerified);
    }
}
