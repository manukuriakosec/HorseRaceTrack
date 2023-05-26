package com.trustwave.horseracetrack.controller;

import com.trustwave.horseracetrack.service.CommandExecutor;

public class HorseTrackATMController {
    CommandExecutor command;

    public HorseTrackATMController() {
    }

    public void setCommand(CommandExecutor command) {
        command = command;
        command.execute();
    }


}
