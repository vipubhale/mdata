package aug.manas.mtconnect.mdata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "aug.manas.mtconnect.mdata", "aug.manas.mtconnect.mdata.exception"  })

public class MTDataApp {
    private static final Logger logger = LoggerFactory.getLogger(MTDataApp.class);

	public static void main(String[] args) {
		logger.debug("Starting the application");
		SpringApplication.run(MTDataApp.class, args);
	}
}
