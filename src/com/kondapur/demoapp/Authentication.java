package com.kondapur.demoapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;

public class Authentication {

	private static final String CLIENT_ID = "408623907339-t2to3m90fu8fmtdetr2r032ummhv80b5.apps.googleusercontent.com";
	private static final String APPLICATION_NAME = "SHBooks";
	public static String auth(HttpServletRequest request){
		String state = new BigInteger(130, new SecureRandom()).toString(32);
		  request.getSession().setAttribute("state", state);
		  // Read index.html into memory, and set the Client ID,
		  // Token State, and Application Name in the HTML before serving it.
		  try {
			return new Scanner(new File("index.html"), "UTF-8")
			      .useDelimiter("\\A").next()
			      .replaceAll("[{]{2}\\s*CLIENT_ID\\s*[}]{2}", CLIENT_ID)
			      .replaceAll("[{]{2}\\s*STATE\\s*[}]{2}", state)
			      .replaceAll("[{]{2}\\s*APPLICATION_NAME\\s*[}]{2}",
			                  APPLICATION_NAME);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}
