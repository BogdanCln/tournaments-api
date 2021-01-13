package com.unibuc.tournaments.dto;

import com.unibuc.tournaments.model.team.TeamMemberType;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class TeamMemberRequest {
    @NotNull
    private TeamMemberType type;
    @NotNull
    private int teamId;
    private String firstName;
    private String lastName;
    private String nickName;
    private Date dateOfBirth;

    public TeamMemberRequest(@NotNull TeamMemberType type, @NotNull int teamId, String firstName, String lastName, String nickName, Date dateOfBirth) {
        this.type = type;
        this.teamId = teamId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.dateOfBirth = dateOfBirth;
    }

    public TeamMemberType getType() {
        return type;
    }

    public void setType(TeamMemberType type) {
        this.type = type;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
