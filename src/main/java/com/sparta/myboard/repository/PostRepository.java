package com.sparta.myboard.repository;

import com.sparta.myboard.entity.Post;
import com.sparta.myboard.entity.Post;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.metamodel.SingularAttribute;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllPostByOrderByCreatedAtDesc();
    // List<Article> findAllByOrderByCreatedAtDesc();
    Optional<Post> findByIdAndPassword(Long id, String password);
    Boolean existsByIdAndPassword(Long id, String password);

}
