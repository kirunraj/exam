import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.sql.*;


public class login extends HttpServlet{
	public void doGet(HttpServletRequest req,HttpServletResponse res)
	throws ServletException,IOException
	{
		final String DB_Driver = "com.mysql.cj.jdbc.Driver";
		final String DB_URL = "jdbc:mysql://localhost:3306/test";
		final String USER = "root";
		final String PASS = "ssn@123";
		System.out.println("hello");
		try{
			Class.forName(DB_Driver);
			Connection conct=DriverManager.getConnection(DB_URL,USER,PASS);
            String user=req.getParameter("user");
			System.out.println("User: " + user);
			Statement stm=conct.createStatement();
			String sql="select password from temp where user=\'"+user+"\';";
			ResultSet rs=stm.executeQuery(sql);
			PrintWriter pw=res.getWriter();

			while(rs.next())
			{
				if(rs.getString("password").compareTo(req.getParameter("pass"))==0){
					RequestDispatcher rd = req.getRequestDispatcher("adminside.html");
            		rd.forward(req, res);
				}
				else{
					pw.print("Password mismatch");
            		RequestDispatcher rd = req.getRequestDispatcher("/admin.html");
        			rd.include(req, res); 
				}
			}
		}
		catch(Exception e){
			System.out.println(e);	
		}
	}
}