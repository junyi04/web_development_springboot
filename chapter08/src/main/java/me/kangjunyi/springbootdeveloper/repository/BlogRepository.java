package me.kangjunyi.springbootdeveloper.repository;

import me.kangjunyi.springbootdeveloper.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {
}
