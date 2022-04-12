package com.highradius;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String invoice = null;
		
		try {
			BufferedReader reader = request.getReader();
			invoice = reader.readLine();
			invoice =  invoice.substring(1, invoice.length() - 1);
			
			System.out.println(invoice);
			String final_values[] = invoice.split(":", -1);
			Connection conn = GetConnection.connectToDB();
			String sql_statement = "DELETE FROM winter_internship WHERE sl_no = ?";
			
			for(int i = 2; i < final_values.length; ++i) {
				PreparedStatement st = conn.prepareStatement(sql_statement);
				st.setString(1, final_values[i].substring(1, 2));
				System.out.println(st);
				st.executeUpdate();
			}
			conn.close();
		}
		catch(Exception e) {
			
		}
	}

}
