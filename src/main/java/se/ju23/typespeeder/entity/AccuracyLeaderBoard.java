/**
 * The AccuracyLeaderBoard class represents an entity mapping to the accuracy_leaderboard_view table in the database.
 * It stores information about players' accuracy scores for leaderboard display.
 *
 * @Author: Robert Tronhage
 */
package se.ju23.typespeeder.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "accuracy_leaderboard_view")
public class AccuracyLeaderBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long playerId;
    private String username;
    private double accuracy;

    public AccuracyLeaderBoard(Long playerId, String username, double accuracy) {
        this.playerId = playerId;
        this.username = username;
        this.accuracy = accuracy;
    }

    public AccuracyLeaderBoard() {
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

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    @Override
    public String toString() {
        return "AccuracyLeaderBoard{" +
                "playerId=" + playerId +
                ", username='" + username + '\'' +
                ", accuracy=" + accuracy + "%" +
                '}';
    }
}
