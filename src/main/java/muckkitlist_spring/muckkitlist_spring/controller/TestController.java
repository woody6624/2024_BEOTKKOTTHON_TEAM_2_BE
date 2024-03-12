package muckkitlist_spring.muckkitlist_spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping
    public String a(){
        return "a";
    }
}
