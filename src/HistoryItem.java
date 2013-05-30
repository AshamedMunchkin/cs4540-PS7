import java.io.Serializable;
import java.util.Date;

public class HistoryItem implements Serializable {
	private static final long serialVersionUID = 1L;
	private int browserId;
	private String browserType;
	private Date requestDate;
	private String requestUrl;
	private String refererUrl;
	
	public HistoryItem(int browserId, String browserType, Date requestDate, String requestUrl, String refererUrl) {
		super();
		this.browserId = browserId;
		this.browserType = browserType;
		this.requestDate = requestDate;
		this.requestUrl = requestUrl;
		this.refererUrl = refererUrl;
	}
	
	public int getBrowserId() {
		return browserId;
	}

	public String getBrowserType() {
		return browserType;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public String getRequestUrl() {
		return requestUrl;
	}

	public String getRefererUrl() {
		return refererUrl;
	}
}
