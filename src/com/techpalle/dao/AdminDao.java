package com.techpalle.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

  public class AdminDao 
  {
	
	   private static final String url="jdbc:mysql://localhost:3306/priya";
	   private static final String dbusername="root";
	   private static final String dbpassword="admin";
	   
	   private static Connection con=null;
	   private static PreparedStatement ps=null;
	   private static Statement s=null;
	   private static ResultSet rs=null;
	   
	private static final String validateQuery="select username,password from admin where username=? and password=?";
   
	
	 public static boolean validate(String username,String password)
		{
	       boolean b=false;
			 try
		  	   {
				 try 
				 {
					Class.forName("com.mysql.cj.jdbc.Driver");
					con=DriverManager.getConnection(url,dbusername,dbpassword);
					 ps=con.prepareStatement(validateQuery);
			  		 ps.setString(1,username);
			  		 ps.setString(2,password);

			  		 rs=ps.executeQuery();
			  		
			  		 b=rs.next();
				} 
				 catch (ClassNotFoundException e) 
				 
				 {
					e.printStackTrace();
				}
				 
              
		  	   }
		  			 catch (SQLException e) 
		  			 {
		  				e.printStackTrace();
		  			}
				 finally
				 {
					 try
					 {
						 if(rs!=null)
							 rs.close();
						 if(ps!=null)
							 ps.close();
						 if(con!=null)
							 con.close();
					 }
					 catch(SQLException e)
					 {
			  		  e.printStackTrace();

					 }
				 }
			return b;
		  }

}
