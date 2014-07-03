package com.kondapur.demoapp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpResponse;

/**
 * Revoke current user's token and reset their session.
 */
public class DisconnectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
      response.setContentType("application/json");
      
      // Only disconnect a connected user.
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
        // Execute HTTP GET request to revoke current token.
        HttpResponse revokeResponse = GoogleAuthentication.getTransport().createRequestFactory()
            .buildGetRequest(new GenericUrl(
                String.format(
                    "https://accounts.google.com/o/oauth2/revoke?token=%s",
                    credential.getAccessToken()))).execute();
        // Reset the user's session.
        request.getSession().removeAttribute("token");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().print(GoogleAuthentication.getGson().toJson("Successfully disconnected."));
      } catch (IOException e) {
        // For whatever reason, the given token was invalid.
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.getWriter().print(GoogleAuthentication.getGson().toJson("Failed to revoke token for given user."));
      }
    }
  }