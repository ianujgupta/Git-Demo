package com.example.training;

import com.example.training.entities.User;
import com.example.training.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class TrainingApplication {

	@Autowired
	private UserRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(TrainingApplication.class, args);
	}

//	@PostConstruct
//	public void initUSers(){
//		List<User> users = Stream.of(
//				new User(1,"aaa" ,"pwd1","aaa@aaa.com"),
//				new User(2,"bbb" ,"pwd2","bbb@bbb.com"))
//				.collect(Collectors.toList());
//		repository.saveAll(users);
//	}
}
