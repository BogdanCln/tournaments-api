package com.unibuc.tournaments.exception.team;

public class TeamMemberNotFoundException extends RuntimeException {
    public TeamMemberNotFoundException() {
        super("Team member not found.");
    }
}
