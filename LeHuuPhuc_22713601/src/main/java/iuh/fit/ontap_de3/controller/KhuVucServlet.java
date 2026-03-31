package iuh.fit.ontap_de3.controller;

import iuh.fit.ontap_de3.dao.Impl.KhuVucDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/khuvuc")
public class KhuVucServlet extends HttpServlet {
    private KhuVucDAOImpl khuVucDAO = new KhuVucDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("khuvucList", khuVucDAO.getAllKhuVuc());
        req.getRequestDispatcher("views/list.jsp").forward(req, resp);
    }
}
