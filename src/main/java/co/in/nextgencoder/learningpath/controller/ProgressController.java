package co.in.nextgencoder.learningpath.controller;

import co.in.nextgencoder.learningpath.service.TopicService;
import co.in.nextgencoder.learningpath.service.dto.MinDivisionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/progress")
public class ProgressController {
    private static final Logger log = LoggerFactory.getLogger(ProgressController.class);

    @Autowired
    private TopicService topicService;

    @GetMapping
    public ResponseEntity<List<MinDivisionDTO>> getProgressItems() {
        List<MinDivisionDTO> topics = topicService.getTotalProgressList();
        return ResponseEntity.ok(topics);
    }
}
