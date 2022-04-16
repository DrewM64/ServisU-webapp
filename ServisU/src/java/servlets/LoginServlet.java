/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import data.UserIO;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import models.User;
import data.UserDB;

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
        String action = request.getParameter("action");
        ServletContext sc = getServletContext();
        String message;
        
        // get username from login.jsp
        String un = request.getParameter("username");
        String pw = request.getParameter("password");
        
        if (action.equals("logindb")) {
            // check if user exists on database
            if (!UserDB.userExists(un)) {
            // if no match, message no match
            message = "User not found. Please try again.";
                request.setAttribute("message", message);
                getServletContext().getRequestDispatcher(url)
                    .forward(request, response);
            } 
            else {
            //if exists, check if password matches DB
            User currentUser = UserDB.selectUser(un);
            // if password matches, create user, create session, add to session, send to welcome
            if (currentUser.getPassword().equals(pw)) {
                    // if match, create new user object, 
                    User newUser = new User();
                    newUser.setUsername(currentUser.getUsername());
                    newUser.setPassword(currentUser.getPassword());

                    // create session, 
                    HttpSession session = request.getSession();

                    // add user to session
                    session.setAttribute("user", newUser);

                    //route to internal jsp page (successful login)
                    url = "/welcome";
                    getServletContext().getRequestDispatcher(url)
                    .forward(request, response);
                }
                // if password not match, display invalid credentials message
                else {
                    // message: "password does not match file"
                    message = "Password does not match user. Please try again.";
                request.setAttribute("message", message);
                getServletContext().getRequestDispatcher(url)
                    .forward(request, response);
                }
            }
        }
        else {
        
            // check if user exists in file (UesrIO.getUser != null)
            String path = sc.getRealPath("/WEB-INF/login_credentials.txt");
            User currentUser = UserIO.getUser(path, un);

            // if no match, display message that things don't match
            if (currentUser == null) {
                // message: "user not found"
                message = "User not found. Please try again.";
                request.setAttribute("message", message);
                getServletContext().getRequestDispatcher(url)
                    .forward(request, response);
            }
            else {
                // if user exists, check if password matches
                if (currentUser.getPassword().equals(pw)) {
                    // if match, create new user object, 
                    User newUser = new User();
                    newUser.setUsername(currentUser.getUsername());
                    newUser.setPassword(currentUser.getPassword());

                    // create session, 
                    HttpSession session = request.getSession();

                    // add user to session
                    session.setAttribute("user", newUser);

                    //route to internal jsp page (successful login)
                    url = "/welcome";
                    getServletContext().getRequestDispatcher(url)
                    .forward(request, response);
                }
                // if password not match, display invalid credentials message
                else {
                    // message: "password does not match file"
                    message = "Password does not match user. Please try again.";
                request.setAttribute("message", message);
                getServletContext().getRequestDispatcher(url)
                    .forward(request, response);
                }
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
