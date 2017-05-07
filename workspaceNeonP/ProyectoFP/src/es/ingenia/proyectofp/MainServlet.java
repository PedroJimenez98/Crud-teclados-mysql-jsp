package es.ingenia.proyectofp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * Servlet implementation class MainServlet
 */
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MainServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Context ctx;
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs;
		String accion = request.getParameter("action");
		String nextJSP = "";
		String teclados[][] = new String[25][5];
		int i = 0;
		String query = "";
		String teclado[] = new String[5];

		switch (accion) {
		case "main":

			query = "SELECT * FROM TECLADOS";

			try {

				ctx = new InitialContext();
				DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/ProyectoFP");
				connection = ds.getConnection();
				stmt = connection.createStatement();
				rs = stmt.executeQuery(query);

				while (rs.next()) {
					String modelo = rs.getString("MODELO");
					String precio = rs.getString("PRECIO");
					String tipo = rs.getString("TIPO");
					String marca = rs.getString("MARCA");
					String cantidad = rs.getString("CANTIDAD");

					teclados[i][0] = marca;
					teclados[i][1] = modelo;
					teclados[i][2] = tipo;
					teclados[i][3] = precio;
					teclados[i][4] = cantidad;
					i++;
				}
				String tecladosAUX[][] = new String[i][5];
				for (int j = 0; j < tecladosAUX.length; j++) {
					for (int k = 0; k < 5; k++) {
						tecladosAUX[j][k] = teclados[j][k];
					}
				}
				request.setAttribute("tecladosAUX", tecladosAUX);

				nextJSP = "/Pagina0.jsp";

			} catch (NamingException e) {
				response.getWriter().append(e.getMessage());
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
				response.getWriter().append(e.getMessage());
			} finally {
				if (stmt != null) {
					try {
						stmt.close();
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
						dispatcher.forward(request, response);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}

			break;
			
		case "addnew":
			
			nextJSP = "/Pagina1.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
			dispatcher.forward(request, response);
			break;
			
		case "modificar":
			
			query = "SELECT * FROM TECLADOS WHERE MODELO LIKE '" + request.getParameter("modelo") + "'";     
			try {

				ctx = new InitialContext();
				DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/ProyectoFP");
				connection = ds.getConnection();
				stmt = connection.createStatement();
				rs = stmt.executeQuery(query);

				while (rs.next()) {
					String marca = rs.getString("MARCA");
					String modelo = rs.getString("MODELO");
					String tipo = rs.getString("TIPO");
					String precio = rs.getString("PRECIO");
					String cantidad = rs.getString("CANTIDAD");

					teclado[0] = marca;
					teclado[1] = modelo;
					teclado[2] = tipo;
					teclado[3] = precio;
					teclado[4] = cantidad;
					i++;
				}
				request.setAttribute("teclado", teclado);
			
			nextJSP = "/Pagina2.jsp";
			
			RequestDispatcher dispatcher1 = getServletContext().getRequestDispatcher(nextJSP);
			dispatcher1.forward(request, response);
			break;
			} catch (NamingException e) {
				response.getWriter().append(e.getMessage());
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
				response.getWriter().append(e.getMessage());
			} finally {
				if (stmt != null) {
					try {
						stmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Context ctx;
		Connection connection = null;
		Statement stmt = null;
		String accion = request.getParameter("action");
		String nextJSP = "";
		String query = "";
		
		try {
			ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/ProyectoFP");
			connection = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

		}
		
		switch (accion) {
		
		case "added":
				query = "INSERT INTO TECLADOS (MARCA, MODELO, TIPO, PRECIO, CANTIDAD) VALUES ('" + request.getParameter("marca") + "', '" + request.getParameter("modelo") + "', '" + request.getParameter("tipo") + "', '" + request.getParameter("precio") + "', '" + request.getParameter("cantidad") + "')";
					
				try {
					stmt = connection.createStatement();
					int rs = stmt.executeUpdate(query);
					response.sendRedirect(request.getContextPath() + "/MainServlet?action=main");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
								
				break;
			
		case "eliminar":
			try {
				ctx = new InitialContext();
				DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/ProyectoFP");
				connection = ds.getConnection();

				query = "DELETE FROM teclados WHERE MODELO='" + request.getParameter("modelo") + "'";
				stmt = connection.createStatement();
				int insert = stmt.executeUpdate(query);

				// response.getWriter().append("</table>");
			} catch (NamingException e) {
				response.getWriter().append(e.getMessage());
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
				response.getWriter().append(e.getMessage());
			} finally {
				if (stmt != null) {
					try {
						response.sendRedirect(request.getContextPath() + "/MainServlet?action=main");

						connection.close();
						stmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
				
			break;
		}
	}
}