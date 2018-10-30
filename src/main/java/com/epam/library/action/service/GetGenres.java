package com.epam.library.action.service;

import com.epam.library.DAO.BookDAO;
import com.epam.library.action.Action;
import com.epam.library.entity.Attribute;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.library.constants.ActionConstants.LANG_ID;

public class GetGenres implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Attribute> genreList = new ArrayList<>();
        BookDAO bookDAO = new BookDAO();
        Integer langID = (Integer) request.getSession().getAttribute(LANG_ID);
        bookDAO.returnGenresForBook(genreList, langID);
        request.setAttribute("genreList",genreList);
    }
}
