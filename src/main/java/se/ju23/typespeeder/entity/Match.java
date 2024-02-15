package se.ju23.typespeeder.entity;

import jakarta.persistence.*;
import se.ju23.typespeeder.enums.GameMode;
import se.ju23.typespeeder.enums.RoleType;

@Entity
@Table(name="match")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @ManyToOne
    @JoinColumn(name="player_id",referencedColumnName = "id")
    @Column(name="playerid")
    private Player player;
    @Column(name="amountpercent")
    double amountOfCorrectWordsInPercent;
    @Column(name="amountpcs")
    double amountOfCorrectWordsInPcs;
    @Column(name="amountconsecutive")
    double amountOfConsecutiveCorrectWords;
    @Column(name="elapsedtime")
    double timeToCompleteInSec;
    @Column(name = "gamemode")
    @Enumerated(EnumType.STRING)
    private GameMode gameMode;

    public Match() {
    }

    public Match(Player player, double amountOfCorrectWordsInPercent, double amountOfCorrectWordsInPcs, double amountOfConsecutiveCorrectWords, double timeToCompleteInSec, GameMode gameMode) {
        this.player = player;
        this.amountOfCorrectWordsInPercent = amountOfCorrectWordsInPercent;
        this.amountOfCorrectWordsInPcs = amountOfCorrectWordsInPcs;
        this.amountOfConsecutiveCorrectWords = amountOfConsecutiveCorrectWords;
        this.timeToCompleteInSec = timeToCompleteInSec;
        this.gameMode = gameMode;
    }

    public long getId() {
        return id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public double getAmountOfCorrectWordsInPercent() {
        return amountOfCorrectWordsInPercent;
    }

    public void setAmountOfCorrectWordsInPercent(double amountOfCorrectWordsInPercent) {
        this.amountOfCorrectWordsInPercent = amountOfCorrectWordsInPercent;
    }

    public double getAmountOfCorrectWordsInPcs() {
        return amountOfCorrectWordsInPcs;
    }

    public void setAmountOfCorrectWordsInPcs(double amountOfCorrectWordsInPcs) {
        this.amountOfCorrectWordsInPcs = amountOfCorrectWordsInPcs;
    }

    public double getAmountOfConsecutiveCorrectWords() {
        return amountOfConsecutiveCorrectWords;
    }

    public void setAmountOfConsecutiveCorrectWords(double amountOfConsecutiveCorrectWords) {
        this.amountOfConsecutiveCorrectWords = amountOfConsecutiveCorrectWords;
    }

    public double getTimeToCompleteInSec() {
        return timeToCompleteInSec;
    }

    public void setTimeToCompleteInSec(double timeToCompleteInSec) {
        this.timeToCompleteInSec = timeToCompleteInSec;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", player=" + player +
                ", amountOfCorrectWordsInPercent=" + amountOfCorrectWordsInPercent +
                ", amountOfCorrectWordsInPcs=" + amountOfCorrectWordsInPcs +
                ", amountOfConsecutiveCorrectWords=" + amountOfConsecutiveCorrectWords +
                ", timeToCompleteInSec=" + timeToCompleteInSec +
                ", gameMode=" + gameMode +
                '}';
    }
}
