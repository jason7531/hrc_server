package com.highradius;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

/**
 * Servlet implementation class EditSalesOrder
 */
@WebServlet("/DeleteInvoice")
public class DeleteInvoice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteInvoice() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
//			BufferedReader reader = request.getReader();
//			invoice = reader.readLine();
//			invoice =  invoice.substring(1, invoice.length() - 1);
			
			
//			String final_values[] = invoice.split(":", -1);
//			JSONObject json = new JSONObject(invoice);
//			System.out.println(json.getString("slNo"));
			String slNo = request.getParameter("slNo");
			System.out.println(slNo);
			Connection conn = GetConnection.connectToDB();
			String sql_statement = "DELETE FROM winter_internship WHERE sl_no = ?";
			PreparedStatement ps = conn.prepareStatement(sql_statement);
			ps.setString(1, slNo);
			System.out.println(ps);
			ps.executeUpdate();
			conn.close();
		}
		catch(Exception e) {
			
		}
	}

}
