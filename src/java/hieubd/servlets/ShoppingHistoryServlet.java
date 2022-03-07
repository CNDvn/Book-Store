/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieubd.servlets;

import bieubd.account.AccountDTO;
import hieubd.cart.CartDAO;
import hieubd.cart.CartDTO;
import hieubd.book.BookDTO;
import hieubd.cartdetail.CartDetailDAO;
import hieubd.cartdetail.CartDetailDTO;
import hieubd.discount.DiscountDAO;
import hieubd.discount.DiscountDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.naming.NamingException;
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
@WebServlet(name = "ShoppingHistoryServlet", urlPatterns = {"/ShoppingHistoryServlet"})
public class ShoppingHistoryServlet extends HttpServlet {

    private final String SUCCESS = "shoppingHistoryPage";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Properties map = (Properties) getServletContext().getAttribute("mapUrl");
        String url = map.getProperty(SUCCESS);
        try {

            String bookName = request.getParameter("bookName");
            String dateStr = request.getParameter("date");
            if (bookName == null) {
                bookName = "";
            }
            Date date = null;
            if (dateStr != null && !dateStr.isEmpty()) {
                date = Date.valueOf(dateStr);
            }

            HttpSession session = request.getSession();
            AccountDTO account = (AccountDTO) session.getAttribute("account");
            CartDAO cartDao = new CartDAO();

            List<CartDTO> listCart = cartDao.getListCart(account.getUserId(), bookName, date);

            CartDetailDAO cartDetailDao = new CartDetailDAO();
            Map<CartDTO, List<CartDetailDTO>> listCartDetail = new LinkedHashMap<>();

            for (CartDTO cartDTO : listCart) {
                List<CartDetailDTO> list = cartDetailDao.getListItemInCart(cartDTO.getCartId());
                listCartDetail.put(cartDTO, list);
            }
            request.setAttribute("listCartDetail", listCartDetail);
            request.setAttribute("listDiscount", new DiscountDAO().getAllDiscount());
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
