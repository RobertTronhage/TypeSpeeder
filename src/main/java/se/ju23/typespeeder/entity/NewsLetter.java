package se.ju23.typespeeder.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NewsLetter {
    private String content = "Potatoes, humble yet versatile, are a staple in cuisines worldwide. From crispy fries to creamy mashed, they delight our taste buds with endless possibilities, nourishing both body and soul.";

    private LocalDateTime publishDateTime;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public NewsLetter(LocalDateTime publishDateTime) {
        this.publishDateTime = publishDateTime;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getPublishDateTime() {
        return publishDateTime;
    }
}
