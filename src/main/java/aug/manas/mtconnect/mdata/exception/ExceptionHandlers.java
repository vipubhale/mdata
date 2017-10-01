package aug.manas.mtconnect.mdata.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import aug.manas.mtconnect.mdata.model.ErrorResponseMsg;

@ControllerAdvice
public class ExceptionHandlers {
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(value = AgentNotAvailableException.class)
	@ResponseBody
	public ErrorResponseMsg handleConflict(AgentNotAvailableException ex) {
		ErrorResponseMsg responseMsg = new ErrorResponseMsg(ex.getMessage(), ex.getStatusCode());
		return responseMsg;
	}
}