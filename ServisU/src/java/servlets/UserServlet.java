/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import data.PasswordUtil;
import data.UserDB;
import java.io.IOException;
import java.util.*;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.User;
import util.MailUtilLocal;

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
            String email = request.getParameter("email");

            // store data in User object
            User user = new User(firstName, lastName, email, username, password);

            // validate the parameters
            String message;
            if (firstName == null || lastName == null || email == null || username == null || password == null ||
                firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty() ) {
                message = "Please fill out all text boxes.";
                url = "/add_user.jsp";
            } else if (UserDB.userExists(username)){
                message = "Username already in use.";
                url = "/add_user.jsp";
            } else if (!PasswordUtil.validPass(password)) {
                message = "Password must be greater than 8 characters, and include "
                        + "at least 1 uppercase letter, 1 lowercase letter, and 1 number.";
                url = "/add_user.jsp";
            }
            else {
                // user passes validation and can be added
                message = "";
                UserDB.insert(user);
                request.setAttribute("user", user);

            // send email to user
            String to = email;
            String from = "email_list@murach.com";
            String subject = "Welcome to our email list";
            String body = "Dear " + firstName + ",\n\n"
                    + "Thanks for joining our email list. We'll make sure to send "
                    + "you announcements about new products and promotions.\n"
                    + "Have a great day and thanks again!\n\n"
                    + "Kelly Slivkoff\n"
                    + "Mike Murach & Associates";
            boolean isBodyHTML = false;

            try {
                MailUtilLocal.sendMail(to, from, subject, body, isBodyHTML);
            } catch (MessagingException e) {
                String errorMessage
                        = "ERROR: Unable to send email. "
                        + "Check Tomcat logs for details.<br>"
                        + "NOTE: You may need to configure your system "
                        + "as described in chapter 14.<br>"
                        + "ERROR MESSAGE: " + e.getMessage();
                request.setAttribute("errorMessage", errorMessage);
                this.log(
                        "Unable to send email. \n"
                        + "Here is the email you tried to send: \n"
                        + "=====================================\n"
                        + "TO: " + email + "\n"
                        + "FROM: " + from + "\n"
                        + "SUBJECT: " + subject + "\n"
                        + "\n"
                        + body + "\n\n");
            }
            url = "/thanks.jsp";
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
            String email = request.getParameter("email");
            String lastName = request.getParameter("lastName");
            String password = request.getParameter("password");
            
            // validate no empty fields and that password is valid
            if (firstName == null || lastName == null || email == null || username == null || password == null ||
                firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty()) {
                message = "Please fill out all three text boxes.";
                url = "/edit_user.jsp";
                User user = UserDB.selectUser(username);
                request.setAttribute("user", user);
                
            } else if (!PasswordUtil.validPass(password)) {
                message = "Password must be greater than 8 characters, and include "
                        + "at least 1 uppercase letter, 1 lowercase letter, and 1 number.";
                url = "/edit_user.jsp";
                User user = UserDB.selectUser(username);
                request.setAttribute("user", user);
                
            } else {
                //hash password (optional) and update the selected user in database
                message = "";
                url = "/view_users.jsp";
                User updatedUser = new User(firstName, lastName, email, username, password);
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
