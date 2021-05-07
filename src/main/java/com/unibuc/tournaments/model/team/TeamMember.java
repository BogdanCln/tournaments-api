package com.unibuc.tournaments.model.team;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "team_member")
public class TeamMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    private TeamMemberType type;
    private String firstName;
    private String lastName;
    private String nickName;
    private Date dateOfBirth;

    @ManyToMany
    @JoinTable(name = "team_member_category",
            joinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "team_member_category_id", referencedColumnName = "id"))
    private List<TeamMemberCategory> categories;
}
