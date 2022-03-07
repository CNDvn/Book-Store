/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieubd.servlets;

import hieubd.book.BookDAO;
import hieubd.book.BookDTO;
import hieubd.book.BookErr;
import hieubd.category.CategoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import hieubd.utils.Common;
import hieubd.utils.MyContanst;

/**
 *
 * @author CND
 */
@WebServlet(name = "UpdateBookServlet", urlPatterns = {"/UpdateBookServlet"})
@MultipartConfig
public class UpdateBookServlet extends HttpServlet {

    private final static String SUCCESS = "search";
    private final static String FAIL = "updateBookPage";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Properties map = (Properties) getServletContext().getAttribute("mapUrl");
        String url = map.getProperty(FAIL);
        try {
            request.setAttribute("listCategory", new CategoryDAO().getAll());
            String bookId = request.getParameter("bookId");
            String realUpdate = request.getParameter("realUpdate");
            BookDAO dao = new BookDAO();
            BookErr bookErr = new BookErr();
            if (realUpdate != null) {
                String title = request.getParameter("title");
                String author = request.getParameter("author");
                String description = request.getParameter("description");
                String price = request.getParameter("price");
                String quantity = request.getParameter("quantity");
                String category = request.getParameter("category");
                Part part = request.getPart("image");
                String image = "";
                if (part != null) {
                    image = part.getSubmittedFileName();
                }
                BookDTO bookObj = Common.checkValidInputBook(bookId, title, image, author, bookErr);
                if (bookObj != null) {
                    bookObj.setImage(request.getParameter("imageTmp"));
                    if (!image.equals("")) {
                        Common.writeImage(request, bookId + MyContanst.IMAGE_EXTENSION, part);
                        bookObj.setImage(bookId + MyContanst.IMAGE_EXTENSION);
                    }
                    bookObj.setBookId(bookId);
                    bookObj.setQuantity(Integer.parseInt(quantity));
                    bookObj.setStatusId(MyContanst.STATUS_ACTIVE);
                    bookObj.setCategoryId(Integer.parseInt(category));
                    bookObj.setDescription(description);
                    bookObj.setPrice(Float.parseFloat(price));
                    Date date = Date.valueOf(request.getParameter("date"));
                    bookObj.setDate(date);
                    dao.UpdateBook(bookObj);
                    request.setAttribute("bookIdSuccess", bookId);
                    url = map.getProperty(SUCCESS);
                }
            } else {
                request.setAttribute("bookObj", dao.getABook(bookId));
            }
            request.setAttribute("bookErr", bookErr);

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