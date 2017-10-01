package aug.manas.mtconnect.mdata.exception;

public class AgentNotAvailableException extends Exception {
	/**
	 * AgentNotAvailableException will be thrown when MTConnect Agent is not available. 
	 *  
	 */
	private static final long serialVersionUID = 4286362214769624215L;

	private String message;
	private int statusCode;

	public AgentNotAvailableException(String message, int statusCode) {
		super(message);
		this.message = message;
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}



}
