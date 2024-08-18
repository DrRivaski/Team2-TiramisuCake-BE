package com.softeer.backend.fo_domain.comment.repository;

import com.softeer.backend.fo_domain.comment.domain.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    Page<Comment> findAllByIdLessThanEqualOrderByIdDesc(Integer id, Pageable pageable);
}
