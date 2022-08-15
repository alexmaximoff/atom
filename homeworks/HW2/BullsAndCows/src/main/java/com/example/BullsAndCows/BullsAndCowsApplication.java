package com.example.BullsAndCows;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
		List<String> words;
		try {
			words = ResourceReader.readFromResource("dictionary.txt");
			log.info("Loaded {} words from dict",words.size());
		} catch (IOException e) {
			//Если не смогли прочитать словарь, то логируем и выходим
			log.error("Sorry, unable to read Word Dictionary from resources.", e);
			return;
		}
		String user_wanna_play = "y";
		console.printf("Welcome to Bulls and Cows game!\n");
		//Если пользователь не хочет играть, то завершаем цикл и прощаемся
		while(!user_wanna_play.equalsIgnoreCase("n")){
			//Загадываем случайное слово
			String theWord = words.get(Double.valueOf(words.size() * Math.random()).intValue());
			//Предлагаем угадать и читаем слово пользователя
			String guess = console.readLine("I offered a %d-letter word %s, your guess?\n>",theWord.length(), theWord);
			//Пока пользователь не угадал слово, считаем быков и коров для подсказки
			//Разделим Слово по символам и Поместим байты в множетво быков и коров
			List<Character> theBulls = new ArrayList<>();
			Set<Character> theCows = new HashSet<>();
			for(char c : theWord.toCharArray()){
				theBulls.add(Character.valueOf(c));
				theCows.add(Character.valueOf(c));
			}
			while (!guess.equalsIgnoreCase(theWord)){
				int bulls=0;
				int cows=0;
				//Если cлова разной длинны, просим новое слово
				if(theWord.length()!=guess.length()){
					guess = console.readLine("Your guess is %d letter, but the Word is %d, try next\n>",guess.length(), theWord.length());
					continue;
				}
				for(int i = 0; i< guess.length(); i++){
					if(theBulls.get(i)==guess.charAt(i)){
						bulls++;
					}else if(theCows.contains(guess.charAt(i))){
						cows++;
					}
				}
				guess = console.readLine("Bulls: %d\n Cows: %d\n>",bulls, cows);
			}
			//Пишем слово
			console.printf("Gratz! You win, the word is %s\n", theWord);
			user_wanna_play = console.readLine("Wanna play again? Y/N\n>");
		}
		console.printf("Good Bye! See you again!\n");
	}
}
