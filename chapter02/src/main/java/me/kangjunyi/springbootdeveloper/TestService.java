package me.kangjunyi.springbootdeveloper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    @Autowired
    MemberRepository memberRepository;  // 1. 빈 주입

    public List<Member> getAllMembers() {
        return memberRepository.findAll();  // 2. 멤버 목록 얻기  // 왜 finAll() 인가에 주목
    }
    /*
        1. MemberRepository 라는 변수를 주입 받은 후에,
        2. findAll() 메서드를 호출해서 멤버 테이블에 지정된 멤버 목록을 가져옴
            -> 개발자가 직접적으로 정의한 메서드가 아니라 JpaRepository 라는 springboot 관련 클래스에서
            메서드를 상속 받아서 사용하는 경우
     */
}
