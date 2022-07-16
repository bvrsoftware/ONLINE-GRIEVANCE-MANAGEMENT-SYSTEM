package com.bvrsoftware;

import java.util.List;



import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.bvrsoftware.entites.Role;
import com.bvrsoftware.payloads.Constants_Value;
import com.bvrsoftware.repository.RoleRepository;

@SpringBootApplication
//@CrossOrigin(origins = {"http://localhost:4200"})
public class OnlineGrievanceManagementSystemApplication implements CommandLineRunner {

	@Autowired
	private RoleRepository roleRepo;
	

	
	public static void main(String[] args)  {
		SpringApplication.run(OnlineGrievanceManagementSystemApplication.class, args);
	}

    //Mapping between classes using ModelMapper class 
	
        @Bean
        ModelMapper modelMapper() {
        return new ModelMapper();
    }
        @Bean
       WebMvcConfigurer corsConfigurer() {
     		return new WebMvcConfigurer() {
     			@Override
     			public void addCorsMappings(CorsRegistry registry) {
     				registry.addMapping("/**").allowedOrigins("*").allowedHeaders("*")
     				.allowCredentials(false).allowedMethods("*").exposedHeaders("Authorization")
     				.maxAge(1728000).allowedOriginPatterns("*");
     			}
     		};
     	}
		@Override
		public void run(String... args) throws Exception {
		
			try {
				Role role1=new Role();
				role1.setId(Constants_Value.ADMIN_ROLE_ID);
				role1.setRoleName(Constants_Value.ADMIN_USER);
				Role role2=new Role();
				role2.setId(Constants_Value.NORMAL_USER_ROLE_ID);
				role2.setRoleName(Constants_Value.NORMAL_USER);
				
				List<Role> roles=List.of(role1,role2);
				
				List<Role> result=this.roleRepo.saveAll(roles);
				result.forEach(r->{
					System.out.println(r.getRoleName());
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}
}
