package com.trustwave.horseracetrack;

import com.trustwave.horseracetrack.controller.HorseTrackATMController;
import com.trustwave.horseracetrack.model.HorseTrackATM;
import com.trustwave.horseracetrack.serviceImpl.InitializeATM;
import com.trustwave.horseracetrack.serviceImpl.PlaceBet;
import com.trustwave.horseracetrack.serviceImpl.Restock;
import com.trustwave.horseracetrack.serviceImpl.WiningHorse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class HorseRaceTrackApplicationTests {

    private HorseTrackATM horseTrackATM;
    private HorseTrackATMController horseTrackATMController;

    @BeforeEach
    public void setup() {
        horseTrackATM = new HorseTrackATM();
        horseTrackATMController = new HorseTrackATMController();
        horseTrackATMController.setCommand(new InitializeATM(horseTrackATM));
    }

    @Test
    public void testRestockCommand() {
        String input = "r\n";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        horseTrackATM.getInventory().put(5, 1);
        horseTrackATMController.setCommand(new Restock(horseTrackATM));
        assertEquals(10, horseTrackATM.getInventory().get(5));
        System.setIn(stdin);
    }

    @Test
    public void testWiningHorseCommand() {
        String input = "w 3\n";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        horseTrackATMController.setCommand(new WiningHorse(horseTrackATM, 3));

        assertEquals("won", horseTrackATM.getHorses().get(3).getStatus());

        // Test winningHorseNumber update
        assertEquals(3, horseTrackATM.getWinningHorseNumber());

        System.setIn(stdin);
    }


    @Test
    public void testPlaceBetCommand() {
        String input = "3 10\n";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        horseTrackATMController.setCommand(new PlaceBet(horseTrackATM, "3", "10"));

        // Assert the expected behavior after executing the PlaceBet command

        System.setIn(stdin);
    }

}
