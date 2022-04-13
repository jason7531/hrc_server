package com.highradius;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class SearchSalesOrder
 */
@WebServlet("/SearchInvoice")
public class SearchInvoice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchInvoice() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		

		
		try {
			Connection conn = GetConnection.connectToDB();
			
			String docId = request.getParameter("docId");
			String businessYear = request.getParameter("businessYear");
			String customerNumber = request.getParameter("customerNumber");
			String invoiceId = request.getParameter("invoiceId");

			
			Statement st = conn.createStatement();
			String sql_statement = "SELECT * FROM winter_internship WHERE doc_id LIKE '" + docId + "%' and buisness_year LIKE '%" + businessYear + "%' and cust_number LIKE '%" + customerNumber + "%' and invoice_id LIKE '%" + invoiceId + "%' ";
			System.out.println(sql_statement);
			ResultSet rs = st.executeQuery(sql_statement);
			
			ArrayList<InvoiceDetails> data = new ArrayList<>();
			while(rs.next()) {
				InvoiceDetails inv = new InvoiceDetails();
				inv.setBusinessCode(rs.getString("business_code"));
				
				inv.setCustNumber(rs.getString("cust_number"));
				inv.setClearDate(rs.getString("clear_date") == null ? "" : rs.getString("clear_date").substring(0, 10));
				inv.setBusinessYear(rs.getInt("buisness_year"));
				inv.setDocID(rs.getLong("doc_id"));
				inv.setPostingDate(rs.getString("posting_date"));
				inv.setDocumentCreateDate(rs.getString("document_create_date"));
				inv.setDueInDate(rs.getString("due_in_date"));
				inv.setInvoiceCurrency(rs.getString("invoice_currency"));
				inv.setDocumentType(rs.getString("document_type"));
				inv.setPostingID(rs.getInt("posting_id"));
				inv.setTotalOpenAmount(rs.getFloat("total_open_amount"));
				inv.setBaselineCreateDate(rs.getString("baseline_create_date"));
				inv.setCustPaymentTerms(rs.getString("cust_payment_terms"));
				inv.setInvoiceID(rs.getLong("invoice_id"));
				inv.setIsOpen(rs.getInt("isOpen"));
				inv.setSl_no(rs.getString("sl_no"));
				data.add(inv);
			}
			
			Gson gson = new GsonBuilder().serializeNulls().create();
			String invoices = gson.toJson(data);
			
			out.print(invoices);
			response.setStatus(200);
			out.flush();
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
