/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import data.UserDB;
import java.io.IOException;
import java.util.ArrayList;
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
@WebServlet(name = "UserServlet", urlPatterns = {"/user"})
public class UserServlet extends HttpServlet {
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
        String url = "/view_users.jsp";
        getServletContext().getRequestDispatcher(url)
                .forward(request, response);
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
        String url = "/view_users.jsp";
        
        // get current action
        String action = request.getParameter("action");
        if (action == null) {
            action = "view";  // default action
        }

        // perform action and set URL to appropriate page
        if (action.equals("view")) {
            url = "/view_users.jsp";    // the "view" page
            
            //get list of User objects to populate table
            ArrayList<User> userList = UserDB.selectUsers();
            request.setAttribute("userList", userList);
        } 
        else if (action.equals("add")) {
            // get parameters from the request
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            // store data in User object
            User user = new User(firstName, lastName, username, password);

            // validate the parameters
            String message;
            if (firstName == null || lastName == null || username == null || password == null ||
                firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || password.isEmpty() ) {
                message = "Please fill out all four text boxes.";
                url = "/add_user.jsp";
            } else if (UserDB.userExists(username)){
                message = "Username already in use.";
                url = "/add_user.jsp";
            } else if (!UserDB.validPass(password)) {
                message = "Password must be greater than 8 characters, and include "
                        + "at least 1 uppercase letter, 1 lowercase letter, and 1 number.";
                url = "/add_user.jsp";
            }
            else {
                message = "";
                url = "/view_users.jsp";
                UserDB.insert(user);
            }
            
            ArrayList<User> userList = UserDB.selectUsers();
            
            request.setAttribute("userList", userList);
            request.setAttribute("message", message);
        
        } else if(action.equals("editButton")) {
            url = "/edit_user.jsp";    // the "edit" page
            
            // get username of single user from view_users.jsp
            // get parameters from the request
            String username = request.getParameter("username");

            // store data in User object
            User user = UserDB.selectUser(username);
            //sends to edit_user.jsp
            request.setAttribute("user", user);
            
        } else if(action.equals("edit")) {
            String message;
            //requests parameters from edit_user.jsp
            String username = request.getParameter("username");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String password = request.getParameter("password");
            
            // validate no empty fields and that password is valid
            if (firstName == null || lastName == null || password == null ||
                firstName.isEmpty() || lastName.isEmpty() || password.isEmpty() ) {
                message = "Please fill out all three text boxes.";
                url = "/edit_user.jsp";
                User user = UserDB.selectUser(username);
                request.setAttribute("user", user);
                
            } else if (!UserDB.validPass(password)) {
                message = "Password must be greater than 8 characters, and include "
                        + "at least 1 uppercase letter, 1 lowercase letter, and 1 number.";
                url = "/edit_user.jsp";
                User user = UserDB.selectUser(username);
                request.setAttribute("user", user);
                
            } else {
                //update the selected user in database
                message = "";
                url = "/view_users.jsp";
                User updatedUser = new User(firstName, lastName, username, password);
                UserDB.update(updatedUser);
                
            }
            
            // get new Arraylist and send back to view_users.jsp
            ArrayList<User> userList = UserDB.selectUsers();
            
            request.setAttribute("userList", userList);
            request.setAttribute("message", message);
        }
        
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet for the Users component of ServisU. Handles routing and "
                + "logic for the view_users.jsp, edit_user.jsp, and add_user.jsp pages.";
    }// </editor-fold>

}
