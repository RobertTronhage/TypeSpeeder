package se.ju23.typespeeder.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NewsLetter {
    private static final int MIN_CONTENT_LENGTH = 100;
    private static final int MAX_CONTENT_LENGTH = 200;

    private String content;
    private LocalDateTime publishDateTime;

    public NewsLetter(String content, LocalDateTime publishDateTime) {
        if (content == null || content.length() < MIN_CONTENT_LENGTH || content.length() > MAX_CONTENT_LENGTH) {
            throw new IllegalArgumentException("Content must be between " + MIN_CONTENT_LENGTH + " and " + MAX_CONTENT_LENGTH + " characters long.");
        }
        this.content = content;
        this.publishDateTime = publishDateTime;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getPublishDateTime() {
        return publishDateTime;
    }
}
