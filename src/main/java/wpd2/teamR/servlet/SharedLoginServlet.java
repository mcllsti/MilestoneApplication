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
import wpd2.teamR.dao.LinkDAO;
import wpd2.teamR.dao.UserDAO;
import wpd2.teamR.models.Link;
import wpd2.teamR.models.User;
import wpd2.teamR.util.FlashMessage;
import wpd2.teamR.util.SessionFunctions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public class SharedLoginServlet extends BaseServlet {
    @SuppressWarnings("unused")
    static final Logger LOG = LoggerFactory.getLogger(SharedLoginServlet.class);

    private LinkDAO links;

    public SharedLoginServlet() {
        links = new LinkDAO();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // CHECK IF LINK EXISTS
        String urlHash = getUrlParamter(request.getRequestURI());

        FlashMessage message = SessionFunctions.getFlashMessage(request);
        Map<String,Object> viewBag = new HashMap<>();
        viewBag.put("message",message);
        viewBag.put("hash",urlHash);

        showView(response,"shared/shared-login.mustache",viewBag);

//        DISPLAY FORM

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // GET THE URL ID
        String urlHash = getUrlParamter(request.getRequestURI());
        String email = request.getParameter("email");

        Link link = null;

        try {
            link = links.findByEmailAndHash(email, urlHash);
        } catch(SQLException error){
            //TODO: THERE WAS AN ERROR DO SOMETHING
            SessionFunctions.setFlashMessage(request,new FlashMessage(FlashMessage.FlashType.ERROR,"Something went wrong","Sorry, something went wrong. Please make sure you entered the correct details."));
            response.sendRedirect("/shared/"+urlHash);
        }

        // SET SESSION REDIRECT
        if(link != null) {
            System.out.println("THIS WORKED ON");
        } else {
            //TODO: THERE WAS AN ERROR DO SOMETHING
            SessionFunctions.setFlashMessage(request,new FlashMessage(FlashMessage.FlashType.INFO,"Uh oh...","Couldn't retrieve the link you were looking for."));
            response.sendRedirect("/shared/"+urlHash);
        }

        // IF NOT, REDIRECT WITH ERROR

    }

}
