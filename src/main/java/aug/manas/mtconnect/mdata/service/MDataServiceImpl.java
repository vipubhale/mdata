package aug.manas.mtconnect.mdata.service;

import java.math.BigInteger;


import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import aug.manas.mtconnect.mdata.config.ConfigBean;
import aug.manas.mtconnect.mdata.exception.AgentNotAvailableException;
import aug.manas.mtconnect.mdata.model.MachineData;
import aug.manas.mtconnect.mtdata.stubs.ComponentStreamType;
import aug.manas.mtconnect.mtdata.stubs.ConditionListType;
import aug.manas.mtconnect.mtdata.stubs.ConditionType;
import aug.manas.mtconnect.mtdata.stubs.DeviceStreamType;
import aug.manas.mtconnect.mtdata.stubs.EventType;
import aug.manas.mtconnect.mtdata.stubs.MTConnectStreamsType;
import aug.manas.mtconnect.mtdata.stubs.SampleType;

/**
 * 
 * @author vipul
 * This is service class
 */

@Service("mdataService")
public class MDataServiceImpl implements MDataService {
	private static final Logger logger = LoggerFactory.getLogger(MDataServiceImpl.class);

	@Autowired
	private AgentInvokeServiceImpl agentInvokeService;

	/**
	 * 
	 * @return
	 * @throws AgentNotAvailableException
	 */
	public ArrayList<MachineData> callAgent() throws AgentNotAvailableException {
		logger.debug("Entering the callAgent method.");
		ArrayList<MachineData> alMachineData = null;
		MTConnectStreamsType mtConnectStream = null;
		try {
			logger.debug("Calling the issueRestCall method");
			mtConnectStream = agentInvokeService.issueRestCall();
			logger.debug("MtConnect Stream data is :: "+mtConnectStream.getStreams().getDeviceStream().toString());

			alMachineData = agentInvokeService.parsingTheResponse(mtConnectStream);
		} catch (RestClientException e) {
			logger.error("Error calling the backend Agent", e);
			throw new AgentNotAvailableException(e.getMessage());
		}
		logger.debug("Leaving the callAgent method.");
		return alMachineData;
	}
}
