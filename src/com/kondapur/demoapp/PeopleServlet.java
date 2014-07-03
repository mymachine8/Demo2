package com.kondapur.demoapp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;

public class PeopleServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
      response.setContentType("application/json");

      // Only fetch a list of people for connected users.
      String tokenData = (String) request.getSession().getAttribute("token");
      if (tokenData == null) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().print(GoogleAuthentication.getGson().toJson("Current user not connected."));
        return;
      }
      try {
        // Build credential from stored token data.
        GoogleCredential credential = new GoogleCredential.Builder()
            .setJsonFactory(GoogleAuthentication.getJsonFactory())
            .setTransport(GoogleAuthentication.getTransport())
            .setClientSecrets(GoogleAuthentication.getClientID(), GoogleAuthentication.getClientSecret()).build()
            .setFromTokenResponse(GoogleAuthentication.getJsonFactory().fromString(
                tokenData, GoogleTokenResponse.class));
        // Create a new authorized API client.
        response.setStatus(HttpServletResponse.SC_OK);
      } catch (IOException e) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.getWriter().print(GoogleAuthentication.getGson().toJson("Failed to read data from Google. " +
            e.getMessage()));
      }
    }
  }