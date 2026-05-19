package fr.origisoft.ecsspringboot.infrastructure.persistence;

import fr.origisoft.ecsspringboot.domain.model.Post;
import fr.origisoft.ecsspringboot.domain.model.PostId;
import fr.origisoft.ecsspringboot.domain.repository.PostRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Component
public class InMemoryPostRepository implements PostRepository {
    private final Map<UUID, Post> posts = new HashMap<>();

    @Override
    public Optional<Post> findById(PostId postId) {
        return Optional.ofNullable(posts.get(postId.id()));
    }

    @Override
    public Post save(Post post) {
        posts.put(post.id().id(), post);

        return post;
    }

    @Override
    public void delete(PostId postId) {
        posts.remove(postId.id());
    }
}
