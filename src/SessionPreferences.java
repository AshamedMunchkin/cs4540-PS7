

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SessionPreferences
 */
@WebServlet("/SessionPreferences")
public class SessionPreferences extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("SessionPreferences.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!request.getParameter("backgroundColor").equals("default")) {
			request.getSession().setAttribute("backgroundColor", request.getParameter("backgroundColor"));
		} else {
			request.getSession().removeAttribute("backgroundColor");
		}
		if (!request.getParameter("fontColor").equals("default")) {
			request.getSession().setAttribute("fontColor", request.getParameter("fontColor"));
		} else {
			request.getSession().removeAttribute("fontColor");
		}
		if (!request.getParameter("fontSize").equals("default")) {
			request.getSession().setAttribute("fontSize", request.getParameter("fontSize"));
		} else {
			request.getSession().removeAttribute("fontSize");
		}
		request.getRequestDispatcher("SessionPreferences.jsp").forward(request, response);
	}

}
