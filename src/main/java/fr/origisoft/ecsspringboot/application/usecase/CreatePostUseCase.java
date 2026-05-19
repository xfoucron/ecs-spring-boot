package fr.origisoft.ecsspringboot.application.usecase;

import fr.origisoft.ecsspringboot.application.command.CreatePostCommand;
import fr.origisoft.ecsspringboot.domain.model.Post;
import fr.origisoft.ecsspringboot.domain.model.PostId;
import fr.origisoft.ecsspringboot.domain.repository.PostRepository;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CreatePostUseCase {
    private final PostRepository postRepository;

    public CreatePostUseCase(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post execute(CreatePostCommand command) {
        Post post = new Post(PostId.generate(), command.message(), new Date());

        return postRepository.save(post);
    }
}
