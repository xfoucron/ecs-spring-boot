package fr.origisoft.ecsspringboot.domain.model;

import fr.origisoft.ecsspringboot.domain.exception.PostMessageTooLongException;

import java.util.Date;

public record Post(PostId id, String message, Date createdAt) {
    public static final int MAX_LENGTH = 100;

    public Post {
        if (message == null) {
            throw new NullPointerException("message is null");
        }

        if (message.length() > MAX_LENGTH) {
            throw new PostMessageTooLongException(MAX_LENGTH, message.length());
        }
    }
}
