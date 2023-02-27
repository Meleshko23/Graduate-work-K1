package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.Comment;

import java.util.List;
import java.util.Optional;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findCommentsByAdsId(Integer adsId);

    @Query(value = "select comments.* from Comments, ads where ads.id = ?1 and Comments.id = ?2", nativeQuery = true)
    Optional<Comment> findAdsComment(Integer adPk, Integer id);

//    Optional<Comment> findCommentByIdAndAuthorId(Integer commentId, Integer authorId);
}