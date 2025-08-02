package co.in.nextgencoder.learningpath.service;

import co.in.nextgencoder.learningpath.domain.TopicItem;
import co.in.nextgencoder.learningpath.domain.UserTopicProgress;
import co.in.nextgencoder.learningpath.reporitory.TopicItemRepository;
import co.in.nextgencoder.learningpath.reporitory.UserTopicProgressRepository;
import co.in.nextgencoder.learningpath.service.dto.UserDTO;
import co.in.nextgencoder.learningpath.service.dto.UserTopicProgressDTO;
import co.in.nextgencoder.learningpath.service.exception.BadRequestException;
import co.in.nextgencoder.learningpath.service.mapper.AppUserMapper;
import co.in.nextgencoder.learningpath.service.mapper.UserTopicProgressMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserTopicProgressService {

    private static final Logger log = LoggerFactory.getLogger(UserTopicProgressService.class);

    @Autowired
    private UserService userService;

    @Autowired
    private AppUserMapper appUserMapper;

    @Autowired
    private TopicItemRepository topicItemRepository;

    @Autowired
    private UserTopicProgressRepository userTopicProgressRepository;

    @Autowired
    private UserTopicProgressMapper userTopicProgressMapper;


    public UserTopicProgressDTO saveProgress(UserTopicProgressDTO userTopicProgressDTO) {
        UserDTO userDTO = userService.getUserProfile();

        TopicItem topicItem = topicItemRepository.findById(userTopicProgressDTO.getTopicItemId())
                .orElseThrow(() -> new BadRequestException("TopicItem not found"));

        UserTopicProgress progress = userTopicProgressRepository
                .findByUserIdAndTopicItemId(userDTO.getId(), topicItem.getId())
                .orElse(new UserTopicProgress());

        progress.setUser(appUserMapper.toAppUser(userDTO));
        progress.setTopicItem(topicItem);
        progress.setCompletedDate(LocalDate.now());
        progress.setStatus(userTopicProgressDTO.getStatus());
        return userTopicProgressMapper.toUserTopicProgressDTO(
                userTopicProgressRepository.save(progress)
        );
    }
}