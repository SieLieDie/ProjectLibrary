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

public class AddGenre implements Action {
    private final static Logger EXCEPTION_LOG = LogManager.getLogger(EXCEPTION_LOG_NAME);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String core = request.getParameter("genreCore");
        int coreID = 0;
        String genreName;
        ArrayList<InterfaceLang> langList = new ArrayList<>();
        ArrayList<Attribute> attributeList = new ArrayList<Attribute>();
        InterfaceDAO interfaceDAO = new InterfaceDAO();
        interfaceDAO.getLangs(langList);
        Attribute genreCore = new Attribute();
        genreCore.setCoreName(core);
        for (InterfaceLang lang:langList) {
            genreName = request.getParameter(lang.getName());
            attributeList.add(new Attribute(genreName, lang.getId()));
        }
        BookDAO bookDAO = new BookDAO();
        try {
            bookDAO.addGenre(attributeList, genreCore, coreID);
        }catch (SQLException e){
            EXCEPTION_LOG.info("Exception in DAOMethod 'addGenre'\n" + e);
            request.setAttribute(EXCEPTION, "addGenreException");
            RequestDispatcher dispatcher = request.getRequestDispatcher(ERROR_PAGE);
            dispatcher.forward(request, response);
        }
        GoToMainPage goToMainPage = new GoToMainPage();
        goToMainPage.execute(request, response);
    }
}
