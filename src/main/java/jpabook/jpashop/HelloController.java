package jpabook.jpashop;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String Hello(Model model) {
        model.addAttribute("data", "hello!"); // attributeName은 해당 파일에 있는 변수이름
        return "hello"; // 화면 이름 hello.html과 매핑
    }

}
