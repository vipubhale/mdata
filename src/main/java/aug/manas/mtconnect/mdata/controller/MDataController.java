package aug.manas.mtconnect.mdata.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import aug.manas.mtconnect.mdata.exception.AgentNotAvailableException;
import aug.manas.mtconnect.mdata.model.MachineData;
import aug.manas.mtconnect.mdata.service.MDataService;

@RestController
@EnableAutoConfiguration
public class MDataController {

	@Autowired
	MDataService mdataService;

	@RequestMapping(value = "/data", method = RequestMethod.GET, produces = "application/json")
	private ArrayList<MachineData> home() throws AgentNotAvailableException {
		ArrayList<MachineData> alMachineData = mdataService.callAgent();
		if (alMachineData == null){
			throw new AgentNotAvailableException();
		}

		return alMachineData;
	}
}
