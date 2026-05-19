package fr.origisoft.ecsspringboot.domain.repository;

import fr.origisoft.ecsspringboot.domain.model.Post;
import fr.origisoft.ecsspringboot.domain.model.PostId;

import java.util.Optional;

public interface PostRepository {
    Optional<Post> findById(PostId postId);

    Post save(Post post);

    void delete(PostId postId);
}
