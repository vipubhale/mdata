package aug.manas.mtconnect.mdata.service;

import java.util.ArrayList;

import aug.manas.mtconnect.mdata.exception.AgentNotAvailableException;
import aug.manas.mtconnect.mdata.model.MachineData;

public interface MDataService {
	
	public ArrayList<MachineData> callAgent() throws AgentNotAvailableException;
}
