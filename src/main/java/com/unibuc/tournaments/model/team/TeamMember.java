package com.unibuc.tournaments.model.team;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "team_member")
public class TeamMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    @NotNull
    private Team team;

    @Enumerated(value = EnumType.STRING)
    private TeamMemberType type;

    private String firstName;
    private String lastName;

    @NotBlank
    private String nickName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    @ManyToMany
    @JoinTable(name = "team_member_category",
            joinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "team_member_category_id", referencedColumnName = "id"))
    private List<TeamMemberCategory> categories;
}
