package info.novatec.sso.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SsoClientApplication.class)
@WebAppConfiguration
public class SsoClientApplicationTests {

	@Test
	public void contextLoads() {
	}

}
