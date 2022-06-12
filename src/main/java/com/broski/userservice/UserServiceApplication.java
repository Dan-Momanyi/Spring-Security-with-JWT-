package com.broski.userservice;

import com.broski.userservice.Domain.AppUser;
import com.broski.userservice.Domain.Role;
import com.broski.userservice.Service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class UserServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner run(UserService userService) {
		return args-> {
			userService.saveRole(new Role(null, "User"));
			userService.saveRole(new Role(null, "Db-operator"));
			userService.saveRole(new Role(null, "Manager"));
			userService.saveRole(new Role(null, "Admin"));
			userService.saveRole(new Role(null, "Super-Admin"));

			userService.saveUser(new AppUser(null, "John Snow", "snow", "1234", new ArrayList<>()));
			userService.saveUser(new AppUser(null, "Will Wickey", "wickey", "1234", new ArrayList<>()));
			userService.saveUser(new AppUser(null, "Master Chief", "deamon", "1234", new ArrayList<>()));
			userService.saveUser(new AppUser(null, "Jack Sparrow", "sparrow", "1234", new ArrayList<>()));
			userService.saveUser(new AppUser(null, "Capitain  Salazar", "salazar", "1234", new ArrayList<>()));

			userService.addRoleToUser("snow", "User");
			userService.addRoleToUser("wickey", "Db-operator");
			userService.addRoleToUser("deamon", "Admin");
			userService.addRoleToUser("sparrow", "Super-Admin");
			userService.addRoleToUser("salazar", "Super-Admin");
		};
	}

}
