// Copyright (c) 2018 Cilogi. All Rights Reserved.
//
// File:        LoginServlet.java
//
// Copyright in the whole and every part of this source file belongs to
// Cilogi (the Author) and may not be used, sold, licenced, 
// transferred, copied or reproduced in whole or in part in 
// any manner or form or in or on any media to any person other than 
// in accordance with the terms of The Author's agreement
// or otherwise without the prior written consent of The Author.  All
// information contained in this source file is confidential information
// belonging to The Author and as such may not be disclosed other
// than in accordance with the terms of The Author's agreement, or
// otherwise, without the prior written consent of The Author.  As
// confidential information this source file must be kept fully and
// effectively secure at all times.
//


package wpd2.teamR.servlet;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wpd2.teamR.dao.UserDAO;
import wpd2.teamR.models.User;
import wpd2.teamR.util.Password;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


public class LoginServlet extends BaseServlet {
    @SuppressWarnings("unused")
    static final Logger LOG = LoggerFactory.getLogger(LoginServlet.class);

    private final String LOGIN_TEMPLATE = "login.mustache";

    private UserDAO users;

    public LoginServlet() {
        users = new UserDAO();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userName = UserFuncs.getCurrentUser(request);
        showView(response, LOGIN_TEMPLATE, userName);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        // CHECK WHETHER USER WISHES TO LOGIN OR REGISTER
        if(request.getParameter("buttons").equals("register")){

            // CARRY OUT REGISTRATION LOGIC
            this.register(request, response);

        } else {

            // CARRY OUT LOGIN LOGIC
            this.login(request,response);

        }





            LOG.debug("This should have saved the user in DB");


        }
        // do nothing, we stay on the page,
        // could also display a warning message by passing parameter to /login on redirect


    /**
     * Login user, based on data passed from form.
     * @param request HTTP Servlet Request
     * @param response HTTP Servlet Response
     * @throws IOException
     */
    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException{

        // GET THE PARAMS FROM THE FORM
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {

            // CHECK USER EXISTS AND PASSWORD MATCHES
            String result = users.checkIsValidUser(email, password);
            if(!result.isEmpty()){

                // TRUE, SO SET SESSION AND REDIRECT TO PAGE
                setCurrentUser(request, result);
                response.sendRedirect("/private");

            } else {

                // LOGIN WASNT SUCCESSFUL
                response.sendRedirect("/login");

            }

        } catch (SQLException error){

            LOG.debug(error.toString());

        }

        LOG.debug("This should have saved the user in DB");

    }

    private void register(HttpServletRequest request, HttpServletResponse response) throws IOException{

        // GET THE PARAMS FROM THE FORM
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User newUser = new User(fname, lname, email, password);


            if(users.registerUser(newUser)){
                // WAS SUCCESS
                // TODO: MAYBE EMAIL

                // SET A SESSION, AND REDIRECT
                setCurrentUser(request, email);
                response.sendRedirect("/private");

            } else {
                // WASNT A SUCCESS
                response.sendRedirect("/login");
            }

            // CHECK USER EXISTS AND PASSWORD MATCHES
//            String result = users.checkIsValidUser(email, password);
//            if(!result.isEmpty()){
//
//                // TRUE, SO SET SESSION AND REDIRECT TO PAGE
//                setCurrentUser(request, result);
//                response.sendRedirect("/private");
//
//            } else {
//
//                // LOGIN WASNT SUCCESSFUL
//                response.sendRedirect("/login");
//
//            }


//        catch (SQLException error){
//
//            LOG.debug(error.toString());
//
//        }

        LOG.debug("This should have saved the user in DB");

    }

}
