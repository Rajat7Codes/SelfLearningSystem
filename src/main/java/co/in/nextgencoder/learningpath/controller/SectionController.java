package co.in.nextgencoder.learningpath.controller;

import co.in.nextgencoder.learningpath.reporitory.SectionRepository;
import co.in.nextgencoder.learningpath.service.SectionService;
import co.in.nextgencoder.learningpath.service.dto.SectionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin/sections")
public class SectionController {

    private static final Logger log = LoggerFactory.getLogger(SectionController.class);

    @Autowired
    private SectionService sectionService;

    @PostMapping
    public ResponseEntity<SectionDTO> createSection(@RequestBody SectionDTO section) {
        return ResponseEntity.ok(sectionService.save(section));
    }

    @GetMapping
    public ResponseEntity<List<SectionDTO>> getAllSections() {
        return ResponseEntity.ok(sectionService.getAllSections());
    }
}
