package me.kangjunyi.springbootdeveloper.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.kangjunyi.springbootdeveloper.domain.Article;
import me.kangjunyi.springbootdeveloper.dto.AddArticleRequest;
import me.kangjunyi.springbootdeveloper.dto.UpdateArticleRequest;
import me.kangjunyi.springbootdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor        // final이 붙거나 @NotNull이 붙은 필드의 생성자 추가
@Service                        // 빈으로 등록
public class BlogService {

    private final BlogRepository blogRepository;

    // 블로그 글 추가 메서드
    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }

    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    // id로 특정 글 조회
    public Article findById(long id) { // 이 경우 결과값은 하나밖에 없기 때문에 리턴 타입이 Article이어야만 합니다. -> id PK라서
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));   // 람다식
    }
    // BlogApiController.java

    // 삭제 메서드 정의
    public void delete(long id) {
        blogRepository.deleteById(id);
    }
    // 컨트롤러로 가면 됩니다. /api/articles/{id}

    @Transactional
    public Article update(long id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));

        // domain -> Article에서 정의한 update()를 사용.
        article.update(request.getTitle(), request.getContent());

        return article;
    }
}
