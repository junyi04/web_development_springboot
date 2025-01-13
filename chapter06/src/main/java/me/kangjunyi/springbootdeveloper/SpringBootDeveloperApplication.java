package me.kangjunyi.springbootdeveloper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
    1. 사전 지식 : API와 REST API
        API : 프로그램 간에 상호 작영하기 위한 매개체

        식당으로 알아보는 API
            여러분이 손님인데, 식당 들어갔어요 -> 점원에게 요리를 주문합니다(주방으로 달려가서 요리를 주문하는게 아니라).
            그리고 점원은 주방에 가서 요리를 만들어달라고 요청합니다.

                  요 청        요 청
            손님 <-----> 점원 <-----> 주방
                  응 답        응 답

            의 형태를 띄고 잇는데, 여기서 손님은 클라이언트(client), 주반에서 일하는 요리사를 서버(server)라고 생각하시면 됩니다.
            그리고 중간에 있는 점원을 API라고 보시면 됩니다. -> '매게체'라고 한 점 기억해주세요.

            우리는 웹 사이트의 주소를 입력해서 '구글 메인 화면을 보여줘'라고 요청하면, API는 이 요청을 받아서 서버에게 전달.
            그러면 서버는 API가 준 요청을 처리해 구성해서 이것을 다시 API로 전달하고, API는 최종 결과물을 브라우저에
            보내주면 화면을 볼 수 있게 됩니다.
                이처럼 API는 클라이언트의 요청을 서버에 잘 전달하고, 서버의 결과물을 클라이언트에게 잘 돌려주는 역할을 합니다.

                그렇가면 REST API란?

            웹의 장점을 최대한 활용하는 형태라고 알려진 REST API
                Representational State Transfer을 줄인 표현으로, 자원을 이름으로 구분해 자원의 사태를 주고 받는 API 방식
                
                URL의 설계 방식
                
                특징
                    REST API는 서버/클라이언트 구조, 무상태, 캐시 처리 기능, 계층화, 일관성과 같은 특징.
                    
                장점 :
                    URL만 보고소 무슨 행동을 하는 API인지 명확하게 알 수 잇음.
                    무상태 특징으로 인해 클라이언트와 서버의 역할이 명확하게 분리됨.
                    HTTP 표준을 사ㅛㅇㅇ하는 모든 플랫폼에서 사용 가능

                단점 :
                    HTTP 메서드, 즉 GET, POST와 같은 방식의 개수에 제한이 있음.
                    설계를 위한 공식적으로 제공되는 쵸준 규약이 없음.

                장단점을 고려했을 때, 주소와 HTTP 메서드만 보고 요청의 내용을 파악할 수 있다는 장점으로
                REST하게 디자인한 API응 보고 RESTful API라고 부르기도 하는 편.

                REST API를 사용하는 방법
                    규칙 1. URL에는 동시에 쓰지 말고, 자원을 표시한다.
                        * 자원 : 가져오는 데이터를 의미. 예를 들어 학생 중에 id가 1인 학생의 정보를 가져오는 URL은
                        1) 예시 : /student/1
                        2) 예시 : /get-student?student_id=1
                    과 같은 방식으로 설계할 수 있는데,

                    이 증 다 REST API에 맞는 방식은 1)예시에 해당함. 2)예시의 경우 자원이 아닌 다른 표현을 섞어 사용했끼 떄문
                    (get).
                    동사를 사용해서 생기게 되는 추후의 문제점 예시 -> 데이터를 요청하는 URL을 설계할 때
                        A 개발자는 get, B 개반자는 show를 쓰면 get-student, show-data 등으로 협의가 이루어지지 않은 설계가
                        될 가능성이 있다.
                    '기능/행위'에 해당하지만 RESTful API에서는 동사를 사용하지 않습니다.

                    규칙 2. 동사는 HTTP 메서드로
                        HTTP 메서드 : 서버에 요청을 하는 방법을 나누는 것. -> POST, GET, PUT, DELETE
                            만들고, 읽고, 업데이트하고, 삭제한다 Create  /Read / Update / Delete 라고 해서 CRUD 라고 합니다.
                        예를 들어, 블로그에 글을 쓰는 설계를 한다고 가정했을 떄

                            1) id가 1인 블로그 글을 '조회 하는 API : GET/articles/1
                            2) 블로그 글을 '추가'하는 API         : POST/articles
                            3) 블로그 글을 '수정'하는 API         : PUT/articles
                            4) 블로그 글을 '삭제'하는 API         : DELETE/articles

                        * GET / POST 등은 URL에 입력하는 값이 아니라 내부적으로 처리하는 방식을 미리 정하는 것으로
                        실제로 HTTP 메서드는 내부에서 서로 다른 함수로 처리하는데 대놓고 적는 일은 잘 없습니다.

                        이외에도 '/'는 계층 관계를 나타내는 데 사용하거나, 밑줄 대신 하이픈을 사용하거나, 자원의 종류가
                        컬렉션인지 도큐먼트인지에 따라 단수, 복수를 나누거나 하는 드의 규칙이 있지만 마찬가지로 추후 설명 예정.

    2. 블로그 개발을 위한 엔티티 구성하기
        이제 프로젝트 시작할 예정인데,

        엔티티 구성하겠습니다.
        엔티티와 매핑되는 테이블 구조는 ⬇️
         +-------------------------------------------------------------+
         | 컬럼명  | 자료형        | null 허용 |  키   |       설명       |
         +-------------------------------------------------------------+
         | id     | BIGINT       |     N     | 기본키 | 일련번호, 기본 키 |
         ---------------------------------------------------------------
         | title  | VARCHAR(255) |     N     |       | 게시물의 제목     |
         ---------------------------------------------------------------
         | content| VARCHAR(255) |     N     |       | 내용             |
         +--------------------------------------------------------------+
         
         
         window 키 누르시고 -> postman 검색
         HTTP 메서드 : POST
         URL : http://localhost:8080/api/articles
         BODY : raw -> JSON
         {
            "title": "제목",
            "content": "내용"
         }

         결과값이 Body에 pretty 모드로 결과를 보여줬습니다.
         -> 여기까지 성공했다면 스프링 부트 서버에 저장된 것을 의미
         
         여기까지가 과정이 HTTP 메서드 POST로 서버에 요청을 한 후에 값을 저장하는 과정에 해당.

         이제 크롬켜세요 -> 주소창에
         http://localhost:8080/h2-console

         select * from article
         run 눌러서 쿼리 실행
         h2 데이터 베이스에 저잔된 데이터를 확인할 수 있음.
*/


@SpringBootApplication
public class SpringBootDeveloperApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootDeveloperApplication.class, args);
    }
}
