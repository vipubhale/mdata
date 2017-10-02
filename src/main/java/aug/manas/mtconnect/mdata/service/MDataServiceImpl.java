package aug.manas.mtconnect.mdata.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aug.manas.mtconnect.mdata.exception.AgentNotAvailableException;
import aug.manas.mtconnect.mdata.model.MachineData;
import aug.manas.mtconnect.mtdata.stubs.MTConnectStreamsType;

/**
 * 
 * @author vipul This is service class
 */

@Service("mdataService")
public class MDataServiceImpl implements MDataService {
	private static final Logger logger = LoggerFactory.getLogger(MDataServiceImpl.class);

	@Autowired
	private AgentInvokeServiceImpl agentInvokeService;

	/**
	 * 
	 * @return ArrayList of MachineData
	 * @throws AgentNotAvailableException
	 */
	public List<MachineData> callAgent() throws AgentNotAvailableException {
		logger.debug("Entering the callAgent method.");
		List<MachineData> alMachineData = null;
		MTConnectStreamsType mtConnectStream = null;
		logger.debug("Calling the issueRestCall method");
		mtConnectStream = agentInvokeService.issueRestCall();
		logger.debug("MtConnect Stream data is :: " + mtConnectStream.getStreams().getDeviceStream().toString());

		alMachineData = agentInvokeService.parsingTheResponse(mtConnectStream);

		logger.debug("Leaving the callAgent method.");
		return alMachineData;
	}
}
