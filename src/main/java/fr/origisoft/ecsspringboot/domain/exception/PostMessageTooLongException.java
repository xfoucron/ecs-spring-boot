package fr.origisoft.ecsspringboot.domain.exception;

public class PostMessageTooLongException extends RuntimeException {
    private final int maxLength;
    private final int actualLength;

    public PostMessageTooLongException(int maxLength, int actualLength) {
        super("Post message exceeds max length");
        this.maxLength = maxLength;
        this.actualLength = actualLength;
    }

    public int maxLength() {
        return maxLength;
    }

    public int actualLength() {
        return actualLength;
    }
}
