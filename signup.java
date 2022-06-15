import java.io.*;
import java.util.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;


public class signup extends HttpServlet{
	public void doGet(HttpServletRequest req,HttpServletResponse res)
	throws ServletException,IOException{
		final String JDBC_Driver="com.mysql.cj.jdbc.Driver";
		final String DB_URL="jdbc:mysql://localhost/test";
		final String USER="root";
		final String PASS="ssn@123";
        PrintWriter out=res.getWriter();
        res.setContentType("text/html");
		try{
			Class.forName(JDBC_Driver);
			Connection conct=DriverManager.getConnection(DB_URL,USER,PASS);
			Statement stm=conct.createStatement();
			String sql="insert into temp values(";
			sql=sql+"'"+req.getParameter("user")+"','"+req.getParameter("pass")+"');";
			stm.execute(sql);
            out.print("<h1>Inserted</h1>");
			RequestDispatcher rd = req.getRequestDispatcher("show");
            rd.forward(req, res);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}