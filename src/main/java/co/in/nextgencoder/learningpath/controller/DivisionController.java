package co.in.nextgencoder.learningpath.controller;

import co.in.nextgencoder.learningpath.service.DivisionService;
import co.in.nextgencoder.learningpath.service.dto.DivisionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin/divisions")
public class DivisionController {

    private static final Logger log = LoggerFactory.getLogger(DivisionController.class);

    @Autowired
    private DivisionService divisionService;

    @PostMapping
    public ResponseEntity<DivisionDTO> createDivision(@RequestBody DivisionDTO division) {
        return ResponseEntity.ok(divisionService.save(division));
    }

    @GetMapping
    public ResponseEntity<List<DivisionDTO>> getAllDivisions() {
        return ResponseEntity.ok(divisionService.getAllDivisions());
    }
}
