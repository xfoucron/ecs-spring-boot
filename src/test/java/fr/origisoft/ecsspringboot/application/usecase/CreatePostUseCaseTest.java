package fr.origisoft.ecsspringboot.application.usecase;

import fr.origisoft.ecsspringboot.application.command.CreatePostCommand;
import fr.origisoft.ecsspringboot.domain.model.Post;
import fr.origisoft.ecsspringboot.domain.repository.PostRepository;
import fr.origisoft.ecsspringboot.infrastructure.persistence.InMemoryPostRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CreatePostUseCaseTest {
    @Test
    void shouldCreatePost() {
        PostRepository postRepository = new InMemoryPostRepository();
        CreatePostUseCase createPostUseCase = new CreatePostUseCase(postRepository);

        Post post = createPostUseCase.execute(new CreatePostCommand("hello"));

        assertNotNull(post.id());
        assertEquals("hello", post.message());
    }
}
