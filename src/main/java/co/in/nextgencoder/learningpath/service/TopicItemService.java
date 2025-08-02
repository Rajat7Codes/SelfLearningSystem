package co.in.nextgencoder.learningpath.service;

import co.in.nextgencoder.learningpath.domain.TopicItem;
import co.in.nextgencoder.learningpath.reporitory.TopicItemRepository;
import co.in.nextgencoder.learningpath.service.dto.TopicItemDTO;
import co.in.nextgencoder.learningpath.service.mapper.TopicItemMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicItemService {

    private static final Logger log = LoggerFactory.getLogger(TopicItemService.class);

    @Autowired
    private TopicItemRepository topicItemRepository;

    @Autowired
    private TopicItemMapper topicItemMapper;


    public TopicItemDTO save(TopicItemDTO topicItem) {
        TopicItem savedTopicItem = topicItemMapper.toTopicItem(topicItem);
        return topicItemMapper.toTopicItemDTO(topicItemRepository.save(savedTopicItem));
    }

    public List<TopicItemDTO> getAllTopicItems() {
        return topicItemRepository.findAll()
                .stream()
                .map(topicItemMapper::toTopicItemDTO)
                .toList();
    }
}