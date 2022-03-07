/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieubd.servlets;

import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.PayPalRESTException;
import hieubd.book.BookDAO;
import hieubd.book.BookDTO;
import hieubd.cartdetail.CartDetailDAO;
import hieubd.paypal.PayPalService;
import hieubd.session.CartObj;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
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
@WebServlet(name = "ExecutePaymentPayPalServlet", urlPatterns = {"/ExecutePaymentPayPalServlet"})
public class ExecutePaymentPayPalServlet extends HttpServlet {

    private final String SUCCESS = "search";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String paymentId = request.getParameter("paymentId");
        String payerId = request.getParameter("PayerID");
        Properties map = (Properties) getServletContext().getAttribute("mapUrl");
        String url = map.getProperty(SUCCESS);
        try {
            PayPalService paymentServices = new PayPalService();
            Payment payment = paymentServices.executePayment(paymentId, payerId);

            PayerInfo payerInfo = payment.getPayer().getPayerInfo();
            Transaction transaction = payment.getTransactions().get(0);

            request.setAttribute("payer", payerInfo);
            request.setAttribute("transaction", transaction);

            HttpSession session = request.getSession();
            BookDAO dao = new BookDAO();
            CartObj cart = (CartObj) session.getAttribute("cartObj");

            long milisecond = System.currentTimeMillis();
            Integer cartId = dao.addCart(cart.getUserId(), cart.getDiscountId(), new Date(milisecond));
            CartDetailDAO cartDetailDao = new CartDetailDAO();
            for (Map.Entry<BookDTO, Integer> item : cart.getItems().entrySet()) {
                cartDetailDao.addBookToCart(cartId, item.getKey(), item.getValue());
            }
            session.removeAttribute("cartObj");
            request.setAttribute("paySuccess", "Order Successful");
            request.getRequestDispatcher(url.split(" ")[0]).forward(request, response);

        } catch (NamingException e) {
            log("Error NamingException at " + this.getClass() + ": " + e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
            log("Error SQLException at " + this.getClass() + ": " + e.getMessage());
        } catch (PayPalRESTException e) {
            log("Error PayPalRESTException at " + this.getClass() + ": " + e.getMessage());
        } catch (Exception e) {
            log("Error Exception at " + this.getClass() + ": " + e.getMessage());
        } finally {
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
