package aug.manas.mtconnect.mdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "aug.manas.mtconnect.mdata", "aug.manas.mtconnect.mdata.exception"  })

public class MTDataApp {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		SpringApplication.run(MTDataApp.class, args);
	}
}
