package aug.manas.mtconnect.mdata.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
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

@Component("agentInvokeService")
public class AgentInvokeServiceImpl {
	private static final Logger logger = LoggerFactory.getLogger(AgentInvokeServiceImpl.class);

	
	private BigInteger nextSequence;

	@Autowired
	private ConfigBean config;
	
    private final RestTemplate restTemplate;

    @Autowired
    public AgentInvokeServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }
    
	/**
	 * Calls the backend Agent
	 * @return MTConnectStreamsType
	 * @throws RestClientException
	 * @throws AgentNotAvailableException 
	 */
	public MTConnectStreamsType issueRestCall() throws AgentNotAvailableException{
		logger.debug("Entering the issueRestCall method.");
		logger.debug("Creating the RestTemplate instance.");
		
		MTConnectStreamsType mtConnectStream = null;
		ResponseEntity<MTConnectStreamsType> mtConnectStreamEntity = null;

		try {
			if (nextSequence != null) {
				logger.debug("NextSequence is  not null calling the Agent.");
				mtConnectStreamEntity = restTemplate.getForEntity(config.getAgentEndpoint() + "?from{nextsequence}",
						MTConnectStreamsType.class, nextSequence);
				mtConnectStream = mtConnectStreamEntity.getBody();

			} else {
				logger.debug("NextSequence is   null calling the Agent.");
				mtConnectStreamEntity = restTemplate.getForEntity(config.getAgentEndpoint() + "?from",
						MTConnectStreamsType.class);
				mtConnectStream = mtConnectStreamEntity.getBody();
			}
		} catch (RestClientException e) {
			logger.error("Error while calling the agent");
			
			if (mtConnectStreamEntity != null && mtConnectStreamEntity.getStatusCodeValue() != 200) {
				throw new AgentNotAvailableException("Agent Not available", mtConnectStreamEntity.getStatusCodeValue());
			}
			else {
				throw new AgentNotAvailableException("Agent Not available", 500);
			}
		}

			logger.debug("Leaving the issueRestCall method.");
		return mtConnectStream;
	}

	/**
	 * This method parses the response from MTConnect Agent and returns the parsedData
	 * 
	 * @param mtConnectStream type data got from Agent
	 * @return ArrayList of MachineData. 
	 */
	public ArrayList<MachineData> parsingTheResponse(MTConnectStreamsType mtConnectStream){
		logger.debug("Entering the issueRestCall method.");

		ArrayList<MachineData> alMachineData = new ArrayList<>();

		List<DeviceStreamType> listDeviceStreamType = mtConnectStream.getStreams().getDeviceStream();
		logger.debug("List of DeviceStreamType is :: ",listDeviceStreamType);
		logger.debug("Size of DeviceStreamType is :: ",listDeviceStreamType.size());

		nextSequence = mtConnectStream.getHeader().getNextSequence();
		logger.debug("nextSequence is :: ",nextSequence);
		
		for (DeviceStreamType deviceStreamType : listDeviceStreamType) {
			MachineData machine = new MachineData();
			
			if (deviceStreamType != null && deviceStreamType.getName() != null){
				machine.setMachineName(deviceStreamType.getName());
			}else {
				machine.setMachineName("");
			}
			logger.debug("MachineName is :: "+ machine.getMachineName());
			
			if (deviceStreamType != null && deviceStreamType.getUuid() != null){
				machine.setMachineId(deviceStreamType.getUuid());
			}else {
				machine.setMachineId("");

			}
			logger.debug("MachineId is :: "+machine.getMachineId());

			List<ComponentStreamType> listComponentStream = deviceStreamType.getComponentStream();
			logger.debug("ListComponentStream is :: ", (listComponentStream == null) ? null: listComponentStream);
			
			for (ComponentStreamType componentStream : listComponentStream) {
				switch (componentStream.getComponent()) {
				// Path Element
				case "Path": {
					List<JAXBElement<? extends EventType>> events = componentStream.getEvents().getEvent();
					for (JAXBElement<? extends EventType> eventType : events) {
						// part_count
						String elementNameInPath = eventType.getValue().getName();
						switch (elementNameInPath) {
						// PartCount Element
						case "part_count": {
							if (eventType.getValue().getValue() != null && eventType.getValue().getValue() != "") {
								String partCount = eventType.getValue().getValue();
								if (partCount != null){
									machine.setPartCount(Integer.parseInt(partCount));

								}
								else {
									machine.setPartCount(0);
								}
								logger.debug("PartCount is :: "+machine.getPartCount());
							}
							break;
						}
						case "active_axes": {
							if (eventType.getValue().getValue() != null && eventType.getValue().getValue() != "") {
								String activeAxesInPath = eventType.getValue().getValue();
								if (activeAxesInPath != null) {
									machine.setActiveAxes(activeAxesInPath);

								}
								else {
									machine.setActiveAxes("");
								}
								logger.debug("ActiveAxes is :: "+machine.getActiveAxes());
							}
							break;
						}

						}
					}
				}

				case "Rotary": {
					List<JAXBElement<? extends SampleType>> sampleTypes = componentStream.getSamples().getSample();
					for (JAXBElement<? extends SampleType> sampleType : sampleTypes) {
						// rotary type
						String rotaryType = sampleType.getValue().getName();
						switch (rotaryType) {
							case "S1speed": {
								if (sampleType.getValue().getValue() != null ){
									machine.setS1Speed(sampleType.getValue().getValue());
								}
								else {
									machine.setS1Speed("");
								}
								logger.debug("S1Speed is :: "+machine.getS1Speed());
								break;
							}
							case "S1load": {
								if (sampleType.getValue().getValue() != null )
								{
									machine.setS1Load(sampleType.getValue().getValue());
								}
								else {
									machine.setS1Load("");
								}
								logger.debug("S1load is :: "+machine.getS1Load());
								break;
							}
							case "SspeedOvr": {
								if (sampleType.getValue().getValue() != null )
								{
									machine.setsSpeedOvr(sampleType.getValue().getValue());
								}
								else {
									machine.setsSpeedOvr("");

								}
								logger.debug("SspeedOvr is :: "+machine.getsSpeedOvr());
								break;
							}
						}

					}
					ConditionListType conditionListType = componentStream.getCondition();
					if (conditionListType != null ){
						List<JAXBElement<? extends ConditionType>> conditionTypes = conditionListType.getCondition() ;
						for (JAXBElement<? extends ConditionType> conditionType : conditionTypes){							
							// rotary type
							String conditionTypeName = conditionType.getValue().getName();
							// System.out.println(conditionTypeName);
							switch (conditionTypeName) {
								case "S1servo": {
									if (conditionTypeName != null && conditionType.getValue() != null && conditionType.getValue().getValue() != null ){
										machine.setS1Servo(conditionType.getValue().getValue());
									}
									else {
										machine.setS1Servo("");
									}
									logger.debug("S1load is :: "+machine.getS1Servo());
									break;
								}
							}
							
						}
					}
				}
				// Controller Element
				case "Controller": {
					ConditionListType conditionListType = componentStream.getCondition();
					if (conditionListType != null ){
						List<JAXBElement<? extends ConditionType>> conditionTypes = conditionListType.getCondition() ;
						for (JAXBElement<? extends ConditionType> conditionType : conditionTypes){
							String conditionTypeName = conditionType.getValue().getName();
							switch (conditionTypeName) {
								case "servo": {
									if (conditionTypeName != null &&  conditionType.getDeclaredType() != null && conditionType.getDeclaredType().getSimpleName() != null ){
										machine.setServo(conditionType.getDeclaredType().getSimpleName());
									}
									else {
										machine.setServo("");

									}
									logger.debug("servo is :: "+machine.getServo());
									break;
								}
								case "comms": {
									if (conditionTypeName != null && conditionType.getDeclaredType() != null && conditionType.getDeclaredType().getSimpleName() != null ){
										machine.setComms(conditionType.getDeclaredType().getSimpleName());
									}else {
										machine.setComms("");
									}
									logger.debug("comms is :: "+machine.getComms());
									break;
								}
								case "logic": {
									if (conditionTypeName != null && conditionType.getDeclaredType() != null && conditionType.getDeclaredType().getSimpleName() != null ){
										machine.setLogic(conditionType.getDeclaredType().getSimpleName());
									}
									else {
										machine.setLogic("");
									}
									logger.debug("logic is :: "+machine.getLogic());
									break;
								}
								case "motion": {
									if (conditionTypeName != null && conditionType.getDeclaredType() != null && conditionType.getDeclaredType().getSimpleName() != null ){
										machine.setMotion(conditionType.getDeclaredType().getSimpleName());
									}else {
										machine.setMotion("");
									}
									logger.debug("motion is :: "+machine.getMotion());
									break;
								}
								case "system": {
									if (conditionTypeName != null && conditionType.getDeclaredType() != null && conditionType.getDeclaredType().getSimpleName() != null ){
										machine.setSystem(conditionType.getDeclaredType().getSimpleName());
									}
									else {
										machine.setSystem("");
									}
									logger.debug("system is :: "+machine.getSystem());
									break;
								}
							}
							
						}
					}
				}

				}
			}
			logger.debug("MachineData for current machine "+ (alMachineData.size()+1) +" is ::"+machine);
			alMachineData.add(machine);
		}
		logger.debug("List of MachineData is ::"+alMachineData);	
		logger.debug("Leaving the issueRestCall method.");
		return alMachineData;		
	}
}