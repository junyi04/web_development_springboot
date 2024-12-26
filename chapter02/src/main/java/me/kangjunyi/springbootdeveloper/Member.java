package me.kangjunyi.springbootdeveloper;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)  // 테이블 상에서의 컬럼 명이 id 라는 의미
    private Long id;    // DB 테이블의 'id' 컬럼과 매칭

    @Column(name = "name", nullable = false)    // 테이블 상에서의 컬럼 명이 name 라는 의미
    private String name;    // DB 테이블의 'name' 컬럼과 매칭
    /*
        각 애너테이션의 역할은 lombok 의 경우 java 과정에서 다 설명함.
        현재는 member 라는 이름의 테이블에 접근하는 데 사용하는 객체로 이해
        이후 member 테이블과 Member 클래스를 매핑하는 코드를 작성할 예정
     */
}
