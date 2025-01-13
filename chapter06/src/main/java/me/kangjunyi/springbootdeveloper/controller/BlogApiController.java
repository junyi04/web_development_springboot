package me.kangjunyi.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.kangjunyi.springbootdeveloper.domain.Article;
import me.kangjunyi.springbootdeveloper.dto.AddArticleRequest;
import me.kangjunyi.springbootdeveloper.dto.ArticleResponse;
import me.kangjunyi.springbootdeveloper.dto.UpdateArticleRequest;
import me.kangjunyi.springbootdeveloper.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BlogApiController {

    private final BlogService blogService;
    
    // HTTP 메서드가 POST 일 때 전달받은 URL 과 동일하면 지금 정의하는 메서드와 매핑 
    @PostMapping("/api/articles")
    // @ResponseBody 로 요청 본문 값 매핑
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) {
        Article savedArticle = blogService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);
    }
    /*
        1. @RestController : 클래스에 붙이면 HTTP 응답으로 객체 데이터를 "JSON" 형식으로 반환
        2. @PostMapping() : HTTP 메서드가 POST일 때 요청 받은 URL과 동일한 메서드와 매핑.
            지금의 경우 /api/articles는 addArticle() 메서드와 매핑.
        3. @RequestBody : HTTP 요청을 할 때, 응답에 해당하는 값을 @RequestBody 애너테이션이 붙은
            대상 객체는 AddArticleRequest에 매핑.
        4. ResponseEntity.status().body()는 응답 코드로 201, 즉, Created를 응답하고
            테이블에 저장된 객체를 반환합니다.

        200 OK : 요청이 성공적으로 수행되었음
        201 Created : 요청이 성공적으로 수행되었고, 새로운 리소스가 생성되었음
        400 Bad Request : 요청 값이 잘못되어 요청에 실패했음
        403 Forbidden : 권한이 없어 요청에 실패했음
        404 Not Found : 요청 값으로 찾은 리소스가 없어 요청에 실패했음
        500 Internal Server Error : 서버 상에 문제가 있어 요청에 실패했음
        
        API가 잘 동작하는 테스트 하나 해볼 예정
        
        SQL statement 입력창에
        select * from article
        
            test 폴더 내에 BlogApiControllerTest.java를 만들기 위한 방법
            파일 내에 들어와서 public class BlogApiController 표기된 부분으로 들어가서
            클래스명 클릭 + alt + enter -> create test가 있었습니다.
     */

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {
        List<ArticleResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();

        return ResponseEntity.ok().body(articles);
    }
    /*
        /api/articles GET 요청이 들어오면 글 전체를 조회하는 findAll() 메서드를 호출
        -> 다음 응답용 객체인 ArticleReponse로 파싱해서 body에 담아
        클라이언트에게 전송합니다 -> 해단 코드에서는 stream을 적용했습니다. -> 추후 설명

        * stream : 여러 데이터가 모여 있는 컬렉션을 간편하게 처리하기 위해서 사용하는 기능
            자바 8에 추가
     */

    @GetMapping("/api/articles/{id}")
    // URL 결로에서 값을 추출
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id) { // URL에서 {id}에 해당하는 값이 id로 들어옴
        Article article = blogService.findById(id);

        return ResponseEntity.ok()
                .body(new ArticleResponse(article));
    }
    /*
        @PathVariable : URL에서 값을 가져오는 애너테이션,
            /api/articles/3 GET 요청을 받으면 id에 3이 argument로 들어오게 됩니다.
            그리고 이 값은 바로 전에 만든 서비스 클래스의 findById() 매서드로 넘어가서 3번 블로그
            글을 찾아봅니다. 그리고 그 글을 찾으면 3번 글의 정보(제목/내용)를 body에 담아서
            웹브라우저로 가져고 옵니다.
     */

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable long id) {
        blogService.delete(id);

        return ResponseEntity.ok()
                .build();
    }
    /*
        @PathVariable 통해서 {id}에 해당하는 값이 들어옵니다.

        POSTMAN

        GET http://localhost:8080/api/articles

        조회했을 때 저희가 작성한 data.sql이 적용된
        제목1부터 내용3까지의 JSON 데이터가 있는지 확인하고,
        거기서 특정 아이디의 데이터를 삭제하겠습니다.

        조회 성공하셨으면
        DELETE로 HTTP 메서드로 바꿔주고
        http://localhost:8080/api/articles/1 하고 Send 버튼 누릅니다.

        GET으로 HTTP 메서드 바꿔주고
        http://localhost:8080/api/articles/1 -> 이건 지워졌기 때문에 조회 x
        
        http://localhost:8080/api/articles -> 얘로 도회해서 전체 글 목록이 줄었는지 확인
     */

    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable long id, @RequestBody UpdateArticleRequest request) {
        Article updateArticle = blogService.update(id, request);

        return ResponseEntity.ok().body(updateArticle);
    }
    /*
        /api/articles/{id} PUT 요청이 드어오면 Request Body 정보가 request 로 넘어옵니다.
        그리고 다시 서비스 클래스의 update() 메서드에 id와 request 를 넘겨줍니다.
        응답 값은 body 에 담아 전송합니다.
     */
}
