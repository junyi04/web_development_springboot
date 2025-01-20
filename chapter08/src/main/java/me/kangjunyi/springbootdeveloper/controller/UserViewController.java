package me.kangjunyi.springbootdeveloper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserViewController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }
    /*
        POST 여청으로 /login 경로로 들어오면 login() 메서드가 login.html 을,
        마찬가지로 요청으로 /signup 경로로 들어오면 signup() 메서드가 signup.thml 을 만든다.

        resource/templates 에 login.html 만들기
     */
    
    // 로그아웃 관련
}
