package co.in.nextgencoder.learningpath.service;

import co.in.nextgencoder.learningpath.domain.Section;
import co.in.nextgencoder.learningpath.reporitory.SectionRepository;
import co.in.nextgencoder.learningpath.service.dto.SectionDTO;
import co.in.nextgencoder.learningpath.service.mapper.SectionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionService {

    private static final Logger log = LoggerFactory.getLogger(SectionService.class);

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private SectionMapper sectionMapper;


    public SectionDTO save(SectionDTO section) {
        Section savedSection = sectionMapper.toSection(section);
        return sectionMapper.toSectionDTO(sectionRepository.save(savedSection));
    }

    public List<SectionDTO> getAllSections() {
        return sectionRepository.findAll()
                .stream()
                .map(sectionMapper::toSectionDTO)
                .toList();
    }
}