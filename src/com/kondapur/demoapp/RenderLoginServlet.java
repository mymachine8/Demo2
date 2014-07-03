package com.kondapur.demoapp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.client.auth.oauth2.TokenResponseException;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.gson.Gson;



import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class RenderLoginServlet
 */
public class RenderLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      response.setContentType("text/html");
	      try {
	        // Create a state token to prevent request forgery.
	        // Store it in the session for later validation.
	        String state = new BigInteger(130, new SecureRandom()).toString(32);
	        request.getSession().setAttribute("state", state);
	        // Fancy way to read index.html into memory, and set the client ID
	        // and state values in the HTML before serving it.
	        String str = System.getProperty("user.dir");
	        response.getWriter().print(new Scanner(new File("template/LoginPage.html"), "UTF-8")
	            .useDelimiter("\\A").next()
	            .replaceAll("[{]{2}\\s*CLIENT_ID\\s*[}]{2}", GoogleAuthentication.getClientID())
	            .replaceAll("[{]{2}\\s*STATE\\s*[}]{2}", state)
	            .toString());
	        response.setStatus(HttpServletResponse.SC_OK);
	      } catch (FileNotFoundException e) {
	        // When running the quickstart, there was some path issue in finding
	        // index.html.  Double check the quickstart guide.
	        e.printStackTrace();
	        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
	        response.getWriter().print(e.toString());
	      }
	}
}
