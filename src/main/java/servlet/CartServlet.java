package servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import model.OrderDetailSession;

/**
 *
 * @author Administrator
 */
public class CartServlet extends HttpServlet {
    Logger logger = Logger.getLogger("CartServlet");
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
        HttpSession session = request.getSession();
        List<OrderDetailSession> orderDetailSesionList = null;
        
        double total = 0D;
        if(session.getAttribute("cart") != null){
            orderDetailSesionList = (List <OrderDetailSession>)session.getAttribute("cart");
            for (OrderDetailSession orderDetailSession: orderDetailSesionList) {
                total += orderDetailSession.getPrice()*orderDetailSession.getQuantity();
            }
        }else{
            orderDetailSesionList = new ArrayList<OrderDetailSession>();
        }
        
        request.setAttribute("total", total);
        request.setAttribute("orderDetailSesionList", orderDetailSesionList);
        request.getRequestDispatcher("cart.jsp").forward(request, response);
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