package com.unibuc.tournaments.exception.team;

public class TeamMemberNotCreatedException extends RuntimeException {
    public TeamMemberNotCreatedException() {
        super("Team member not created.");
    }
}
