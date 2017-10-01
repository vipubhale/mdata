package aug.manas.mtconnect.mdata.model;

public class ErrorResponseMsg {
	private String message;
	private int code;

	public ErrorResponseMsg(String msg, int code) {
		this.message = msg;
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}