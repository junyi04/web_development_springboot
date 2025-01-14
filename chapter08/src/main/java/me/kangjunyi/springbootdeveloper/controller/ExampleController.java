package me.kangjunyi.springbootdeveloper.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Controller     // 컨트롤러 명시
public class ExampleController {

    @GetMapping("/thymeleaf/example")
    public String thymeleafExample(Model model) { //import org.springframework.ui.Model
        // 뷰(우리가 보는 화면)로 데이터를 넘겨주는 모델 객체
        Person examplePerson = new Person();
        // id 1L, 이름에 홍길동, 나이가 11 취미에 운동, 독서 입력하세요.
        examplePerson.setId(1L);
        examplePerson.setName("홍길동");
        examplePerson.setAge(11);
        examplePerson.setHobbies(List.of("운동", "독서"));

        //이상에서 Person 클래스의 인스턴스에 값 대입
        model.addAttribute("person", examplePerson);    // Person 객체를 "person" 키에 저장
        model.addAttribute("today", LocalDate.now());

        return "example";       // example.html이라는 뷰를 조회합니다.
    }

    @Setter
    @Getter
    class Person {
        private long id;
        private String name;
        private int age;
        private List<String> hobbies;
    }
}
