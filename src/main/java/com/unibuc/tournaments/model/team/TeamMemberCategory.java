package com.unibuc.tournaments.model.team;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "team_member_category")
public class TeamMemberCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToMany(mappedBy = "categories")
    private List<TeamMember> teamMembers;
}
