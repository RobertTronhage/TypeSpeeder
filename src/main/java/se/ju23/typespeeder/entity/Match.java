package se.ju23.typespeeder.entity;

import jakarta.persistence.*;

@Entity
@Table(name="match")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name="playerid")
    long playerId;
    @Column(name="amountpercent")
    double amountOfCorrectWordsInPercent;
    @Column(name="amountpcs")
    double amountOfCorrectWordsInPcs;
    @Column(name="amountconsecutive")
    double amountOfConsecutiveCorrectWords;
    @Column(name="elapsedtime")
    double timeToCompleteInSec;

}
