/**
 * The SpeedLeaderboard class represents an entry in the speed leaderboard view of the TypeSpeeder game.
 * It contains information about the username and the time taken to complete a game in seconds.
 *
 * @Author: Robert Tronhage
 */
package se.ju23.typespeeder.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "speed_leaderboard_view")
public class SpeedLeaderboard {

    @Id
    private String username;
    @Column(name = "time_to_complete_in_sec")
    private double timeToCompleteInSec;

    public SpeedLeaderboard(String username, double timeToCompleteInSec) {
        this.username = username;
        this.timeToCompleteInSec = timeToCompleteInSec;
    }

    public SpeedLeaderboard() {
    }

    public String getUsername() {
        return username;
    }

    public double getTimeToCompleteInSec() {
        return timeToCompleteInSec;
    }

    @Override
    public String toString() {
        return "SpeedLeaderboard{" +
                "username='" + username + '\'' +
                ", timeToCompleteInSec=" + timeToCompleteInSec +
                '}';
    }
}
