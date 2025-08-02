package co.in.nextgencoder.learningpath.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/hello")
public class HelloController {


    private static final Logger log = LoggerFactory.getLogger(HelloController.class);

    @GetMapping
    public ResponseEntity<String> sayHello() {

        return ResponseEntity.ok("Hello");
    }
}
