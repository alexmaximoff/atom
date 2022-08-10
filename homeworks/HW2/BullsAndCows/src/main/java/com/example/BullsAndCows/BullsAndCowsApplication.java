package com.example.BullsAndCows;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.Console;

@SpringBootApplication
@Slf4j
public class BullsAndCowsApplication implements CommandLineRunner {

	public static void main(String[] args) {
		log.info("STARTING THE APPLICATION");
		SpringApplication.run(BullsAndCowsApplication.class, args);
		log.info("APPLICATION FINISHED");
	}

	@Override
	public void run(String... args) {
		Console console = System.console();
		String user_wanna_play = "y";
		console.printf("Welcome to Bulls and Cows game!\n");
		while(!user_wanna_play.equalsIgnoreCase("n")){
			String guess = console.readLine("Your guess: ");
			console.printf("You printed %s\n", guess);
			user_wanna_play = console.readLine("Wanna play again? Y/N\n");
			console.printf("You printed %s\n", user_wanna_play);
		}
		console.printf("Good Bye! See you again!\n");
	}
}
