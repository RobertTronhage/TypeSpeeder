package se.ju23.typespeeder.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "streak_leaderboard_view")
public class StreakLeaderboard {

    @Id
    private String username;
    @Column(name = "amount_of_consecutive_correct_words")
    private double amountOfConsecutiveCorrectWords;

    public StreakLeaderboard(String username, double amountOfConsecutiveCorrectWords) {
        this.username = username;
        this.amountOfConsecutiveCorrectWords = amountOfConsecutiveCorrectWords;
    }

    public StreakLeaderboard() {
    }
    public String getUsername() {
        return username;
    }

    public double getAmountOfConsecutiveCorrectWords() {
        return amountOfConsecutiveCorrectWords;
    }

    public void setAmountOfConsecutiveCorrectWords(double maxStreak) {
        this.amountOfConsecutiveCorrectWords = maxStreak;
    }

    @Override
    public String toString() {
        return "StreakLeaderboard{" +
                "username='" + username + '\'' +
                ", amountOfConsecutiveCorrectWords=" + amountOfConsecutiveCorrectWords +
                '}';
    }
}
