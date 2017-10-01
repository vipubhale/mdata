package aug.manas.mtconnect.mdata.itest;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.iterableWithSize;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.jayway.jsonpath.JsonPath;
/**
 * 
 * @author vipul
 * This is an Integration Test Suite for mdata dashboard application.
 */
@ActiveProfiles("dev")
@RunWith(SpringRunner.class)
@WebMvcTest
public class ITestMDataController {

	@Autowired
	private MockMvc mockMvc;

	private MvcResult mvcResult;
	private String contentDataControllerOutput = "";

	@Before
	public void setup() throws Exception {
		mvcResult = this.mockMvc.perform(get("/data")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$", iterableWithSize(5))).andReturn();

		contentDataControllerOutput = mvcResult.getResponse().getContentAsString();
	}

	/**
	 * Test the MDataController's response when SOAP UI is running. This test
	 * makes sure response data of type JSON
	 * 
	 * @throws Exception
	 */
	@Test
	public void shouldReturnJsonContent() throws Exception {
		this.mockMvc.perform(get("/data")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"));

	}

	/**
	 * Test the MDataController's response when SOAP UI is running. This test
	 * makes sure response data of type JSON and having multiple machine data
	 * 
	 * @throws Exception
	 */
	@Test
	public void shouldReturnMultipleMachinesData() throws Exception {
		this.mockMvc.perform(get("/data")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$", iterableWithSize(5)));

	}

	/**
	 * Test the MDataController's response when SOAP UI is running. This test
	 * makes sure response data of type JSON and having multiple machine data
	 * and check for values of machineId
	 * 
	 * @throws Exception
	 */
	@Test
	public void shouldReturnDataMachineId() throws Exception {

		String[] machineIds = { "device_with_fanuc_1", "device_with_fanuc_2", "device_with_fanuc_3",
				"device_with_fanuc_4", "device_with_fanuc_5" };
		assertNotNull(contentDataControllerOutput);

		for (int i = 0; i < machineIds.length; i++) {
			String machineId = JsonPath.read(contentDataControllerOutput, "$[" + i + "].machineId");
			assertNotNull(machineId);
			assertThat(Arrays.asList(machineIds), hasItem(machineId));
		}
	}

	/**
	 * Test the MDataController's response when SOAP UI is running. This test
	 * makes sure response data of type JSON and having multiple machine data
	 * and check for values of machineName
	 * 
	 * @throws Exception
	 */

	@Test
	public void shouldReturnDataMachineName() throws Exception {
		String[] machineNames = { "CNC-2-AXIS-1", "CNC-2-AXIS-2", "CNC-2-AXIS-3", "CNC-2-AXIS-4", "CNC-2-AXIS-5" };
 
		assertNotNull(contentDataControllerOutput);

		for (int i = 0; i < machineNames.length; i++) {
			String machineName = JsonPath.read(contentDataControllerOutput, "$[" + i + "].machineName");
			assertNotNull(machineName);
			assertThat(Arrays.asList(machineNames), hasItem(machineName));
		}
	}

	/**
	 * Test the MDataController's response when SOAP UI is running. This test
	 * makes sure response data of type JSON and having multiple machine data
	 * and check for values of partCount
	 * 
	 * @throws Exception
	 */

	@Test
	public void shouldReturnDataPartCount() throws Exception {
		Integer[] partCounts = { 51, 87, 162, 150, 34 };

		assertNotNull(contentDataControllerOutput);

		for (int i = 0; i < partCounts.length; i++) {
			int partCount = JsonPath.read(contentDataControllerOutput, "$[" + i + "].partCount");
			assertNotNull(partCount);
			assertThat(Arrays.asList(partCounts), hasItem(partCount));
		}

	}

	/**
	 * Test the MDataController's response when SOAP UI is running. This test
	 * makes sure response data of type JSON and having multiple machine data
	 * and check for values of machineId
	 * 
	 * @throws Exception
	 */

	@Test
	public void shouldReturnDataActiveAxes() throws Exception {
		String[] activeAxes = { "X Z", "X Z", "X Z", "X Z", "X Z" };

		assertNotNull(contentDataControllerOutput);

		for (int i = 0; i < activeAxes.length; i++) {
			String activeAxex = JsonPath.read(contentDataControllerOutput, "$[" + i + "].activeAxes");
			assertNotNull(activeAxex);
			assertThat(Arrays.asList(activeAxes), hasItem(activeAxex));
		}

	}

	/**
	 * Test the MDataController's response when SOAP UI is running. This test
	 * makes sure response data of type JSON and having multiple machine data
	 * and check for values of machineId
	 * 
	 * @throws Exception
	 */

	@Test
	public void shouldReturnDataS1Speed() throws Exception {
		Integer[] s1Speeds = { 0, 2570, 2895, 760, 1439 };

		assertNotNull(contentDataControllerOutput);

		for (int i = 0; i < s1Speeds.length; i++) {
			String s1Speed = JsonPath.read(contentDataControllerOutput, "$[" + i + "].s1Speed");
			assertNotNull(s1Speed);
			assertThat(Arrays.asList(s1Speeds), hasItem(Integer.parseInt(s1Speed)));
		}

	}

	/**
	 * Test the MDataController's response when SOAP UI is running. This test
	 * makes sure response data of type JSON and having multiple machine data
	 * and check for values of machineId
	 * 
	 * @throws Exception
	 */

	@Test
	public void shouldReturnDataS1Load() throws Exception {
		Integer[] s1Loads = { 0, 19, 26, 148, 12 };
		
		assertNotNull(contentDataControllerOutput);

		for (int i = 0; i < s1Loads.length; i++) {
			String s1Load = JsonPath.read(contentDataControllerOutput, "$[" + i + "].s1Load");
			assertNotNull(s1Load);
			assertThat(Arrays.asList(s1Loads), hasItem(Integer.parseInt(s1Load)));
		}

	}

	/**
	 * Test the MDataController's response when SOAP UI is running. This test
	 * makes sure response data of type JSON and having multiple machine data
	 * and check for values of machineId
	 * 
	 * @throws Exception
	 */

	@Test
	public void shouldReturnDataSSpeedOvr() throws Exception {
		Integer[] sSpeedOvrs = { 100, 100, 110, 120, 100 };

		assertNotNull(contentDataControllerOutput);

		for (int i = 0; i < sSpeedOvrs.length; i++) {
			String sSpeedOvr = JsonPath.read(contentDataControllerOutput, "$[" + i + "].sSpeedOvr");
			assertNotNull(sSpeedOvr);
			assertThat(Arrays.asList(sSpeedOvrs), hasItem(Integer.parseInt(sSpeedOvr)));
		}

	}

	/**
	 * Test the MDataController's response when SOAP UI is running. This test
	 * makes sure response data of type JSON and having multiple machine data
	 * and check for values of machineId
	 * 
	 * @throws Exception
	 */

	@Test
	public void shouldReturnDataServo() throws Exception {
		String[] servos = { "NormalType", "NormalType", "NormalType", "NormalType", "NormalType" };

		assertNotNull(contentDataControllerOutput);

		for (int i = 0; i < servos.length; i++) {
			String servo = JsonPath.read(contentDataControllerOutput, "$[" + i + "].servo");
			assertNotNull(servo);
			assertThat(Arrays.asList(servos), hasItem(servo));
		}

	}

	/**
	 * Test the MDataController's response when SOAP UI is running. This test
	 * makes sure response data of type JSON and having multiple machine data
	 * and check for values of machineId
	 * 
	 * @throws Exception
	 */

	@Test
	public void shouldReturnDataComms() throws Exception {
		String[] comms = { "NormalType", "NormalType", "NormalType", "NormalType", "NormalType" };

		assertNotNull(contentDataControllerOutput);

		for (int i = 0; i < comms.length; i++) {
			String comm = JsonPath.read(contentDataControllerOutput, "$[" + i + "].comms");
			assertNotNull(comm);
			assertThat(Arrays.asList(comms), hasItem(comm));
		}

	}

	/**
	 * Test the MDataController's response when SOAP UI is running. This test
	 * makes sure response data of type JSON and having multiple machine data
	 * and check for values of machineId
	 * 
	 * @throws Exception
	 */

	@Test
	public void shouldReturnDataLogic() throws Exception {
		String[] logics = { "NormalType", "NormalType", "NormalType", "NormalType", "NormalType" };

		assertNotNull(contentDataControllerOutput);

		for (int i = 0; i < logics.length; i++) {
			String logic = JsonPath.read(contentDataControllerOutput, "$[" + i + "].logic");
			assertNotNull(logic);
			assertThat(Arrays.asList(logics), hasItem(logic));
		}

	}

	/**
	 * Test the MDataController's response when SOAP UI is running. This test
	 * makes sure response data of type JSON and having multiple machine data
	 * and check for values of machineId
	 * 
	 * @throws Exception
	 */

	@Test
	public void shouldReturnDataMotion() throws Exception {
		String[] motions = { "NormalType", "NormalType", "NormalType", "NormalType", "NormalType" };

		assertNotNull(contentDataControllerOutput);

		for (int i = 0; i < motions.length; i++) {
			String motion = JsonPath.read(contentDataControllerOutput, "$[" + i + "].motion");
			assertNotNull(motion);
			assertThat(Arrays.asList(motions), hasItem(motion));
		}

	}

	/**
	 * Test the MDataController's response when SOAP UI is running. This test
	 * makes sure response data of type JSON and having multiple machine data
	 * and check for values of machineId
	 * 
	 * @throws Exception
	 */

	@Test
	public void shouldReturnDataSystem() throws Exception {
		String[] systems = { "NormalType", "NormalType", "NormalType", "NormalType", "NormalType" };

		for (int i = 0; i < systems.length; i++) {
			String system = JsonPath.read(contentDataControllerOutput, "$[" + i + "].system");
			assertNotNull(system);
			assertThat(Arrays.asList(systems), hasItem(system));
		}

	}
}
