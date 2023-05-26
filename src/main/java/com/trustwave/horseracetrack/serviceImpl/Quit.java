package com.trustwave.horseracetrack.serviceImpl;

import com.trustwave.horseracetrack.model.HorseTrackATM;
import com.trustwave.horseracetrack.service.CommandExecutor;

public class Quit implements CommandExecutor {

    private final HorseTrackATM horseTrackATM;

    public Quit(HorseTrackATM horseTrackATM) {
        this.horseTrackATM = horseTrackATM;
    }

    @Override
    public void execute() {
        horseTrackATM.quitApplication();
    }
}
