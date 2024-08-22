package org.faillon.documentsapi.adapter.out.persistence.author;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.faillon.documentsapi.application.port.out.author.*;
import org.faillon.documentsapi.common.dto.author.AuthorResponse;
import org.faillon.documentsapi.common.dto.author.CreateAuthorRequest;
import org.faillon.documentsapi.common.dto.author.UpdateAuthorRequest;
import org.faillon.documentsapi.common.exception.author.AuthorNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthorPersistenceAdapter implements GetAllAuthorsPort, GetAuthorPort,
        UpdateAuthorPort, CreateAuthorPort, DeleteAuthorPort {

    Logger logger = LoggerFactory.getLogger(AuthorPersistenceAdapter.class);

    private final AuthorRepository authorRepository;
    private Function<AuthorEntity, AuthorResponse> mapperToDto = AuthorResponse::new;

    @Override
    public List<AuthorResponse> getAllAuthors() {

        List<AuthorEntity> entityList = authorRepository.findAll();

        //transform from entity list to DTO list
        List<AuthorResponse> authorResponseList = entityList.stream()
                .map(mapperToDto)
                .toList();

        return authorResponseList;
    }

    @Override
    public AuthorResponse getAuthor(Long authorId) {
        Optional<AuthorEntity> entity = authorRepository.findById(authorId);

        if(entity.isEmpty()){
            logger.error("Author not found");
            throw new AuthorNotFoundException(authorId);
        }

        //transform from entity to DTO
        AuthorResponse response = mapperToDto.apply(entity.get());
        return response;
    }

    @Override
    public AuthorResponse updateAuthor(Long authorId, UpdateAuthorRequest request) {
        getAuthor(authorId);

        //transform from dto to entity
        AuthorEntity entity =  AuthorMapper.updateRequestToEntity(request);
        entity.setId(authorId);
        authorRepository.save(entity);

        AuthorResponse response = AuthorResponse.builder()
                .id(authorId)
                .name(request.getName())
                .lastName(request.getLastName())
                .build();

        return response;
    }

    @Override
    public AuthorResponse createAuthor(CreateAuthorRequest authorRequest) {

        AuthorEntity authorEntity = AuthorMapper.createRequestToEntity(authorRequest);
        AuthorEntity createdEntity = authorRepository.save(authorEntity);

        AuthorResponse response = AuthorResponse.builder()
                .id(createdEntity.getId())
                .name(createdEntity.getName())
                .lastName(createdEntity.getLastName())
                .build();

        return response;
    }

    @Override
    public void deleteAuthor(Long authorId) {
        getAuthor(authorId);

        authorRepository.deleteById(authorId);
    }
}
