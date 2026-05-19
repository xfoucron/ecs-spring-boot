package fr.origisoft.ecsspringboot.application.usecase;

import fr.origisoft.ecsspringboot.application.command.CreatePostCommand;
import fr.origisoft.ecsspringboot.application.command.FindPostCommand;
import fr.origisoft.ecsspringboot.domain.model.Post;
import fr.origisoft.ecsspringboot.domain.model.PostId;
import fr.origisoft.ecsspringboot.domain.repository.PostRepository;
import fr.origisoft.ecsspringboot.infrastructure.persistence.InMemoryPostRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class FindPostUseCaseTest {
    @Test
    void shouldFindPost() {
        PostRepository postRepository = new InMemoryPostRepository();

        CreatePostUseCase createPostUseCase = new CreatePostUseCase(postRepository);
        FindPostUseCase findPostUseCase = new FindPostUseCase(postRepository);

        PostId postId = createPostUseCase.execute(new CreatePostCommand("hello")).id();
        Post post = findPostUseCase.execute(new FindPostCommand(postId));

        assertNotNull(post.id());
        assertEquals("hello", post.message());
    }
}
