package org.faillon.documentsapi.adapter.in.events;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.faillon.documentsapi.application.port.in.author.DeleteAuthorUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaDeleteAuthorConsumer {

    Logger logger = LoggerFactory.getLogger(KafkaDeleteAuthorConsumer.class);
    private final DeleteAuthorUseCase deleteAuthorUseCase;

    @KafkaListener(topics = "authors-deleted", groupId = "authors-group")
    public void consumeMessage(String authorIdMessage) {
        logger.info("Consuming Message Deleting Author with ID: " + authorIdMessage);
        deleteAuthorUseCase.deleteAuthor(Long.parseLong(authorIdMessage));
    }
}
