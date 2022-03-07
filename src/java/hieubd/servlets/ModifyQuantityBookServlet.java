/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieubd.servlets;

import hieubd.session.CartObj;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author CND
 */
@WebServlet(name = "ModifyQuantityBookServlet", urlPatterns = {"/ModifyQuantityBookServlet"})
public class ModifyQuantityBookServlet extends HttpServlet {

    private final static String SUCCESS = "cart";
    private final static String TYPE_REDUCE = "reduce";
    private final static String TYPE_INCREASE = "increase";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Properties map = (Properties) getServletContext().getAttribute("mapUrl");
        String url = map.getProperty(SUCCESS);
        try {
            String type = request.getParameter("type");
            String bookId = request.getParameter("bookId");
            HttpSession session = request.getSession();
            CartObj cartObj = (CartObj)session.getAttribute("cartObj");
            if(type.equals(TYPE_REDUCE)){
                cartObj.reduceQuantityItem(bookId);
            }
            if(type.equals(TYPE_INCREASE)){
                cartObj.increaseQuantityItem(bookId);
            }
            session.setAttribute("cartObj", cartObj);
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
