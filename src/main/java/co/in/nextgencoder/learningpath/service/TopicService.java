package co.in.nextgencoder.learningpath.service;

import co.in.nextgencoder.learningpath.domain.*;
import co.in.nextgencoder.learningpath.domain.enumeration.ProgressStatus;
import co.in.nextgencoder.learningpath.reporitory.*;
import co.in.nextgencoder.learningpath.service.dto.ActiveTopicDTO;
import co.in.nextgencoder.learningpath.service.dto.ActiveTopicItemDTO;
import co.in.nextgencoder.learningpath.service.dto.TopicDTO;
import co.in.nextgencoder.learningpath.service.exception.BadRequestException;
import co.in.nextgencoder.learningpath.service.mapper.TopicItemMapper;
import co.in.nextgencoder.learningpath.service.mapper.TopicMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

    private static final Logger log = LoggerFactory.getLogger(TopicService.class);

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private DivisionRepository divisionRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private TopicItemRepository topicItemRepository;

    @Autowired
    private TopicItemMapper topicItemMapper;

    @Autowired
    private TopicMapper topicMapper;

    @Autowired
    private UserTopicProgressRepository userTopicProgressRepository;

    @Autowired
    private UserService userService;


    public TopicDTO save(TopicDTO topic) {
        Topic savedTopic = topicMapper.toTopic(topic);
        return topicMapper.toTopicDTO(topicRepository.save(savedTopic));
    }

    public List<TopicDTO> getAllTopics() {
        return topicRepository.findAll()
                .stream()
                .map(topicMapper::toTopicDTO)
                .toList();
    }

    public List<ActiveTopicDTO> findByDeadlineDate(LocalDate date) {
        List<Topic> topics = topicRepository.findByDeadlineDateLessThanEqual(date);

        List<ActiveTopicDTO> activeTopicList = new ArrayList<>();
        for (Topic topic : topics) {
            ActiveTopicDTO activeTopicDTO = new ActiveTopicDTO();
            activeTopicDTO.setId(topic.getId());
            activeTopicDTO.setTitle(topic.getTitle());
            activeTopicDTO.setDeadlineDate(topic.getDeadlineDate());

            Optional<Division> divisionOptional = divisionRepository.findById(topic.getDivision().getId());
            if (divisionOptional.isEmpty()) {
                throw new BadRequestException("Division not found");
            }
            activeTopicDTO.setDivisionTitle(divisionOptional.get().getTitle());

            Optional<Section> sectionOptional = sectionRepository.findById(
                    divisionOptional.get().getSection().getId()
            );
            if (sectionOptional.isEmpty()) {
                throw new BadRequestException("Section not found");
            }
            activeTopicDTO.setSectionTitle(sectionOptional.get().getTitle());

            activeTopicDTO.setTopicItemList(new ArrayList<>());
            List<TopicItem> topicItemList = topicItemRepository.findAllByTopicIdOrderByAlignmentAsc(topic.getId());
            for (TopicItem topicItem : topicItemList) {
                ActiveTopicItemDTO topicItemDTO = topicItemMapper.toActiveTopicItemDTO(topicItem);
                Optional<UserTopicProgress> userTopicProgress = userTopicProgressRepository.findByUserIdAndTopicItemId(
                        userService.getUserProfile().getId(),
                        topicItemDTO.getId()
                );
                ProgressStatus status = userTopicProgress.isPresent() ? userTopicProgress.get().getStatus() : ProgressStatus.IN_PROGRESS;
                topicItemDTO.setProgressStatus(
                        status
                );
                activeTopicDTO.getTopicItemList().add(topicItemDTO);
            }

            activeTopicList.add(activeTopicDTO);
        }

        return activeTopicList;
    }
}