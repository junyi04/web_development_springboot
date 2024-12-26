package me.kangjunyi.springbootdeveloper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
    1. 테스트 코드 개념 익히기
        테스트 코드는 작성한 코드가 의도대로 잘 동작하고 예상치 못한 문제가 없는지 확인할 목적으로
            작성하는 코드.
        보톤 테스트 코드 관련 공부는 본 개발 공부를 하느라 미루는 경우가 많은데, 좀 익숙해지면
            솔직히 이거 완전 좋음
        
        장점 :
            1) 유지 보수에 편리
            2) 코드 수정 시 기존 기능이 제대로 작동하지 않을까봐 걱정할 필요 없음

        테스트 코드란?
            테스트 코드는 test 디렉토리에서 작업함 -> 프로젝트 생성시 src 의 하위 폴더,
                main과 같은 라인에 test 폴더가 있음.
            테스트 코드에는 다양한 패턴이 있는데, give-when-then 패턴 사용 예정
                1) given : 테스트 실행 준비하는 단계
                2) when : 테스트를 진행하는 단계
                3) then : 테스트 결과를 검증하는 단계

            ex) 새로운 메뉴를 지정하는 코드를 테스트한다고 가정했을 떄의
            테스트 코드를 given, when, then 으로 구분해서 예시

            @Display("새로운 메뉴를 저장한다.")
            @Test
            public void savedMenuTest() {
                // 1. given : 메뉴를 저장하기 위한 준비 과정
                final Sting name = "아메리카노";
                final int price = 2000;
                final Menu americano = new Menu(name, price);

                // 2. when : 실제로 메뉴를 저장
                final long savedId = menuService.save(americano)

                // 3. then : 메뉴가 잘 추가되었는지 검증
                final Menu savedMenu = menuService.findById(saveId).get();
                assertThat(savedMenu.getName()).isEqualTo(name);
                assertThat(savedMenu.getPrice()).isEqualTo(price);
            }
    
    이상의 코드를 확인하면 세 부분으로 나뉘어져 있음. 메뉴를 저장하기 위해 준비하는 과정인 given, 
    실제로 메뉴를 저장하는 when, 메뉴가 잘 추가되었는지 확인하는 then.
    
    2. 스프링 부트 3와 테스트
        스트링 부트는 애플리케이션을 테스트하기 위한 도구와 애너테이션을 제공.
        spring-boot-starter-test 스타터에 테스트를 위한 도구가 모여있음.
        
        스프링 부트 스타터 테스트 목록 (중 많이 쓰이는 두 개만 소개)
            1) JUnit : 자바 프로ㅗ그래밍용 단위 테스트 프레인 워크
            2) AssertJ : 검증 중인 어써션을 작성하는데 사용되는 라이브러리
            
            Junit 이란?
                자바 언어를 위한 *단위 테스트 프레임
                    *단위 테스트 : 작성한 코드가 의도대로 작동하는지 작은 단위로 검증함을 의미
                        보통 그 단위는 메서드 기준
                JUnit 을 사용하면 단위 테스트의 결과가 직관적으로 나오는 편

                특징 :
                    1) 테스트 방식을 구분할 수 있는 애너테이션 제공
                    2) @Test 애너테이션으로 메서드를 호출할 때마다 새 인스턴스를 생성,
                        독립테스트가 가능
                    3) 예상관과를 검증하는 assertion 메서드 제공
                    4) 사용 방법이 단순, 테스트 코드 작성 시간이 적습니다
                    5) 자동 실행, 자체 결과를 확인하고 실패한 지점을 보여줌
                    
    test/java 폴더에 JUnitTest.java 폴더 새성
 */
@SpringBootApplication
public class SpringBootDeveloperApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootDeveloperApplication.class, args);
    }
}