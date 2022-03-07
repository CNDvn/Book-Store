/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieubd.servlets;

import bieubd.account.AccountDTO;
import hieubd.utils.MyContanst;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import javax.servlet.ServletContext;
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
@WebServlet(name = "MainServlet", urlPatterns = {"/MainServlet"})
public class MainServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String action = request.getParameter("btnAction");
        if (action == null) {
            action = "";
        }
        String url = "";
        try {
            HttpSession session = request.getSession();
            AccountDTO account = (AccountDTO)session.getAttribute("account");
            ServletContext contex = request.getServletContext();
            Properties map = (Properties) contex.getAttribute("mapUrl");
            url = map.getProperty(action).split(" ")[0];
            String author = map.getProperty(action).split(" ")[1];
//            url = map.getProperty(action);
            if ( (author.equals("1") && account.getRoleId() != MyContanst.ROLE_ADMIN) || (author.equals("2") && account.getRoleId() != MyContanst.ROLE_CUSTOMER) ) {
                url = map.getProperty("loginPageHtml");
            }
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
