package aug.manas.mtconnect.mdata.service;

import java.util.List;

import aug.manas.mtconnect.mdata.exception.AgentNotAvailableException;
import aug.manas.mtconnect.mdata.model.MachineData;

public interface MDataService {
	
	public List<MachineData> callAgent() throws AgentNotAvailableException;
}
