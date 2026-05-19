package fr.origisoft.ecsspringboot.application.usecase;

import fr.origisoft.ecsspringboot.application.command.FindPostCommand;
import fr.origisoft.ecsspringboot.application.exception.PostNotFoundException;
import fr.origisoft.ecsspringboot.domain.model.Post;
import fr.origisoft.ecsspringboot.domain.repository.PostRepository;
import org.springframework.stereotype.Component;

@Component
public class FindPostUseCase {
    private final PostRepository postRepository;

    public FindPostUseCase(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post execute(FindPostCommand command) {
        return postRepository
                .findById(command.postId())
                .orElseThrow(() ->
                        new PostNotFoundException(command.postId())
                );
    }
}
