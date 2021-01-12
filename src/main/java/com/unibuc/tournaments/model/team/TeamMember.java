package com.unibuc.tournaments.model.team;

import java.util.Date;
import java.util.List;

public class TeamMember {
    private int id;
    private int teamId;
    private TeamMemberType type;
    private String firstName;
    private String lastName;
    private String nickName;
    private Date dateOfBirth;
    private List<TeamMemberCategory> categories;

    public int getId() {
        return id;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public List<TeamMemberCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<TeamMemberCategory> categories) {
        this.categories = categories;
    }

    public void setId(int id) {

        this.id = id;
    }

    public TeamMemberType getType() {
        return type;
    }

    public void setType(TeamMemberType type) {
        this.type = type;
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

    public TeamMember(int id, int teamId, TeamMemberType type, String firstName, String lastName, String nickName, Date dateOfBirth, List<TeamMemberCategory> categories) {
        this.id = id;
        this.teamId = teamId;
        this.type = type;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.dateOfBirth = dateOfBirth;
        this.categories = categories;
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
