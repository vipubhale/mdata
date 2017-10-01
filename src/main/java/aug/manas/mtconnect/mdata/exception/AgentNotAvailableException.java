package aug.manas.mtconnect.mdata.exception;

public class AgentNotAvailableException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4286362214769624215L;
	
	public AgentNotAvailableException(String message) {
		super();
		this.message = message;
	}

	private String message;
	


}
