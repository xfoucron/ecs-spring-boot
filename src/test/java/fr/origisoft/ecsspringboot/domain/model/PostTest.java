package fr.origisoft.ecsspringboot.domain.model;

import fr.origisoft.ecsspringboot.domain.exception.PostMessageTooLongException;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class PostTest {
    @Test
    void shouldThrowExceptionWhenMessageIsTooLong() {
        assertThrows(PostMessageTooLongException.class, () ->
                new Post(PostId.generate(), "A".repeat(Post.MAX_LENGTH + 1), new Date()));
    }
}
