package co.in.nextgencoder.learningpath.controller;

import co.in.nextgencoder.learningpath.service.TopicItemService;
import co.in.nextgencoder.learningpath.service.dto.TopicItemDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin/topic-items")
public class TopicItemController {

    private static final Logger log = LoggerFactory.getLogger(TopicItemController.class);

    @Autowired
    private TopicItemService topicItemService;

    @PostMapping
    public ResponseEntity<TopicItemDTO> createTopicItem(@RequestBody TopicItemDTO topicItem) {
        return ResponseEntity.ok(topicItemService.save(topicItem));
    }

    @GetMapping
    public ResponseEntity<List<TopicItemDTO>> getAllTopicItems() {
        return ResponseEntity.ok(topicItemService.getAllTopicItems());
    }
}
