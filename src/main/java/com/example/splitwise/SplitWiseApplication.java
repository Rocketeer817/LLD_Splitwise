package com.example.splitwise;

import com.example.splitwise.commands.Command;
import com.example.splitwise.commands.CommandExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.beans.Beans;

import java.util.Scanner;

@EnableJpaAuditing
@SpringBootApplication
public class SplitWiseApplication implements org.springframework.boot.CommandLineRunner {

	private final CommandExecutor commandExecutor;

	private final Scanner scanner = new Scanner(System.in);

	@Autowired
	public SplitWiseApplication(CommandExecutor commandExecutor){
		this.commandExecutor = commandExecutor;
	}

	@Override
	public void run(String... args) throws Exception
	{
		while(true){
			String input = scanner.next();
			commandExecutor.execute(input);
		}
	}



	public static void main(String[] args) {
		SpringApplication.run(SplitWiseApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
