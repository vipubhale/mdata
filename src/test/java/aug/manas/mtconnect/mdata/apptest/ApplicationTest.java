package aug.manas.mtconnect.mdata.apptest;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import aug.manas.mtconnect.mdata.controller.MDataController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

    @Autowired
    private MDataController controller;

	
    @Test
    public void contextLoads() throws Exception {
    }


    @Test
    public void contexLoads() throws Exception {
        assertThat(controller).isNotNull();
    }
    
}