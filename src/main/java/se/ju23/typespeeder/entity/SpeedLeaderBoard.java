package se.ju23.typespeeder.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class SpeedLeaderBoard {

    @Id
    private Long id;
    private Long playerId;
    private String username;
    private double inputSpeed;

    public SpeedLeaderBoard(Long playerId, String username, double inputSpeed) {
        this.playerId = playerId;
        this.username = username;
        this.inputSpeed = inputSpeed;
    }

    public SpeedLeaderBoard() {
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

    public double getInputSpeed() {
        return inputSpeed;
    }

    public void setInputSpeed(double inputSpeed) {
        this.inputSpeed = inputSpeed;
    }

    @Override
    public String toString() {
        return "SpeedLeaderBoard{" +
                "playerId=" + playerId +
                ", username='" + username + '\'' +
                ", inputSpeed=" + inputSpeed +
                '}';
    }
}
