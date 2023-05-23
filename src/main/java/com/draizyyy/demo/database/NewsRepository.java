package com.draizyyy.demo.database;

import com.draizyyy.demo.model.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findAll();
    Optional<News> findById(Long id);
    News save(News news);
}
