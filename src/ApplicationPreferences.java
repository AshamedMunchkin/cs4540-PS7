

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ApplicationPreferences
 */
@WebServlet("/ApplicationPreferences")
public class ApplicationPreferences extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("ApplicationPreferences.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!request.getParameter("backgroundColor").equals("default")) {
			request.getServletContext().setAttribute("backgroundColor", request.getParameter("backgroundColor"));
		} else {
			request.getServletContext().removeAttribute("backgroundColor");
		}
		if (!request.getParameter("fontColor").equals("default")) {
			request.getServletContext().setAttribute("fontColor", request.getParameter("fontColor"));
		} else {
			request.getServletContext().removeAttribute("fontColor");
		}
		if (!request.getParameter("fontSize").equals("default")) {
			request.getServletContext().setAttribute("fontSize", request.getParameter("fontSize"));
		} else {
			request.getServletContext().removeAttribute("fontSize");
		}
		request.getRequestDispatcher("ApplicationPreferences.jsp").forward(request, response);
	}

}
