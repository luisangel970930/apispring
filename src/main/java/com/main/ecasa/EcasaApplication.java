package com.main.ecasa;

import com.main.ecasa.model.Appuser;
import com.main.ecasa.model.Role;
import com.main.ecasa.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;

@SpringBootApplication
@Import(SwaggerConfiguration.class)
public class EcasaApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(EcasaApplication.class, args);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

@Bean
	 CommandLineRunner run  (UserService userService){
		return args->{
			userService.saveRole(new Role(null,"ROLE_USER"));
			userService.saveRole(new Role(null,"ROLE_MANAGER"));
			userService.saveRole(new Role(null,"ROLE_ADMIN"));
			userService.saveRole(new Role(null,"ROLE_SUPER_ADMIN"));

			userService.saveUser(new Appuser(null,"John Travolta","john","123", new ArrayList<>()));
			userService.saveUser(new Appuser(null,"Will Smith","will","123", new ArrayList<>()));
			userService.saveUser(new Appuser(null,"Jim Carry","jim","123", new ArrayList<>()));
			userService.saveUser(new Appuser(null,"Arnold Travolta","arnold","123", new ArrayList<>()));
			userService.saveUser(new Appuser(null,"Andy Travolta","andy","123", new ArrayList<>()));

			userService.addRoleToUser("john","ROLE_USER");
			userService.addRoleToUser("john","ROLE_MANAGER");
			userService.addRoleToUser("will","ROLE_MANAGER");
			userService.addRoleToUser("jim","ROLE_ADMIN");
			userService.addRoleToUser("arnold","ROLE_SUPER_ADMIN");
			userService.addRoleToUser("arnold","ROLE_ADMIN");
			userService.addRoleToUser("andy","ROLE_USER");

		};
	 }

}
