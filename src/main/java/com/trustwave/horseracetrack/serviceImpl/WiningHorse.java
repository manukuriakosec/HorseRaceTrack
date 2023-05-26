package com.trustwave.horseracetrack.serviceImpl;

import com.trustwave.horseracetrack.model.HorseTrackATM;
import com.trustwave.horseracetrack.service.CommandExecutor;

public class WiningHorse implements CommandExecutor {

    private final HorseTrackATM horseTrackATM;
    private final int horseNumber;

    public WiningHorse(HorseTrackATM horseTrackATM, int horseNumber) {
        this.horseTrackATM = horseTrackATM;
        this.horseNumber = horseNumber;
    }

    public void execute() {
        horseTrackATM.setWinningHorse(horseNumber);
    }
}

