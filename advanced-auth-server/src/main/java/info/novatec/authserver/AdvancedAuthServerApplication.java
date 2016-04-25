package info.novatec.authserver;

import java.security.Principal;

import org.apache.catalina.security.SecurityUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Controller
@EnableAuthorizationServer
@SpringBootApplication
public class AdvancedAuthServerApplication extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(AdvancedAuthServerApplication.class, args);
	}

    @Override
    public void addViewControllers ( ViewControllerRegistry registry ) {
        registry.addViewController ( "/login" ).setViewName ( "login" );
    }

    @RequestMapping(path = "/user")
    @ResponseBody
    public Principal user ( Principal user ) {
        return user;
    }

    @Configuration
    @Order( 20 )
    public static class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure ( HttpSecurity http ) throws Exception {
            http.csrf ().disable ();
            http.formLogin ().loginPage ( "/login" ).permitAll ();
            http.authorizeRequests ().anyRequest ().authenticated ();
        }

        @Override
        protected void configure ( AuthenticationManagerBuilder auth ) throws Exception {
            auth.inMemoryAuthentication ()
                    .withUser ( "user@example.com" ).password ( "secret" ).roles ( "USER" )
                    .and ()
                    .withUser ( "admin@example.com" ).password ( "admin" ).roles ( "ADMIN", "USER" );
        }

    }

    @EnableResourceServer
    public static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

        @Override
        public void configure ( HttpSecurity http ) throws Exception {
            http.requestMatchers ().antMatchers ( "/user" ).and ().authorizeRequests ().anyRequest ().authenticated ();
        }
    }
}
