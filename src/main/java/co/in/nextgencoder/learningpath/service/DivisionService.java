package co.in.nextgencoder.learningpath.service;

import co.in.nextgencoder.learningpath.domain.Division;
import co.in.nextgencoder.learningpath.domain.Section;
import co.in.nextgencoder.learningpath.reporitory.DivisionRepository;
import co.in.nextgencoder.learningpath.reporitory.SectionRepository;
import co.in.nextgencoder.learningpath.service.dto.DivisionDTO;
import co.in.nextgencoder.learningpath.service.exception.BadRequestException;
import co.in.nextgencoder.learningpath.service.mapper.DivisionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DivisionService {

    private static final Logger log = LoggerFactory.getLogger(DivisionService.class);

    @Autowired
    private DivisionRepository divisionRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private DivisionMapper divisionMapper;


    public DivisionDTO save(DivisionDTO division) {
        Division savedDivision = divisionMapper.toDivision(division);

        Optional<Section> sectionOptional = sectionRepository.findById(division.getSectionId());
        if(sectionOptional.isEmpty()) {
            throw new BadRequestException("Section with id "+division.getSectionId()+" not found.");
        }

        savedDivision.setSection(sectionOptional.get());
        return divisionMapper.toDivisionDTO(divisionRepository.save(savedDivision));
    }

    public List<DivisionDTO> getAllDivisions() {
        return divisionRepository.findAll()
                .stream()
                .map(divisionMapper::toDivisionDTO)
                .toList();
    }
}