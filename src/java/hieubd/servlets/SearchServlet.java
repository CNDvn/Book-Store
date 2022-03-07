/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieubd.servlets;

import hieubd.book.BookDAO;
import hieubd.category.CategoryDAO;
import hieubd.category.CategoryDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author CND
 */
@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
@MultipartConfig
public class SearchServlet extends HttpServlet {

    private final static String SUCCESS = "searchPage";
    private final static String FAIL = "errorPage";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Properties mapUrl = (Properties) getServletContext().getAttribute("mapUrl");
        String url = mapUrl.getProperty(FAIL);

        try {
            String txtMin = request.getParameter("txtMin");
            String txtMax = request.getParameter("txtMax");
            Integer min = null, max = null;
            if (txtMin != null && txtMax != null && !txtMin.isEmpty() && !txtMax.isEmpty()) {
                min = Integer.parseInt(txtMin);
                max = Integer.parseInt(txtMax);
            }
            String txtCategory = request.getParameter("txtCategory");
            Integer category = null;
            if (txtCategory != null && !txtCategory.isEmpty()) {
                category = Integer.parseInt(txtCategory);
            }
            String txtSearch = request.getParameter("txtSearch");
            if (txtSearch == null) {
                txtSearch = "";
            }

            BookDAO dao = new BookDAO();
            request.setAttribute("listBook", dao.searchBook(min, max, category, txtSearch));

            List<CategoryDTO> listCategory = (new CategoryDAO()).getAll();
            request.setAttribute("listCategory", listCategory);
            url = mapUrl.getProperty(SUCCESS);

        } catch (NamingException e) {
            log("Error NamingException at " + this.getClass() + ": " + e.getMessage());
        } catch (SQLException e) {
            log("Error SQLException at " + this.getClass() + ": " + e.getMessage());
        } catch (Exception e) {
            log("Error Exception at " + this.getClass() + ": " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url.split(" ")[0]).forward(request, response);
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
