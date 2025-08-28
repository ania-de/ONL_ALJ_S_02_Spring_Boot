package pl.coderslab;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {

    @Value("${coderslab.course.name}")
    private String courseName;

    private TaboretService  taboretService;

    public HomeController(TaboretService taboretService) {
        this.taboretService = taboretService;
    }

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello World " + courseName;
    }
    @GetMapping("/taborets")
    public List<Taboret> taborets(){
        return taboretService.findAll();
    }
}