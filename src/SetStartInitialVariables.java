

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class SetStartTime
 *
 */
@WebListener
public class SetStartInitialVariables implements ServletContextListener {

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0) {
    	// Set the starting time of the server for counting purposes.
        arg0.getServletContext().setAttribute("startTime", new Date());
        
        // Set up our map of colors.
        LinkedHashMap<String, String> colors = new LinkedHashMap<String, String>();
        colors.put("aqua", "Aqua");
        colors.put("black", "Black");
        colors.put("blue", "Blue");
        colors.put("fuchsia", "Fuchsia");
        colors.put("gray", "Gray");
        colors.put("green", "Green");
        colors.put("lime", "Lime");
        colors.put("maroon", "Maroon");
        colors.put("navy", "Navy");
        colors.put("olive", "Olive");
        colors.put("orange", "Orange");
        colors.put("purple", "Purple");
        colors.put("red", "Red");
        colors.put("silver", "Silver");
        colors.put("teal", "Teal");
        colors.put("white", "White");
        colors.put("yellow", "Yellow");
        arg0.getServletContext().setAttribute("colors", colors);
        
        // Set up our font sizes.
        LinkedList<Integer> fontSizes = new LinkedList<Integer>();
        fontSizes.add(new Integer(6));
        fontSizes.add(new Integer(7));
        fontSizes.add(new Integer(8));
        fontSizes.add(new Integer(9));
        fontSizes.add(new Integer(10));
        fontSizes.add(new Integer(11));
        fontSizes.add(new Integer(12));
        fontSizes.add(new Integer(14));
        fontSizes.add(new Integer(16));
        fontSizes.add(new Integer(18));
        fontSizes.add(new Integer(21));
        fontSizes.add(new Integer(24));
        fontSizes.add(new Integer(36));
        fontSizes.add(new Integer(46));
        fontSizes.add(new Integer(60));
        arg0.getServletContext().setAttribute("fontSizes", fontSizes);
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
    }
	
}
