package com.epam.library.action;

import com.epam.library.DAO.BookDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static com.epam.library.constants.ActionConstants.*;

public class AddBook implements Action {
    private static final Logger ROOT_LOGGER = LogManager.getRootLogger();
    private final static Logger EXCEPTION_LOG = LogManager.getLogger(EXCEPTION_LOG_NAME);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String bookName = request.getParameter(BOOK_NAME);
        Integer authorCoreID = Integer.valueOf(request.getParameter("author"));
        Integer genreCoreID = Integer.valueOf(request.getParameter("genre"));
        int inStoke = Integer.parseInt(request.getParameter("inStoke"));
        String description = request.getParameter("description");
        BookDAO bookDAO = new BookDAO();
        try {
            bookDAO.addBook(bookName, authorCoreID, genreCoreID, inStoke, description);
        } catch (SQLException e) {
            EXCEPTION_LOG.info("Exception in DAOMethod 'addBook'\n" + e);
            request.setAttribute(EXCEPTION, "addBookException");
            RequestDispatcher dispatcher = request.getRequestDispatcher(ERROR_PAGE);
            dispatcher.forward(request, response);
        }
        ROOT_LOGGER.info("Book was added: " + bookName);
        GoToMainPage goToMainPage = new GoToMainPage();
        goToMainPage.execute(request, response);
    }
}
