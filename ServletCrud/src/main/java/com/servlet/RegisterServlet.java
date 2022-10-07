package com.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/RS")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	public RegisterServlet() {
        super();        
    }

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		boolean result=true;
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Crud","root","root");
			PreparedStatement ps = con.prepareStatement("insert into users values(?,?)");
			String id = req.getParameter("uname");
			String pwd = req.getParameter("upass");
			ps.setString(1, id);
			ps.setString(2, pwd);
			result=ps.execute();
			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(result)
		{			
			pw.println("<h1>Registration failed register again</h1>");
		}
		else {
			pw.println("<h1>Registration successful</h1>");
			pw.println("<a href=\"index.html\">Login here</a>");
		}
	}
}