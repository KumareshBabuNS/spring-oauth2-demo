package info.novatec.sso.client;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableOAuth2Sso
@RestController
@SpringBootApplication
public class SsoClientApplication {

    private OAuth2RestTemplate restTemplate;

    public static void main( String[] args) {
		SpringApplication.run(SsoClientApplication.class, args);
	}

    @RequestMapping(path = "/")
    public String user( Principal user ) {

        String result = restTemplate.getForObject ( "http://localhost:9092/resource/service", String.class );

        return String.format ( "<p>Success: %s</p><hl><p>Response from resource: '%s'</p>", user, result );
    }

    @Autowired
    public void setRestTemplate ( OAuth2RestTemplate restTemplate ) {
        this.restTemplate = restTemplate;
    }
}
