package com.trustwave.horseracetrack.serviceImpl;

import com.trustwave.horseracetrack.model.HorseTrackATM;
import com.trustwave.horseracetrack.service.CommandExecutor;

public class InitializeATM implements CommandExecutor {

    private final HorseTrackATM horseTrackATM;

    public InitializeATM(HorseTrackATM horseTrackATM) {
        this.horseTrackATM = horseTrackATM;
    }

    @Override
    public void execute() {
        horseTrackATM.initialize();
    }
}
