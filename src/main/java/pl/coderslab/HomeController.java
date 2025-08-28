package pl.coderslab;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {

    private final CartoonRepository cartoonRepository;

    @Value("${coderslab.course.name}")
    private String courseName;

    private TaboretService  taboretService;

    public HomeController(CartoonRepository cartoonRepository, TaboretService taboretService) {
        this.cartoonRepository = cartoonRepository;
        this.taboretService = taboretService;
    }

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello World " + courseName;
    }

    @PostMapping("/s1")
    public ResponseEntity<String> statusWithResponseEntity9() {
        throw  new RuntimeException("some exception");
    }

    @GetMapping("/cartoon/{id}")
    public Cartoon find(@PathVariable Long id){
        return cartoonRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(String.format("cartoon %d not found", id)));
    }

    @GetMapping("/cartoon-err/{id}")
    public Cartoon findCartoon(@PathVariable Long id) {
        return cartoonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }


    @GetMapping("/cartoon-err-code/{id}")
    public Cartoon findCartoonCode(@PathVariable Long id) {
        return cartoonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found", "MY_ERROR_CODE"));
    }

}