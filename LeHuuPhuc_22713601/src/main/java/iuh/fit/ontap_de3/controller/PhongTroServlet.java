package iuh.fit.ontap_de3.controller;

import iuh.fit.ontap_de3.dao.Impl.KhuVucDAOImpl;
import iuh.fit.ontap_de3.dao.Impl.PhongTroDAOImpl;
import iuh.fit.ontap_de3.model.KhuVuc;
import iuh.fit.ontap_de3.model.PhongTro;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/phongtro")
public class PhongTroServlet extends HttpServlet {
    private PhongTroDAOImpl phongTroDAO = new PhongTroDAOImpl();
    private KhuVucDAOImpl khuVucDAO = new KhuVucDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";
        req.setAttribute("khuvucList", khuVucDAO.getAllKhuVuc());
        switch (action) {
            case "list":
                req.setAttribute("list", phongTroDAO.getAllPhongTro());
                req.getRequestDispatcher("views/list.jsp").forward(req, resp);
                break;
            case "detail":
                int ma = Integer.parseInt(req.getParameter("id"));
                req.setAttribute("detail", phongTroDAO.getByID(ma));
                req.getRequestDispatcher("views/detail.jsp").forward(req, resp);
                break;
            case "search":
                String ten = req.getParameter("name");
                req.setAttribute("list", phongTroDAO.getByName(ten));
                req.getRequestDispatcher("views/list.jsp").forward(req, resp);
                break;
            case "byKV":
                int ma1 = Integer.parseInt(req.getParameter("id"));
                req.setAttribute("list", phongTroDAO.getByKhuVuc(ma1));
                req.getRequestDispatcher("views/list.jsp").forward(req, resp);
                break;
            case "edit":
                int ma2 = Integer.parseInt(req.getParameter("id"));
                req.setAttribute("ptid", phongTroDAO.getByID(ma2));
                req.getRequestDispatcher("views/update.jsp").forward(req, resp);
                break;
            case "add":
                req.getRequestDispatcher("views/insert.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("update".equals(action)) {
            PhongTro phongTro = new PhongTro();
            phongTro.setMaPT(Integer.parseInt(req.getParameter("maPT")));
            phongTro.setTenPT(req.getParameter("tenPT"));
            phongTro.setGiaThue(Double.valueOf(req.getParameter("giaThue")));
            phongTro.setDienTich(Float.parseFloat(req.getParameter("dienTich")));
            phongTro.setHinhAnh(req.getParameter("hinhAnh"));

            KhuVuc khuVuc = new KhuVuc();
            int maKV = Integer.parseInt(req.getParameter("maKV"));
            khuVuc.setMaKV(maKV);
            phongTro.setMaKV(khuVuc);

            phongTroDAO.updatePhongTro(phongTro);
            resp.sendRedirect("phongtro?action=list");
        } else if ("insert".equals(action)) {
            PhongTro phongTro = new PhongTro();
            phongTro.setTenPT(req.getParameter("tenPT"));
            phongTro.setGiaThue(Double.valueOf(req.getParameter("giaThue")));
            phongTro.setDienTich(Float.parseFloat(req.getParameter("dienTich")));
            phongTro.setHinhAnh(req.getParameter("hinhAnh"));

            KhuVuc khuVuc = new KhuVuc();
            int maKV = Integer.parseInt(req.getParameter("maKV"));
            khuVuc.setMaKV(maKV);
            phongTro.setMaKV(khuVuc);

            phongTroDAO.addPhongTro(phongTro);
            resp.sendRedirect("phongtro?action=list");
        } else if ("delete".equals(action)) {
            phongTroDAO.delete(Integer.parseInt(req.getParameter("maPT")));
            resp.sendRedirect("phongtro?action=list");
        }
    }
}
