package com.trustwave.horseracetrack.model;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class HorseTrackATM {

    private Map<Integer, Horse> horses;
    private Map<Integer, Integer> inventory;
    private int winningHorseNumber;

    public void initialize() {
        horses = new HashMap<>();
        initializeHorseMap();
        inventory = new HashMap<>();
        initializeInventory();
        winningHorseNumber = 1;
        displayInventoryAndHorses();
    }

    private void initializeInventory() {
        inventory.put(1, 10);
        inventory.put(5, 10);
        inventory.put(10, 10);
        inventory.put(20, 10);
        inventory.put(100, 10);
    }

    public void initializeHorseMap() {
        horses.put(1, new Horse(1, "That Darn Gray Cat", 5, "won"));
        horses.put(2, new Horse(2, "Fort Utopia", 10, "lost"));
        horses.put(3, new Horse(3, "Count Sheep", 9, "lost"));
        horses.put(4, new Horse(4, "Ms Traitour", 4, "lost"));
        horses.put(5, new Horse(5, "Real Princess", 3, "lost"));
        horses.put(6, new Horse(6, "Pa Kettle", 5, "lost"));
        horses.put(7, new Horse(7, "Gin Stinger", 6, "lost"));
    }

    public void restock() {
        inventory.clear();
        initializeInventory();
        displayInventoryAndHorses();
    }

    public void setWinningHorse(int horseNumber) {
        if (horseNumber < 1 || horseNumber > 7) {
            System.out.println("Invalid Horse Number: " + horseNumber);
            displayInventoryAndHorses();
            return;
        }
        horses.get(winningHorseNumber).setStatus("lost");
        horses.get(horseNumber).setStatus("won");
        winningHorseNumber = horseNumber;
        displayInventoryAndHorses();
    }

    public void placeBet(String horseNumber, String amount) {
        int horseNo = Integer.parseInt(horseNumber);
        float betAmount = Float.parseFloat(amount);
        if (horseNo < 1 || horseNo > 7) {
            System.out.println("Invalid Horse Number: " + horseNumber);
            displayInventoryAndHorses();
            return;
        }

        Horse horse = horses.get(horseNo);
        if (betAmount % 1 != 0) {
            System.out.println("Invalid Bet: " + betAmount);
            displayInventoryAndHorses();
            return;
        }

        if (horse.getStatus().equals("won")) {
            int totalWinnings = (int) (betAmount * horse.getOdds());
            if (!hasSufficientFunds(totalWinnings)) {
                System.out.println("Insufficient Funds: " + totalWinnings);
                displayInventoryAndHorses();
                return;
            }

            System.out.println("Payout: " + horse.getName() + "," + totalWinnings);
            distributePayout(totalWinnings);
            displayInventoryAndHorses();
        } else {
            System.out.println("No Payout: " + horse.getName());
            displayInventoryAndHorses();
        }
    }

    private boolean hasSufficientFunds(int amount) {
        int total = 0;
        for (Map.Entry<Integer, Integer> entry : inventory.entrySet()) {
            int denomination = entry.getKey();
            total += denomination * entry.getValue();
        }
        return amount <= total;
    }

    private void distributePayout(int amount) {
        Map<Integer, Integer> payout = new HashMap<>();
        payout.put(1, 0);
        payout.put(5, 0);
        payout.put(10, 0);
        payout.put(20, 0);
        payout.put(100, 0);

        int remainingAmount = amount;

        int[] denominations = {100, 20, 10, 5, 1};
        for (int denomination : denominations) {
            int count = remainingAmount / denomination;
            count = Math.min(count, inventory.get(denomination));
            remainingAmount -= count * denomination;
            payout.put(denomination, count);
        }

        if (remainingAmount > 0) {
            System.out.println("Insufficient Funds: " + amount);
        } else {
            for (Map.Entry<Integer, Integer> entry : payout.entrySet()) {
                int denomination = entry.getKey();
                int count = entry.getValue();
                inventory.put(entry.getKey(), inventory.get(denomination) - count);
            }
            displayDispensing(payout);
        }
    }

    private void displayDispensing(Map<Integer, Integer> payout) {

        payout.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(LinkedHashMap::new, (m, e) -> m.put(e.getKey(), e.getValue()), Map::putAll).forEach((key, value) -> System.out.println("$" + key + "," + value));
    }

    private void displayInventory() {
        System.out.println("Inventory:");
        inventory.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(LinkedHashMap::new, (m, e) -> m.put(e.getKey(), e.getValue()), Map::putAll).forEach((key, value) -> System.out.println("$" + key + "," + value));
    }

    private void displayHorses() {
        System.out.println("Horses:");
        for (Map.Entry<Integer, Horse> entry : horses.entrySet()) {
            Horse horse = entry.getValue();
            System.out.println(horse.getId() + "," + horse.getName() + "," + horse.getOdds() + "," + horse.getStatus());
        }
    }

    private void displayInventoryAndHorses() {
        displayInventory();
        displayHorses();
    }

    public void quitApplication() {
        System.exit(0);
    }


    public Map<Integer, Integer> getInventory() {
        return inventory;
    }

    public Map<Integer, Horse> getHorses() {
        return horses;
    }

    public int getWinningHorseNumber() {
        return winningHorseNumber;
    }
}
