package com.trustwave.horseracetrack.serviceImpl;

import com.trustwave.horseracetrack.model.HorseTrackATM;
import com.trustwave.horseracetrack.service.CommandExecutor;

public class Restock implements CommandExecutor {
    private final HorseTrackATM horseTrackATM;

    public Restock(HorseTrackATM horseTrackATM) {
        this.horseTrackATM = horseTrackATM;
    }

    public void execute() {
        horseTrackATM.restock();
    }
}
