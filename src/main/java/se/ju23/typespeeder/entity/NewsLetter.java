package se.ju23.typespeeder.entity;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class NewsLetter {
    private String content;
    private LocalDateTime publishDateTime;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public NewsLetter(LocalDateTime publishDateTime) {
        this.content = "This is the latest updates from Admin: As of patch 0.4.2 players are able to play game and save their results! to report bugs/issues, feel free to contact Admin at robert.tronhage@iths.se";
        this.publishDateTime = LocalDateTime.of(2024,02,23,10,15,20);
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getPublishDateTime() {
        return publishDateTime;
    }
}
