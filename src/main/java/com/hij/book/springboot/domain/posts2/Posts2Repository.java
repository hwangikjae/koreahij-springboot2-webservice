package com.hij.book.springboot.domain.posts2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Posts2Repository extends JpaRepository<Posts2,Long> {
    @Query("SELECT p FROM Posts2 p WHERE p.delyn='N' ORDER BY p.id DESC")

    List<Posts2> findAllDesc();
}
