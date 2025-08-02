package co.in.nextgencoder.learningpath.controller;

import co.in.nextgencoder.learningpath.domain.UserTopicProgress;
import co.in.nextgencoder.learningpath.service.TopicService;
import co.in.nextgencoder.learningpath.service.UserTopicProgressService;
import co.in.nextgencoder.learningpath.service.dto.ActiveTopicDTO;
import co.in.nextgencoder.learningpath.service.dto.UserTopicProgressDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/tasks")
public class TaskController {
    private static final Logger log = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private TopicService topicService;

    @Autowired
    private UserTopicProgressService userTopicProgressService;

    @GetMapping("/by-date")
    public ResponseEntity<List<ActiveTopicDTO>> getTopicsByDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<ActiveTopicDTO> topics = topicService.findByDeadlineDate(date);
        return ResponseEntity.ok(topics);
    }

    @PostMapping("/mark-complete")
    public ResponseEntity<UserTopicProgressDTO> getTopicsByDate(@RequestBody UserTopicProgressDTO userTopicProgressDTO) {
        UserTopicProgressDTO savedProgress = userTopicProgressService.saveProgress(userTopicProgressDTO);
        return ResponseEntity.ok(savedProgress);
    }
}
