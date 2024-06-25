/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;
import model.*;
import static dal.DAO.Instance;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author sinan
 */
public class CheckoutServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CheckoutServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckoutServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.setAttribute("thanhtien", ListProduct.getInstance().getSumPrice());
        request.setAttribute("pList", ListProduct.getInstance().getproductList());
        request.setAttribute("shipperList", Instance.getAllShipper());
        request.getRequestDispatcher("view/checkout.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
                Cookie arr[] = request.getCookies();
        User_Info user = (User_Info) request.getSession().getAttribute("account");
        String userID = user.getUserID() + "";
         HashMap<String, String> map = new HashMap();
         for (Cookie cookie : arr) {
           if(Instance.isProduct(ListProduct.getInstance().tachChuoi(cookie.getName()))){
                if (ListProduct.getInstance().tachChuoi2(cookie.getName()).equals(userID)) {
                    map.put(cookie.getName(), cookie.getValue());
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                 
                }
            }
        }
        for (String i : map.keySet()) {
            Instance.updateQuantity(Integer.parseInt(map.get(i)),ListProduct.getInstance().tachChuoi(i));
        }
        

         String shipper = request.getParameter("shipper");
         String address = request.getParameter("shipto");
         
         if(user!=null && !ListProduct.getInstance().getproductList().isEmpty()) {
             Instance.InsertIntoOrder(user.getUserID()
                     , Integer.parseInt(shipper),
                     address, ListProduct.getInstance().donhang()
                     ,ListProduct.getInstance().getSumPrice());
             request.setAttribute("msg", "Success");
         }else {
             request.setAttribute("msg", "Fail");
         }
         
         
         
        ListProduct.getInstance().getproductList().clear();
        doGet(request, response);
    
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
