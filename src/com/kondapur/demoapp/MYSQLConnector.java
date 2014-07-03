package com.kondapur.demoapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MYSQLConnector {

	  private Connection connect = null;
	  private Statement statement = null;
	  private ResultSet resultSet = null;
	  public String[] readDataBase() throws Exception {
	    try {
	      // this will load the MySQL driver, each DB has its own driver
	      
	      Class.forName("com.mysql.jdbc.Driver");
	      //DriverManager.registerDriver(new com.mysql.jdbc.Driver());
	      // setup the connection with the DB.
	      connect = DriverManager
	          .getConnection("jdbc:mysql://localhost/shbooks?"
	              + "user=root&password=machine8");

	      // statements allow to issue SQL queries to the database
	      statement = connect.createStatement();
	      // resultSet gets the result of the SQL query
	      resultSet = statement
	          .executeQuery("select * from SHBooks.demoLogin");
	      return writeResultSet(resultSet);

	      // preparedStatements can use variables and are more efficient
	     /* preparedStatement = connect
	          .prepareStatement("insert into  FEEDBACK.COMMENTS values (default, ?, ?, ?, ? , ?, ?)");
	      // "myuser, webpage, datum, summary, COMMENTS from FEEDBACK.COMMENTS");
	      // parameters start with 1
	      preparedStatement.setString(1, "Test");
	      preparedStatement.setString(2, "TestEmail");
	      preparedStatement.setString(3, "TestWebpage");
	      preparedStatement.setDate(4, new java.sql.Date(2009, 12, 11));
	      preparedStatement.setString(5, "TestSummary");
	      preparedStatement.setString(6, "TestComment");
	      preparedStatement.executeUpdate();

	      preparedStatement = connect
	          .prepareStatement("SELECT myuser, webpage, datum, summary, COMMENTS from FEEDBACK.COMMENTS");
	      resultSet = preparedStatement.executeQuery();
	      writeResultSet(resultSet);

	      // remove again the insert comment
	      preparedStatement = connect
	      .prepareStatement("delete from FEEDBACK.COMMENTS where myuser= ? ; ");
	      preparedStatement.setString(1, "Test");
	      preparedStatement.executeUpdate();
	      
	      resultSet = statement
	      .executeQuery("select * from FEEDBACK.COMMENTS");
	      writeMetaData(resultSet);*/
	      
	    } catch (Exception e) {
	      throw e;
	    } finally {
	      close();
	    }

	  }

	  private void writeMetaData(ResultSet resultSet) throws SQLException {
	    // now get some metadata from the database
	    System.out.println("The columns in the table are: ");
	    System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
	    for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
	      System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
	    }
	  }

	  private String [] writeResultSet(ResultSet resultSet) throws SQLException {
	    // resultSet is initialised before the first data set
	    String loginInfo[] = new String[3];
	    while (resultSet.next()) {
	      // it is possible to get the columns via name
	      // also possible to get the columns via the column number
	      // which starts at 1
	      // e.g., resultSet.getSTring(2);
	      loginInfo[0] = resultSet.getString("loginid");
	      loginInfo[1] = resultSet.getString("username");
	      loginInfo[2] = resultSet.getString("emailid");
	    }
	    return loginInfo;
	  }

	  // you need to close all three to make sure
	  private void close() {

	  }
}
