package com.rusnac.clubrestapi;

import com.rusnac.clubrestapi.model.Exercise;
import com.rusnac.clubrestapi.model.Training;
import com.rusnac.clubrestapi.model.Role;
import com.rusnac.clubrestapi.model.User;
import com.rusnac.clubrestapi.service.IExerciseService;
import com.rusnac.clubrestapi.service.IService;
import com.rusnac.clubrestapi.service.ITrainingService;
import com.rusnac.clubrestapi.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableAsync
public class ClubRestApiApplication implements CommandLineRunner {

	@Autowired
	private IUserService<User> userService;

	@Autowired
	private IService<Role> roleService;

	@Autowired
	private ITrainingService<Training> trainingIService;

	@Autowired
	private IExerciseService<Exercise> exerciseIService;

	public static void main(String[] args) {
		SpringApplication.run(ClubRestApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		if(exerciseIService.findAll().isEmpty()){
			exerciseIService.saveOrUpdate(new Exercise(1L, "Skipping", 2.11));
			exerciseIService.saveOrUpdate(new Exercise(2L, "Dumbbell squat", 3.33));
			exerciseIService.saveOrUpdate(new Exercise(3L, "Lunges", 1.78));
			exerciseIService.saveOrUpdate(new Exercise(4L, "Push-ups", 4.21));
			exerciseIService.saveOrUpdate(new Exercise(5L, "Lateral raise", 1.79));
			exerciseIService.saveOrUpdate(new Exercise(6L, "Burpees", 2.34));
			exerciseIService.saveOrUpdate(new Exercise(7L, "Bicep curl", 4.88));
			exerciseIService.saveOrUpdate(new Exercise(8L, "Plank", 1.08));
		}

		if(roleService.findAll().isEmpty()) {
			roleService.saveOrUpdate(new Role(1L, "admin"));
			roleService.saveOrUpdate(new Role(2L, "user"));
		}

		if(userService.findAll().isEmpty()) {
			User user1 = new User();
			user1.setEmail("1@1.1");
			user1.setName("Cristi User");
			user1.setPosition("ST");
			user1.setRole(roleService.findById(2L).get());
			user1.setPassword(new BCryptPasswordEncoder().encode("212121"));
			userService.saveOrUpdate(user1);

			User user2 = new User();
			user2.setEmail("test@admin.com");
			user2.setName("Cristi Admin");
			user2.setPosition("CB");
			user2.setRole(roleService.findById(1L).get());
			user2.setPassword(new BCryptPasswordEncoder().encode("212121"));
			userService.saveOrUpdate(user2);
		}

		if (trainingIService.findAll().isEmpty()) {
			Training training = new Training();
			training.setTrDuration(15);
			training.setTrPosition("CM");
			training.setExId(exerciseIService.findById(1L).get());
			trainingIService.saveOrUpdate(training);
		}
	}

//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurerAdapter() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/**").allowedOrigins("http://localhost:3000");
//			}
//		};
//	}
}
