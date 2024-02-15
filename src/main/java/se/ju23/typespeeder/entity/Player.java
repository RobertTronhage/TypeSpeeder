package se.ju23.typespeeder.entity;

import jakarta.persistence.*;
import se.ju23.typespeeder.enums.RoleType;

import java.util.List;

@Entity
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(name="username")
    String userName;
    String email;
    String password;
    int level; // för varje 100 XP levlar man en gång!
    int experience;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private RoleType role;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    private List<Match> matches;

    public Player(String userName, String email, String password, int level, int experience, RoleType role) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.level = level;
        this.experience = experience;
        this.role = role;
    }

    public Player() {

    }

    public Player(String username, String password, int i, int i1, RoleType roleType) {
    }

    public long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getLevel() {
        return level;
    }

    public int getExperience() {
        return experience;
    }

    public RoleType getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", level=" + level +
                ", experience=" + experience +
                ", role=" + role +
                '}';
    }
}
