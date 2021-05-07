package com.unibuc.tournaments.model.team;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data()
public class TeamMember {
    private int id;
    private int teamId;
    private TeamMemberType type;
    private String firstName;
    private String lastName;
    private String nickName;
    private Date dateOfBirth;
    private List<TeamMemberCategory> categories;

    public TeamMember(int teamId, TeamMemberType type, String firstName, String lastName, String nickName, Date dateOfBirth) {
        this.teamId = teamId;
        this.type = type;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.dateOfBirth = dateOfBirth;
    }

    public TeamMember(int id, int teamId, TeamMemberType type, String firstName, String lastName, String nickName, Date dateOfBirth) {
        this.id = id;
        this.teamId = teamId;
        this.type = type;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.dateOfBirth = dateOfBirth;
    }
}
