package aug.manas.mtconnect.mdata.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import aug.manas.mtconnect.mdata.MTDataApp;
import aug.manas.mtconnect.mdata.exception.AgentNotAvailableException;
import aug.manas.mtconnect.mdata.model.MachineData;
import aug.manas.mtconnect.mdata.service.MDataService;

@RestController
@EnableAutoConfiguration
public class MDataController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MDataService mdataService;

	@RequestMapping(value = "/data", method = RequestMethod.GET, produces = "application/json")
	private ArrayList<MachineData> data() throws AgentNotAvailableException {
		logger.debug("Entering the data method corresponding to /data");
		logger.debug("Calling the mdataService's callAgent method");
		ArrayList<MachineData> alMachineData = mdataService.callAgent();
		logger.debug("Ouput of callAgent method is :: ", alMachineData);

		if (alMachineData == null){
			logger.debug("Empty machine data list throwing exception :: ");
			throw new AgentNotAvailableException("no data");
		}
		logger.debug("Leaving the data method corresponding to /data");
		return alMachineData;
	}
}
