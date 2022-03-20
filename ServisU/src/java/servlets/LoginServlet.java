/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import data.UserIO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.User;

/**
 *
 * @author daniel
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "/login.jsp";
        getServletContext().getRequestDispatcher(url)
                .forward(request, response);
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
        
        String url= "/login.jsp";
        ServletContext sc = getServletContext();
        String message;
        
        // get username from login.jsp
        String un = request.getParameter("username");
        String pw = request.getParameter("password");
        // check if user exists in file (UesrIO.getUser != null)
        String path = sc.getRealPath("/WEB-INF/login_credentials.txt");
        User currentUser = UserIO.getUser(path, un);
        // if no match, display message that things don't match
        if (currentUser == null) {
            // message: "user not found"
            message = "User not found. Please re-enter information.";
            request.setAttribute("message", message);
            getServletContext().getRequestDispatcher(url)
                .forward(request, response);
        }
        else {
            // if user exists, check if password matches
            if (currentUser.getPassword().equals(pw)) {
                // if match, create new user object, create session, add user to session
                //route to internal jsp page (successful login)
                url = "/index.jsp";
                getServletContext().getRequestDispatcher(url)
                .forward(request, response);
            }
            // if password not match, display invalid credentials message
            else {
                // message: "password does not match file"
                message = "Password does not match file. Please re-enter information.";
            request.setAttribute("message", message);
            getServletContext().getRequestDispatcher(url)
                .forward(request, response);
            }
        
        }
        
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
