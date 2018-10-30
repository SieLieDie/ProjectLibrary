package com.epam.library.action;

import com.epam.library.DAO.BookDAO;
import com.epam.library.DAO.InterfaceDAO;
import com.epam.library.entity.Attribute;
import com.epam.library.entity.InterfaceLang;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.epam.library.constants.ActionConstants.ERROR_PAGE;
import static com.epam.library.constants.ActionConstants.EXCEPTION;
import static com.epam.library.constants.ActionConstants.EXCEPTION_LOG_NAME;

public class AddAuthor implements Action {
    private final static Logger EXCEPTION_LOG = LogManager.getLogger(EXCEPTION_LOG_NAME);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int coreID = 0;
        String regex = "\\W";
        String authorCoreName = request.getParameter("authorCoreName");
        String[] coreArray = authorCoreName.split(regex);
        ArrayList<InterfaceLang> langList = new ArrayList<>();
        ArrayList<Attribute> authorList = new ArrayList<Attribute>();
        InterfaceDAO interfaceDAO = new InterfaceDAO();
        interfaceDAO.getLangs(langList);
        BookDAO bookDAO = new BookDAO();
        for (InterfaceLang lang : langList) {
            String authorName = request.getParameter(lang.getName());
            String[] nameArray = authorName.split(regex);
            authorList.add(new Attribute(nameArray[0], nameArray[1], lang.getId()));
        }
        Attribute authorCore = new Attribute();
        authorCore.setCoreName(coreArray[0]);
        authorCore.setSecondCoreName(coreArray[1]);
        try {
            bookDAO.addAuthor(authorList, authorCore, coreID);
        } catch (SQLException e) {
            EXCEPTION_LOG.info("Exception in DAOMethod 'addAuthor'\n" + e);
            request.setAttribute(EXCEPTION, "addAuthorException");
            RequestDispatcher dispatcher = request.getRequestDispatcher(ERROR_PAGE);
            dispatcher.forward(request, response);
        }
        GoToMainPage goToMainPage = new GoToMainPage();
        goToMainPage.execute(request, response);
    }
}
