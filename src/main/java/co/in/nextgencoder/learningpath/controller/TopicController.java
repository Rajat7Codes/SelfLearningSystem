package co.in.nextgencoder.learningpath.controller;

import co.in.nextgencoder.learningpath.service.TopicService;
import co.in.nextgencoder.learningpath.service.dto.TopicDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin/topics")
public class TopicController {

    private static final Logger log = LoggerFactory.getLogger(TopicController.class);

    @Autowired
    private TopicService topicService;

    @PostMapping
    public ResponseEntity<TopicDTO> createTopic(@RequestBody TopicDTO topic) {
        return ResponseEntity.ok(topicService.save(topic));
    }

    @GetMapping
    public ResponseEntity<List<TopicDTO>> getAllTopics() {
        return ResponseEntity.ok(topicService.getAllTopics());
    }
}
