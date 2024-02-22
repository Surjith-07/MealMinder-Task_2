package Springboot.MealMinder;

import Springboot.MealMinder.commands.MenuMinderCommands;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class MealMinderApplication {

	public static void main(String[] args) {
		SpringApplication.run(MealMinderApplication.class, args);
		ApplicationContext context = SpringApplication.run(MealMinderApplication.class, args);

		//// Retrieve the MenuMinderCommands bean from the application context
		MenuMinderCommands menuMinderCommands = context.getBean(MenuMinderCommands.class);
		menuMinderCommands.start(new Scanner(System.in));
	}
}
