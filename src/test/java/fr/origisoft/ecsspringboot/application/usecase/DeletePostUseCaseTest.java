package fr.origisoft.ecsspringboot.application.usecase;

import fr.origisoft.ecsspringboot.application.command.CreatePostCommand;
import fr.origisoft.ecsspringboot.application.command.DeletePostCommand;
import fr.origisoft.ecsspringboot.application.command.FindPostCommand;
import fr.origisoft.ecsspringboot.application.exception.PostNotFoundException;
import fr.origisoft.ecsspringboot.domain.model.Post;
import fr.origisoft.ecsspringboot.domain.repository.PostRepository;
import fr.origisoft.ecsspringboot.infrastructure.persistence.InMemoryPostRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeletePostUseCaseTest {
    @Test
    void shouldDeletePost() {
        PostRepository postRepository = new InMemoryPostRepository();

        CreatePostUseCase createPostUseCase = new CreatePostUseCase(postRepository);
        DeletePostUseCase deletePostUseCase = new DeletePostUseCase(postRepository);
        FindPostUseCase findPostUseCase = new FindPostUseCase(postRepository);

        Post post = createPostUseCase.execute(new CreatePostCommand("hello"));

        deletePostUseCase.execute(new DeletePostCommand(post.id()));

        assertThrows(PostNotFoundException.class, () ->
                findPostUseCase.execute(new FindPostCommand(post.id())));
    }
}
