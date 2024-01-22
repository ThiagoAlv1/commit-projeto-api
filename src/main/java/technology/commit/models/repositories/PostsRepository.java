package technology.commit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import technology.commit.models.posts.Posts;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PostsRepository extends JpaRepository<Posts, UUID> {
}
