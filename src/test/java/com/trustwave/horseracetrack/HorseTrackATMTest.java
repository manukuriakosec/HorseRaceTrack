package com.trustwave.horseracetrack;

import com.trustwave.horseracetrack.model.Horse;
import com.trustwave.horseracetrack.model.HorseTrackATM;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HorseTrackATMTest {

    private HorseTrackATM horseTrackATM;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() {
        horseTrackATM = new HorseTrackATM();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void testInitialize() {
        horseTrackATM.initialize();

        // Test inventory initialization
        Map<Integer, Integer> expectedInventory = new HashMap<>();
        expectedInventory.put(1, 10);
        expectedInventory.put(5, 10);
        expectedInventory.put(10, 10);
        expectedInventory.put(20, 10);
        expectedInventory.put(100, 10);
        assertEquals(expectedInventory.size(), horseTrackATM.getInventory().size());

        // Test horse map initialization
        Map<Integer, Horse> expectedHorses = new HashMap<>();
        expectedHorses.put(1, new Horse(1, "That Darn Gray Cat", 5, "won"));
        expectedHorses.put(2, new Horse(2, "Fort Utopia", 10, "lost"));
        expectedHorses.put(3, new Horse(3, "Count Sheep", 9, "lost"));
        expectedHorses.put(4, new Horse(4, "Ms Traitour", 4, "lost"));
        expectedHorses.put(5, new Horse(5, "Real Princess", 3, "lost"));
        expectedHorses.put(6, new Horse(6, "Pa Kettle", 5, "lost"));
        expectedHorses.put(7, new Horse(7, "Gin Stinger", 6, "lost"));
        assertEquals(expectedHorses.size(), horseTrackATM.getHorses().size());

        // Test winning horse number initialization
        assertEquals(1, horseTrackATM.getWinningHorseNumber());

        // Test displayInventoryAndHorses() method
        String expectedOutput = "Inventory: \n" + "$1,10 \n" + "$5,10 \n" + "$10,10 \n" + "$20,10 \n" + "$100,10 \n" + "Horses: \n" + "1,That Darn Gray Cat,5,won \n" + "2,Fort Utopia,10,lost \n" + "3,Count Sheep,9,lost \n" + "4,Ms Traitour,4,lost \n" + "5,Real Princess,3,lost \n" + "6,Pa Kettle,5,lost \n" + "7,Gin Stinger,6,lost \n";
        assertEquals(expectedOutput.length(), outputStream.toString().length());
    }

    @Test
    public void testRestock() {
        horseTrackATM.initialize();
        horseTrackATM.getInventory().put(5, 1);
        horseTrackATM.restock();
        assertEquals(10, horseTrackATM.getInventory().get(5));
    }

    @Test
    public void testSetWinningHorse_ValidHorseNumber() {
        horseTrackATM.initialize();
        horseTrackATM.setWinningHorse(3);

        assertEquals("won", horseTrackATM.getHorses().get(3).getStatus());

        // Test winningHorseNumber update
        assertEquals(3, horseTrackATM.getWinningHorseNumber());
    }

    @Test
    public void testSetWinningHorse_InvalidHorseNumber() {
        horseTrackATM.initialize();
        horseTrackATM.setWinningHorse(8);

        // Test invalid horse number

        assertTrue(outputStream.toString().contains("Invalid Horse Number: 8"));
    }

    @Test
    public void testPlaceBet_ValidBet() {
        horseTrackATM.initialize();
        horseTrackATM.placeBet("1", "5");

        // Test payout and inventory update
        assertTrue(outputStream.toString().contains("That Darn Gray Cat,25"));
    }


    @Test
    public void testPlaceBet_InValidBetAmount() {
        horseTrackATM.initialize();
        horseTrackATM.placeBet("3", "5.8");

        // Test payout and inventory update

        assertTrue(outputStream.toString().contains("Invalid Bet: 5.8"));
    }

    @Test
    public void testPlaceBet_InValidBetHorse() {
        horseTrackATM.initialize();
        horseTrackATM.placeBet("8", "20");

        // Test payout and inventory update

        assertTrue(outputStream.toString().contains("Invalid Horse Number: 8"));
    }

    @Test
    public void testPlaceBet_No_Payout() {
        horseTrackATM.initialize();
        horseTrackATM.placeBet("3", "5");
        // Test payout and inventory update
        assertTrue(outputStream.toString().contains("No Payout: Count Sheep"));
    }


    @Test
    public void testPlaceBet_InvalidHorseNumber() {
        horseTrackATM.initialize();
        horseTrackATM.placeBet("8", "5");

        // Test invalid horse number
        assertTrue(outputStream.toString().contains("Invalid Horse Number: 8"));
    }

    @Test
    public void testInSufficientFunds() {
        horseTrackATM.initialize();
        horseTrackATM.placeBet("1", "9999");

        // Test invalid horse number
        assertTrue(outputStream.toString().contains("Insufficient Funds: 49995"));
    }
}


