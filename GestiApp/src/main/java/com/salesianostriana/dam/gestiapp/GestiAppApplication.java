package com.salesianostriana.dam.gestiapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.salesianostriana.dam.gestiapp.model.AppUser;
import com.salesianostriana.dam.gestiapp.service.AppUserService;
@SpringBootApplication
public class GestiAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestiAppApplication.class, args);

		
	}
	
	@Bean
    public CommandLineRunner init(AppUserService servicio, BCryptPasswordEncoder passwordEncoder) {
        return args -> {

        	AppUser adm = new AppUser();
    		
    		adm.setName("Manolo");
    		adm.setSurname("Rodríguez");
    		adm.setUserEmail("123@123.com");
    		adm.setPassword("123");
    		adm.setValidated(true);
    		adm.setAdmin(true);

            servicio.save(adm);

            AppUser user = new AppUser();
    		
    		user.setName("Pepa");
    		user.setSurname("García");
    		user.setUserEmail("321@321.com");
    		user.setPassword("321");
    		user.setValidated(false);
    		user.setAdmin(false);
    		
    		AppUser user2 = new AppUser();
    		
    		user2.setName("Pepa");
    		user2.setSurname("García");
    		user2.setUserEmail("44@44.com");
    		user2.setPassword("444");
    		user2.setValidated(false);
    		user2.setAdmin(false);

            servicio.save(user);
            
            servicio.save(user2);
            
            
            for (AppUser usuario : servicio.findAll()) {
                usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
                servicio.save(usuario);
            }

        };
	
	}
};
