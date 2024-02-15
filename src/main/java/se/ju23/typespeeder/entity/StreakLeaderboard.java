package se.ju23.typespeeder.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class StreakLeaderboard {

    @Id
    private Long id;
    private Long playerId;
    private String username;
    private double maxStreak;

    public StreakLeaderboard(Long playerId, String username, double maxStreak) {
        this.playerId = playerId;
        this.username = username;
        this.maxStreak = maxStreak;
    }

    public StreakLeaderboard() {
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getMaxStreak() {
        return maxStreak;
    }

    public void setMaxStreak(double maxStreak) {
        this.maxStreak = maxStreak;
    }

    @Override
    public String toString() {
        return "StreakLeaderboard{" +
                "playerId=" + playerId +
                ", username='" + username + '\'' +
                ", maxStreak=" + maxStreak +
                '}';
    }
}
