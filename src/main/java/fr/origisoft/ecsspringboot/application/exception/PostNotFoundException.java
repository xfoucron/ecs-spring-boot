package fr.origisoft.ecsspringboot.application.exception;

import fr.origisoft.ecsspringboot.domain.model.PostId;

public class PostNotFoundException extends RuntimeException {
    private final PostId postId;

    public PostNotFoundException(PostId postId) {
        super("Post not found");
        this.postId = postId;
    }

    public PostId postId() {
        return postId;
    }
}
