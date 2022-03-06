package com.santu.predict;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SuppressWarnings("deprecation")
@SpringBootApplication
public class PredictWebApplication //implements CommandLineRunner
{

//	@Autowired
//	private UserRepository userRepository;
//	
//	@Autowired
//	private SequenceGeneratorService sequenceGeneratorService;
	
	public static void main(String[] args) {
		SpringApplication.run(PredictWebApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
//	@Override
//	public void run(String... args) throws Exception {
//		
//		User u1 = new User().builder().id(sequenceGeneratorService.generateSequence("user_sequence")).firstName("santu1").lastName("patra1").email("santupatra1@gmail.com").password("password1234").build();
//		User u2 = new User().builder().id(sequenceGeneratorService.generateSequence("user_sequence")).firstName("santu2").lastName("patra2").email("santupatra2@gmail.com").password("password1234").build();
//		
//		
//		userRepository.save(u1);
//		userRepository.save(u2);
//		
//		List<User> users = userRepository.findAll();
//		users.forEach(System.out :: println);
//		
//	}

}
