package com.bailwal.crudwithlambda.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PostalCodeRepo {

	
	void Add()
	
	void executeConnection()
	{
		 System.out.println( "Hello World mahesh!" );
	        try (Connection conn = DriverManager.getConnection(
	                "jdbc:postgresql://postgre-instance.c3enhs8xm5kf.us-east-1.rds.amazonaws.com:5432/purolator", "postgres", "password123")) {

	      
	            Statement stmt =  conn.createStatement();
	            
	            String sql = "INSERT INTO test (id,name) "
	                    + "VALUES (1,'ttttt123');";
	           
	                 stmt.executeUpdate(sql);
	                 stmt.close();
	                // conn .commit();
	                 conn.close();
	                 
	                 System.out.println("Done");
	            
	        } catch (SQLException e) {
	            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}
}
