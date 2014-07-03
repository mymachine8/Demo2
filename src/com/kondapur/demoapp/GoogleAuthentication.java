package com.kondapur.demoapp;


import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.gson.Gson;

/**
 * Simple server to demonstrate how to use Google+ Sign-In and make a request
 * via your own server.
 */
public class GoogleAuthentication {
  /*
   * Default HTTP transport to use to make HTTP requests.
   */
  private static final HttpTransport TRANSPORT = new NetHttpTransport();

  /*
   * Default JSON factory to use to deserialize JSON.
   */
  private static final JacksonFactory JSON_FACTORY = new JacksonFactory();

  /*
   * Gson object to serialize JSON responses to requests to this servlet.
   */
  private static final Gson GSON = new Gson();

  /*
   * Creates a client secrets object from the client_secrets.json file.
   */

  /*
   * This is the Client ID that you generated in the API Console.
   */
  private static final String CLIENT_ID = "408623907339-t2to3m90fu8fmtdetr2r032ummhv80b5.apps.googleusercontent.com";

  /*
   * This is the Client Secret that you generated in the API Console.
   */
  private static final String CLIENT_SECRET = "bCoumZnsWHA9M1NGeunbjh_J";

   public static String getClientID(){
	   return CLIENT_ID;
   }
   
   public static String getClientSecret(){
	   return CLIENT_SECRET;
   }
   
   public static HttpTransport getTransport(){
	   return TRANSPORT;
   }
   
   public static JacksonFactory getJsonFactory(){
	   return JSON_FACTORY;
   }
   
   public static Gson getGson(){
	   return GSON;
   }

}
