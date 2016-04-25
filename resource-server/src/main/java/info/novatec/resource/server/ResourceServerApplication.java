package info.novatec.resource.server;

import java.security.Principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableResourceServer
@SpringBootApplication
public class ResourceServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResourceServerApplication.class, args);
	}

	@RequestMapping(path = "/service")
	public String myService( Principal user) {
		return String.format ( "Resource server says hello to user '%s'", user.getName () );
	}
}
