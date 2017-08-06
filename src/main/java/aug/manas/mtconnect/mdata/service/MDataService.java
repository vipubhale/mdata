package aug.manas.mtconnect.mdata.service;

import java.math.BigInteger;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
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
@Scope("singleton")
@Service
public class MDataService {
	
	private BigInteger nextSequence;

	@Autowired
	private ConfigBean config;

	public ArrayList<MachineData> callAgent() throws AgentNotAvailableException {
//		HashMap<String, ArrayList<String>> machineDataMap = new HashMap<String, ArrayList<String>>();

		ArrayList<MachineData> alMachineData = new ArrayList<MachineData>();
		RestTemplate restTemplate = new RestTemplate();
		MTConnectStreamsType mtConnectStream = null;
		try {
			if (nextSequence == null){
				mtConnectStream = restTemplate.getForObject(config.getAgentEndpoint()+"?from{nextsequence}", MTConnectStreamsType.class, nextSequence);
			}
			else {
				mtConnectStream = restTemplate.getForObject(config.getAgentEndpoint(), MTConnectStreamsType.class);
			}
		} catch (RestClientException e) {
			// System.out.println("And Error is " + e.getLocalizedMessage());
			throw new AgentNotAvailableException();
		}
		List<DeviceStreamType> listDeviceStreamType = mtConnectStream.getStreams().getDeviceStream();
		
		nextSequence = mtConnectStream.getHeader().getNextSequence();
		
		for (DeviceStreamType deviceStreamType : listDeviceStreamType) {
			MachineData machine = new MachineData();
			
			if (deviceStreamType != null && deviceStreamType.getName() != null){
				machine.setMachineName(deviceStreamType.getName());
			}
			if (deviceStreamType != null && deviceStreamType.getUuid() != null){
				machine.setMachineId(deviceStreamType.getUuid());
			}
//
//			if (machineDataMap.get("machineName") == null) {
//				machineDataMap.put("machineName", new ArrayList<String>());
//			} else {
//				machineDataMap.get("machineName").add(deviceStreamType.getName());
//			}
//
//			if (machineDataMap.get("machineId") == null) {
//				machineDataMap.put("machineId", new ArrayList<String>());
//			} else {
//				machineDataMap.get("machineId").add(deviceStreamType.getUuid());
//			}

			List<ComponentStreamType> listComponentStream = deviceStreamType.getComponentStream();

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
									machine.setPartCount(partCount);
								}
								else {
									machine.setPartCount("");
								}
//								if (machineDataMap.get("partCount") == null) {
//									machineDataMap.put("partCount", new ArrayList<String>());
//								} else {
//									machineDataMap.get("partCount").add(eventType.getValue().getValue());
//								}

							}
							break;
						}
						case "active_axes": {
							if (eventType.getValue().getValue() != null && eventType.getValue().getValue() != "") {
								String activeAxesInPath = eventType.getValue().getValue();
//								// System.out.println(activeAxesInPath);
								if (activeAxesInPath != null) {
									machine.setActiveAxes(activeAxesInPath);
								}
								else {
									machine.setActiveAxes("");
								}

//								if (machineDataMap.get("activeAxes") == null) {
//									machineDataMap.put("activeAxes", new ArrayList<String>());
//								} else {
//									machineDataMap.get("activeAxes").add(eventType.getValue().getValue());
//								}
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
								// System.out.println(sampleType.getValue().getValue() + " :: " + sampleType.getValue().getName());
								if (sampleType.getValue().getValue() != null ){
									machine.setS1Speed(sampleType.getValue().getValue());
//									if (machineDataMap.get("s1Speed") == null) {
//										machineDataMap.put("s1Speed", new ArrayList<String>());
//									} else {
//										machineDataMap.get("s1Speed").add(sampleType.getValue().getValue());
//									}
								}
								else {
									machine.setS1Speed("");
								}
								break;
							}
							case "S1load": {
								// System.out.println(sampleType.getValue().getValue() + " :: " + sampleType.getValue().getName());
								if (sampleType.getValue().getValue() != null )
								{
									machine.setS1Load(sampleType.getValue().getValue());
//								if (machineDataMap.get("s1Load") == null) {
//									machineDataMap.put("s1Load", new ArrayList<String>());
//								} else {
//									machineDataMap.get("s1Load").add(sampleType.getValue().getValue());
//								}
								}
								else {
									machine.setS1Load("");
								}
								break;
							}
							case "SspeedOvr": {
								// System.out.println(sampleType.getValue().getValue() + " :: " + sampleType.getValue().getName());
								if (sampleType.getValue().getValue() != null )
								{
									machine.setsSpeedOvr(sampleType.getValue().getValue());
//								if (machineDataMap.get("sSpeedOvr") == null) {
//									machineDataMap.put("sSpeedOvr", new ArrayList<String>());
//								} else {
//									machineDataMap.get("sSpeedOvr").add(sampleType.getValue().getValue());
//								}
								}
								else {
									machine.setsSpeedOvr("");

								}
								break;
							}
						}

					}
					ConditionListType conditionListType = componentStream.getCondition();
					if (conditionListType != null ){
						List<JAXBElement<? extends ConditionType>> conditionTypes = conditionListType.getCondition() ;
						for (JAXBElement<? extends ConditionType> conditionType : conditionTypes){
//							// System.out.println(conditionType.getName() + " :: "+ conditionType.getValue() );
							
							// rotary type
							String conditionTypeName = conditionType.getValue().getName();
							// System.out.println(conditionTypeName);
							switch (conditionTypeName) {
								case "S1servo": {
									// System.out.println(conditionTypeName + " :: " + conditionType.getValue().getValue());
									if (conditionTypeName != null && conditionType.getValue() != null && conditionType.getValue().getValue() != null ){
										machine.setS1Servo(conditionType.getValue().getValue());
//										if (machineDataMap.get("s1Servo") == null) {
//											machineDataMap.put("s1Servo", new ArrayList<String>());
//										} else {
//											machineDataMap.get("s1Servo").add(conditionType.getValue().getValue());
//										}
									}
									else {
										machine.setS1Servo("");
									}
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
//						    System.out.println(conditionTypeName);
							switch (conditionTypeName) {
								case "servo": {
//									 System.out.println(conditionTypeName + " :: " + conditionType.getDeclaredType().getSimpleName());
									if (conditionTypeName != null &&  conditionType.getDeclaredType() != null && conditionType.getDeclaredType().getSimpleName() != null ){
										machine.setServo(conditionType.getDeclaredType().getSimpleName());
//										if (machineDataMap.get("servo") == null) {
//											machineDataMap.put("servo", new ArrayList<String>());
//										} else {
//											machineDataMap.get("servo").add(conditionType.getValue().getValue());
//										}
									}
									else {
										machine.setServo("");

									}
									break;
								}
								case "comms": {
									// System.out.println(conditionTypeName + " :: " + conditionType.getValue().getValue());
									if (conditionTypeName != null && conditionType.getDeclaredType() != null && conditionType.getDeclaredType().getSimpleName() != null ){
										machine.setComms(conditionType.getDeclaredType().getSimpleName());
//										if (machineDataMap.get("comms") == null) {
//											machineDataMap.put("comms", new ArrayList<String>());
//										} else {
//											machineDataMap.get("comms").add(conditionType.getValue().getValue());
//										}
									}else {
										machine.setComms("");
									}
									break;
								}
								case "logic": {
//									 System.out.println(conditionTypeName + " :: " + conditionType.getDeclaredType().getSimpleName());
									if (conditionTypeName != null && conditionType.getDeclaredType() != null && conditionType.getDeclaredType().getSimpleName() != null ){
										machine.setLogic(conditionType.getDeclaredType().getSimpleName());
//										if (machineDataMap.get("logic") == null) {
//											machineDataMap.put("logic", new ArrayList<String>());
//										} else {
//											machineDataMap.get("logic").add(conditionType.getValue().getValue());
//										}
									}
									else {
										machine.setLogic("");
									}
									break;
								}
								case "motion": {
									// System.out.println(conditionTypeName + " :: " + conditionType.getValue().getValue());
									if (conditionTypeName != null && conditionType.getDeclaredType() != null && conditionType.getDeclaredType().getSimpleName() != null ){
										machine.setMotion(conditionType.getDeclaredType().getSimpleName());
//										if (machineDataMap.get("motion") == null) {
//											machineDataMap.put("motion", new ArrayList<String>());
//										} else {
//											machineDataMap.get("motion").add(conditionType.getValue().getValue());
//										}
									}else {
										machine.setMotion("");
									}
									break;
								}
								case "system": {
									// System.out.println(conditionTypeName + " :: " + conditionType.getValue().getValue());
									if (conditionTypeName != null && conditionType.getDeclaredType() != null && conditionType.getDeclaredType().getSimpleName() != null ){
										machine.setSystem(conditionType.getDeclaredType().getSimpleName());
//										if (machineDataMap.get("system") == null) {
//											machineDataMap.put("system", new ArrayList<String>());
//										} else {
//											machineDataMap.get("system").add(conditionType.getValue().getValue());
//										}
									}
									else {
										machine.setSystem("");
									}
									break;
								}
							}
							
						}
					}
				}

				}
			}
			alMachineData.add(machine);
		}
		return alMachineData;

	}

}
