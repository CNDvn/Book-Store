/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieubd.servlets;

import hieubd.discount.DiscountDAO;
import hieubd.discount.DiscountDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author CND
 */
@WebServlet(name = "AddNewDiscountServlet", urlPatterns = {"/AddNewDiscountServlet"})
public class AddNewDiscountServlet extends HttpServlet {

    private final String SUCCESS = "manageDiscount";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Properties map = (Properties) getServletContext().getAttribute("mapUrl");
        String url = map.getProperty(SUCCESS);
        try {
            String codeDiscount = request.getParameter("codeDiscount");
            String valueDiscount = request.getParameter("valueDiscount");
            String descriptionDiscount = request.getParameter("descriptionDiscount");
            DiscountDAO dao = new DiscountDAO();
            if (dao.isExistedDiscount(codeDiscount)) {
                request.setAttribute("msgAddDiscountFail", "Code discount existed");
            } else {
                DiscountDTO dto = new DiscountDTO(codeDiscount, Integer.parseInt(valueDiscount), descriptionDiscount);
                dao.addNewDiscount(dto);
                request.setAttribute("msgAddDiscountSuccess", "Add Discount Successful");
            }
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
