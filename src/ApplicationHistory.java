

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ApplicationHistory
 */
@WebServlet("/ApplicationHistory")
public class ApplicationHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getHeader("Referer").equals(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/ps7-omarshal" + request.getServletPath()) && (int) request.getSession().getAttribute("applicationRefreshRate") > 0) {
			response.setIntHeader("Refresh", (int) request.getSession().getAttribute("applicationRefreshRate"));
		} else {
			request.getSession().removeAttribute("applicationRefreshRate");
		}
		request.getRequestDispatcher("ApplicationHistory.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("submit").equals("refresh") && !request.getParameter("refreshRate").equals("no")) {
			request.getSession().setAttribute("applicationRefreshRate", Integer.parseInt(request.getParameter("refreshRate")));
			response.setIntHeader("Refresh", (int) request.getSession().getAttribute("applicationRefreshRate"));
		} else {
			request.getSession().removeAttribute("applicationRefreshRate");
		}
		if (request.getParameter("submit").equals("applicationReset")) {
			request.getServletContext().setAttribute("applicationHistory", new LinkedList<HistoryItem>());
			request.getServletContext().setAttribute("startTime", new Date());
			request.getServletContext().setAttribute("counter", new Integer(0));
		}
		
		request.getRequestDispatcher("ApplicationHistory.jsp").forward(request, response);
	}

}
