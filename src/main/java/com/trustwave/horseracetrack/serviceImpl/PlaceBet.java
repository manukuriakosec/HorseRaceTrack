package com.trustwave.horseracetrack.serviceImpl;

import com.trustwave.horseracetrack.model.HorseTrackATM;
import com.trustwave.horseracetrack.service.CommandExecutor;

public class PlaceBet implements CommandExecutor {

    private final HorseTrackATM horseTrackATM;
    private final String horseNumber;
    private final String betAmount;

    public PlaceBet(HorseTrackATM horseTrackATM, String horseNumber, String betAmount) {
        this.horseTrackATM = horseTrackATM;
        this.horseNumber = horseNumber;
        this.betAmount = betAmount;
    }

    public void execute() {
        horseTrackATM.placeBet(horseNumber, betAmount);
    }
}
