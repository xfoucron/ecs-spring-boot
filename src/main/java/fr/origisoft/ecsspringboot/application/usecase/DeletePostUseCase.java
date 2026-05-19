package fr.origisoft.ecsspringboot.application.usecase;

import fr.origisoft.ecsspringboot.application.command.DeletePostCommand;
import fr.origisoft.ecsspringboot.application.exception.PostNotFoundException;
import fr.origisoft.ecsspringboot.domain.model.Post;
import fr.origisoft.ecsspringboot.domain.repository.PostRepository;
import org.springframework.stereotype.Component;

@Component
public class DeletePostUseCase {
    private final PostRepository postRepository;

    public DeletePostUseCase(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void execute(DeletePostCommand command) {
        Post post = postRepository
                .findById(command.postId())
                .orElseThrow(() ->
                        new PostNotFoundException(command.postId())
                );

        postRepository.delete(post.id());
    }
}
