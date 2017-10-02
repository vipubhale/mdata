package aug.manas.mtconnect.mdata.unit;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import aug.manas.mtconnect.mdata.exception.AgentNotAvailableException;
import aug.manas.mtconnect.mdata.model.MachineData;
import aug.manas.mtconnect.mdata.service.AgentInvokeServiceImpl;
import aug.manas.mtconnect.mdata.service.MDataServiceImpl;
import aug.manas.mtconnect.mtdata.stubs.MTConnectStreamsType;

/**
 * 
 * @author vipul 
 * 
 * This is an Unit Test for service tier of the application.
 * 
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class UTestMDataService {
	
	private JAXBElement<MTConnectStreamsType> mtConnectStreamjaxb;
	private List<MachineData> svcMockResultList;
	private List<MachineData> svcResultList;


	@Mock
	private AgentInvokeServiceImpl agentInvokeService;
	
	@InjectMocks
	private MDataServiceImpl mdataService;
	
	/**
	 *
	 * Setup.
	 */
	@Before
	public void setup()  {
	    MockitoAnnotations.initMocks(this);
	}

	/**
	 * 
	 * This is a unit test for Service.
	 * 
	 * @throws JAXBException
	 * @throws FileNotFoundException
	 * @throws AgentNotAvailableException
	 *  
	 */
	@Test
	public void unitTestServiceCallAgent() throws JAXBException, FileNotFoundException, AgentNotAvailableException {
		
		svcMockResultList = new ArrayList<MachineData>();
		// Creating a dummylist of machinedata to verify against.
		MachineData machineData1 = new MachineData("device_with_fanuc_1", "CNC-2-AXIS-1", 51, "X Z", "0", "0", "100",
				"", "NormalType", "NormalType", "NormalType", "NormalType", "NormalType");
		MachineData machineData2 = new MachineData("device_with_fanuc_2", "CNC-2-AXIS-2", 87, "X Z", "2570", "19", "100",
				"", "NormalType", "NormalType", "NormalType", "NormalType", "NormalType");
		MachineData machineData3 = new MachineData("device_with_fanuc_3", "CNC-2-AXIS-3", 162, "X Z", "2895", "26", "110",
				"", "NormalType", "NormalType", "NormalType", "NormalType", "NormalType");
		MachineData machineData4 = new MachineData("device_with_fanuc_4", "CNC-2-AXIS-4", 150, "X Z", "760", "148", "120",
				"", "NormalType", "NormalType", "NormalType", "NormalType", "NormalType");
		MachineData machineData5 = new MachineData("device_with_fanuc_5", "CNC-2-AXIS-5", 34, "X Z", "1439", "12", "100",
				"", "NormalType", "NormalType", "NormalType", "NormalType", "NormalType");

		svcMockResultList.add(machineData1);
		svcMockResultList.add(machineData2);
		svcMockResultList.add(machineData3);
		svcMockResultList.add(machineData4);
		svcMockResultList.add(machineData5);

		//Reading the xml file and create a dummy response for Agent
		JAXBContext jc = JAXBContext.newInstance(MTConnectStreamsType.class);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		File xml = new File("src/test/resources/MData_unittest.xml");
		InputStream inputStream = new FileInputStream(xml);
		Source source = new StreamSource(inputStream);
		mtConnectStreamjaxb = unmarshaller.unmarshal(source, MTConnectStreamsType.class);
		
		// Use Mockito when clauses to return the AgentResponse for issueRestCall method
		when(this.agentInvokeService.issueRestCall()).thenReturn(mtConnectStreamjaxb.getValue());
		// Use Mockito when clauses to return the ParsedResponse for parsingTheResponse method. Mock it as a real call
		when(this.agentInvokeService.parsingTheResponse(mtConnectStreamjaxb.getValue())).thenCallRealMethod();
	
		//Actually calling the service and capture the response
		svcResultList = this.mdataService.callAgent();
		
		//assert the response size.
		assertEquals(5, svcResultList.size());
		for (int i = 0; i < svcResultList.size(); i++) {
			assertEquals(svcMockResultList.get(i), svcResultList.get(i));
		}
	}

	/**
	 * 
	 * 
	 * @throws JAXBException
	 * @throws FileNotFoundException
	 * @throws AgentNotAvailableException
	 *  
	 * This is a unit test for Service to check for AgentNotAvailableException when agent goes down.
	 */
	@Test(expected = AgentNotAvailableException.class)
	public void unitTestServiceCallAgenForException() throws JAXBException, FileNotFoundException,AgentNotAvailableException {
		// Use Mockito when clauses to return the AgentResponse for issueRestCall method
		when(this.agentInvokeService.issueRestCall()).thenThrow(new AgentNotAvailableException("Agent not available.", 500));

		//Actually calling the service and capture the response
		this.mdataService.callAgent();
	}

}
