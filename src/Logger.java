

import java.io.IOException;
import java.util.LinkedList;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class Logger
 */
@WebFilter(urlPatterns = {"/*"})
public class Logger implements Filter {

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// Cast the request to a HttpServletRequest.
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		// Prevent caching (hopefully)
		httpResponse.setHeader("Cache-control", "no-cache, no-store");
		httpResponse.setHeader("Pragma", "no-cache"); 
		httpResponse.setHeader("Expires", "-1");
		
		// See if this browser has been counted.
		boolean uncounted = true;
		Cookie[] cookies = httpRequest.getCookies();
		if (cookies != null)
		for (int i = 0; i < cookies.length; i++) {
			if (cookies[i].getName().equals("counted") && cookies[i].getValue().equals(((Date) httpRequest.getServletContext().getAttribute("startTime")).toString())) {
				uncounted = false;
				break;
			}
		}
		
		if (httpRequest.getSession().getAttribute("browserId") == null) {
			uncounted = true;
		}
		
		// Make sure counter is set.
		Integer counter = (Integer) httpRequest.getServletContext().getAttribute("counter");
		if (counter == null) {
			counter = 0;
		}
		httpRequest.getServletContext().setAttribute("counter", counter);
		
		// If this browser hasn't been counted, count it and assign it an id.
		if (uncounted) {	
			httpRequest.getSession().setAttribute("browserId", counter.intValue());
			Cookie countedCookie = new Cookie("counted", ((Date) httpRequest.getServletContext().getAttribute("startTime")).toString());
			countedCookie.setMaxAge(60*60*24*365);
			httpResponse.addCookie(countedCookie);
			counter++;
			httpRequest.getServletContext().setAttribute("counter", counter);
		}
		
		// Create a new history item.
		HistoryItem historyItem = new HistoryItem(
				(int) httpRequest.getSession().getAttribute("browserId"),
				httpRequest.getHeader("User-Agent"),
				new Date(),
				httpRequest.getRequestURI().replaceFirst("/ps7-omarshal", ""),
				httpRequest.getHeader("Referer"));
		
		// Get the list of session history items. Create one if one doesn't exist.
		@SuppressWarnings("unchecked")
		LinkedList<HistoryItem> sessionHistory = (LinkedList<HistoryItem>) httpRequest.getSession().getAttribute("sessionHistory");
		if (sessionHistory == null) {
			sessionHistory = new LinkedList<HistoryItem>();
		}
		
		// Get the list of application history items. Create one if one doesn't exist.
		@SuppressWarnings("unchecked")
		LinkedList<HistoryItem> applicationHistory = (LinkedList<HistoryItem>) httpRequest.getServletContext().getAttribute("applicationHistory");
		if (applicationHistory == null) {
			applicationHistory = new LinkedList<HistoryItem>();
		}
		
		// Commit the altered (or new) lists of history items to their respective scopes.
		sessionHistory.add(historyItem);
		applicationHistory.add(historyItem);
		
		httpRequest.getSession().setAttribute("sessionHistory", sessionHistory);
		httpRequest.getServletContext().setAttribute("applicationHistory", applicationHistory);

		// pass the request along the filter chain
		chain.doFilter(httpRequest, httpResponse);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
