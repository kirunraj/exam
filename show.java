import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.sql.*;


public class show extends HttpServlet{
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
			String sql="select * from temp;";
			ResultSet rs=stm.executeQuery(sql);
			PrintWriter pw=res.getWriter();

			while(rs.next())
			{
				String user1=rs.getString("user");
                String pass1=rs.getString("password");
                pw.println("user:"+user1+"\n");
                pw.println("password:"+pass1+"\n");

			}
		}
		catch(Exception e){
			System.out.println(e);	
		}
	}
}