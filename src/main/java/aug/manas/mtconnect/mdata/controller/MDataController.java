package aug.manas.mtconnect.mdata.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import aug.manas.mtconnect.mdata.exception.AgentNotAvailableException;
import aug.manas.mtconnect.mdata.model.MachineData;
import aug.manas.mtconnect.mdata.service.MDataService;

/**
 * 
 * @author vipul
 * This controller has two endpoints /data and /
 *   /  redirects to index.html
 *   /data would call the backend agent api via service mdataService
 **/
@RestController
@EnableAutoConfiguration
public class MDataController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MDataService mdataService;
	/**
	 * 
	 * @return A list of machineData in json format
	 * @throws AgentNotAvailableException
	 */
	@RequestMapping(value = "/data", method = RequestMethod.GET, produces = "application/json")
	public List<MachineData> data() throws AgentNotAvailableException {
		logger.debug("Entering the data method corresponding to /data");
		logger.debug("Calling the mdataService's callAgent method");
		List<MachineData> alMachineData = mdataService.callAgent();
		logger.debug("Ouput of callAgent method is :: ", alMachineData);
		logger.debug("Leaving the data method corresponding to /data");
		return alMachineData;
	}
	
	/**
	 * 
	 * @return redirects to index.html
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView getIndexPage(){
		return new ModelAndView("redirect:/index.html");
	}
}
