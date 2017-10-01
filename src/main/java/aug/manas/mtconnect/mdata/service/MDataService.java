package aug.manas.mtconnect.mdata.service;

import java.util.ArrayList;

import org.springframework.web.client.RestClientException;

import aug.manas.mtconnect.mdata.exception.AgentNotAvailableException;
import aug.manas.mtconnect.mdata.model.MachineData;
import aug.manas.mtconnect.mtdata.stubs.MTConnectStreamsType;

public interface MDataService {
	
	public ArrayList<MachineData> callAgent() throws AgentNotAvailableException;
}
