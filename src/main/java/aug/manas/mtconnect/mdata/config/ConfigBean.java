package aug.manas.mtconnect.mdata.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigBean {

    @Value("${agent.endpoint}")
    private String agentEndpoint;

	public String getAgentEndpoint() {
		return agentEndpoint;
	}

	public void setAgentEndpoint(String agentEndpoint) {
		this.agentEndpoint = agentEndpoint;
	}
}