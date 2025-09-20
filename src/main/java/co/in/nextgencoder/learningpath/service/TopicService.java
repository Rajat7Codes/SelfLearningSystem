package co.in.nextgencoder.learningpath.service;

import co.in.nextgencoder.learningpath.domain.*;
import co.in.nextgencoder.learningpath.domain.enumeration.ProgressStatus;
import co.in.nextgencoder.learningpath.reporitory.*;
import co.in.nextgencoder.learningpath.service.dto.*;
import co.in.nextgencoder.learningpath.service.exception.BadRequestException;
import co.in.nextgencoder.learningpath.service.mapper.ProgressMapper;
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

    @Autowired
    private ProgressMapper progressMapper;


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

    public List<MinDivisionDTO> getTotalProgressList() {
        List<MinDivisionDTO> divisionList = divisionRepository.findAllBySectionIdOrderByAlignmentAsc(1)
                .stream().map(progressMapper::toMinDivisionDTO).toList();

        for( MinDivisionDTO division : divisionList) {
            log.info("Division => "+division.getTitle());
            List<MinTopicDTO> topicList = topicRepository.findAllByDivisionIdOrderByAlignmentAsc(division.getId())
                    .stream().map(progressMapper::toMinTopicDTO).toList();

            for( MinTopicDTO topic : topicList) {
                log.info("Topic => "+topic.getTitle());
                List<ProgressTopicItemDTO> topicItemList = topicItemRepository.findAllByTopicIdOrderByAlignmentAsc(topic.getId())
                        .stream().map(progressMapper::toProgressTopicItemDTO).toList();

                for( ProgressTopicItemDTO topicItem : topicItemList) {
                    log.info("Topic Item => "+topicItem.getTitle());
                    Optional<UserTopicProgress> userTopicProgress = userTopicProgressRepository.findByUserIdAndTopicItemId(
                            userService.getUserProfile().getId(),
                            topicItem.getId()
                    );

                    if( userTopicProgress.isPresent() && userTopicProgress.get().getStatus() == ProgressStatus.COMPLETED) {
                        topicItem.setCompleted(true);
                        topic.setCompletedTopicItems(topic.getCompletedTopicItems() + 1);
                    } else {
                        topicItem.setCompleted(false);
                    }
                }

                topic.setTotalTopicItems((long) topicItemList.size());
                topic.setCompleted(topic.getTotalTopicItems() - topic.getCompletedTopicItems() == 0);
                topic.setTopicItems(topicItemList);

                division.setCompletedTopics(division.getCompletedTopics() +
                        (topic.getCompleted() ? 1 : 0));
                division.setTotalTopics(division.getTotalTopics() + 1);
            }

            division.setCompleted(division.getTotalTopics() - division.getCompletedTopics() == 0);
            division.setTopics(topicList);
        }

        return divisionList;
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