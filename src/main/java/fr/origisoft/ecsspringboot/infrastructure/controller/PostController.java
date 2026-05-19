package fr.origisoft.ecsspringboot.infrastructure.controller;

import fr.origisoft.ecsspringboot.application.command.CreatePostCommand;
import fr.origisoft.ecsspringboot.application.command.DeletePostCommand;
import fr.origisoft.ecsspringboot.application.command.FindPostCommand;
import fr.origisoft.ecsspringboot.application.exception.PostNotFoundException;
import fr.origisoft.ecsspringboot.application.usecase.CreatePostUseCase;
import fr.origisoft.ecsspringboot.application.usecase.DeletePostUseCase;
import fr.origisoft.ecsspringboot.application.usecase.FindPostUseCase;
import fr.origisoft.ecsspringboot.domain.exception.PostMessageTooLongException;
import fr.origisoft.ecsspringboot.domain.model.Post;
import fr.origisoft.ecsspringboot.domain.model.PostId;
import fr.origisoft.ecsspringboot.infrastructure.controller.request.CreatePostRequest;
import fr.origisoft.ecsspringboot.infrastructure.controller.response.ApiError;
import fr.origisoft.ecsspringboot.infrastructure.controller.response.CreatePostResponse;
import fr.origisoft.ecsspringboot.infrastructure.controller.response.FindPostResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.spi.LoggingEventBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/posts")
public class PostController {
    private static final Logger log = LoggerFactory.getLogger(PostController.class);

    private final CreatePostUseCase createPostUseCase;
    private final FindPostUseCase findPostUseCase;
    private final DeletePostUseCase deletePostUseCase;

    public PostController(CreatePostUseCase createPostUseCase, FindPostUseCase findPostUseCase, DeletePostUseCase deletePostUseCase) {
        this.createPostUseCase = createPostUseCase;
        this.findPostUseCase = findPostUseCase;
        this.deletePostUseCase = deletePostUseCase;
    }

    @PostMapping
    public ResponseEntity<CreatePostResponse> createPost(@RequestBody CreatePostRequest request) {
        Post post = createPostUseCase.execute(new CreatePostCommand(request.message()));

        return ResponseEntity.ok(new CreatePostResponse(post.id().id(), post.message(), post.createdAt()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FindPostResponse> findPost(@PathVariable UUID id) {
        Post post = findPostUseCase.execute(
                new FindPostCommand(PostId.of(id))
        );

        return ResponseEntity.ok(new FindPostResponse(post.id().id(), post.message(), post.createdAt()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable UUID id) {
        deletePostUseCase.execute(
                new DeletePostCommand(PostId.of(id))
        );

        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<?> handlePostNotFound(PostNotFoundException exception) {
        final String eventName = "postNotFound";

        addBaseMetadata(
                log.atWarn()
                        .addKeyValue("post.id", exception.postId().id())
                        .addKeyValue("event.reason", "post_not_found")
                        .addKeyValue("event.action", "post_get")
                , exception).log();

        return ResponseEntity
                .status(404)
                .body(new ApiError(
                        eventName,
                        exception.getMessage()
                ));
    }

    @ExceptionHandler(PostMessageTooLongException.class)
    public ResponseEntity<?> handlePostMessageTooLong(PostMessageTooLongException exception) {
        final String eventName = "postMessageTooLong";

        addBaseMetadata(
                log.atWarn()
                        .addKeyValue("event.action", "post_create")
                        .addKeyValue("event.reason", "message_too_long")
                        .addKeyValue("post.message.max_length", exception.maxLength())
                        .addKeyValue("post.message.actual_length", exception.actualLength())
                , exception).log();

        return ResponseEntity
                .badRequest()
                .body(new ApiError(
                        eventName,
                        exception.getMessage()
                ));
    }

    private LoggingEventBuilder addBaseMetadata(LoggingEventBuilder base, Throwable throwable) {
        return base
                .setMessage(throwable.getMessage())
                .addKeyValue("error.type", throwable.getClass().getSimpleName())
                .addKeyValue("event.outcome", "failure");
    }
}
