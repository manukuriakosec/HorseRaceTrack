package com.trustwave.horseracetrack;

import com.trustwave.horseracetrack.controller.HorseTrackATMController;
import com.trustwave.horseracetrack.model.HorseTrackATM;
import com.trustwave.horseracetrack.serviceImpl.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

/**
 * The type Horse race track application use command patten to provide the result
 */
@SpringBootApplication
public class HorseRaceTrackApplication {

    public static void main(String[] args) {
        SpringApplication.run(HorseRaceTrackApplication.class, args);
        HorseTrackATM horseTrackATM = new HorseTrackATM();
        HorseTrackATMController horseTrackATMController = new HorseTrackATMController();
        horseTrackATMController.setCommand(new InitializeATM(horseTrackATM));
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) {
                String command = scanner.nextLine().trim();
                if (command == null || command.trim().isEmpty()) {
                    System.out.println("Invalid Command: " + command);
                } else if (command.equalsIgnoreCase("r") || command.equalsIgnoreCase("R")) {
                    horseTrackATMController.setCommand(new Restock(horseTrackATM));
                } else if ((command.startsWith("w") || command.startsWith("W")) && command.length() >= 2) {
                    int horseNumber = Integer.parseInt(command.substring(2).trim());
                    horseTrackATMController.setCommand(new WiningHorse(horseTrackATM, horseNumber));
                } else if (command.startsWith("q") || command.startsWith("Q")) {
                    horseTrackATMController.setCommand(new Quit(horseTrackATM));
                    scanner.close();
                } else if (Character.isDigit(command.charAt(0))) {
                    String[] parts = command.split("\\s+");
                    if (parts.length == 2 && parts[0].matches("\\d+") && parts[1].matches("\\d+")) {
                        horseTrackATMController.setCommand(new PlaceBet(horseTrackATM, parts[0], parts[1]));
                    } else {
                        System.out.println("Invalid Command: " + command);
                    }
                } else {
                    System.out.println("Invalid Command: " + command);
                }
            }
        } catch (Exception e) {
            System.out.println("System interrupted. Please restart");
            e.printStackTrace();
        }

    }
}
