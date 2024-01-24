package technology.commit.models.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import technology.commit.models.posts.Posts;
import technology.commit.models.usuario.Usuario;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Long> {
    Page<Posts> findByCriadorPostId(Usuario criadorPostId, Pageable paginacao);
}
