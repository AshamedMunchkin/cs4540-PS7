

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SessionHistory
 */
@WebServlet("/SessionHistory")
public class SessionHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getHeader("Referer").equals(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/ps7-omarshal" + request.getServletPath()) && (int) request.getSession().getAttribute("sessionRefreshRate") > 0) {
			response.setIntHeader("Refresh", (int) request.getSession().getAttribute("sessionRefreshRate"));
		} else {
			request.getSession().removeAttribute("sessionRefreshRate");
		}
		request.getRequestDispatcher("SessionHistory.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("submit").equals("refresh") && !request.getParameter("refreshRate").equals("no")) {
			request.getSession().setAttribute("sessionRefreshRate", Integer.parseInt(request.getParameter("refreshRate")));
			response.setIntHeader("Refresh", (int) request.getSession().getAttribute("sessionRefreshRate"));
		} else {
			request.getSession().removeAttribute("sessionRefreshRate");
		}
		if (request.getParameter("submit").equals("sessionReset")) {
			request.getSession().setAttribute("sessionHistory", new LinkedList<HistoryItem>());
		}
		
		request.getRequestDispatcher("SessionHistory.jsp").forward(request, response);
	}

}
