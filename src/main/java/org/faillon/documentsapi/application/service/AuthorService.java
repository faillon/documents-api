package org.faillon.documentsapi.application.service;

import lombok.RequiredArgsConstructor;
import org.faillon.documentsapi.application.port.in.author.*;
import org.faillon.documentsapi.application.port.out.author.*;
import org.faillon.documentsapi.common.dto.author.AuthorResponse;
import org.faillon.documentsapi.common.dto.author.CreateAuthorRequest;
import org.faillon.documentsapi.common.dto.author.UpdateAuthorRequest;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService implements GetAllAuthorsUseCase, GetAuthorUseCase ,
        UpdateAuthorUseCase, CreateAuthorUseCase, DeleteAuthorUseCase {

    private final GetAllAuthorsPort getAllAuthorsPort;
    private final GetAuthorPort getAuthorPort;
    private final UpdateAuthorPort updateAuthorPort;
    private final CreateAuthorPort createAuthorPort;

    private final DeleteAuthorPort deleteAuthorPort;

    @Override
    public List<AuthorResponse> getAllAuthors() {
        return getAllAuthorsPort.getAllAuthors();
    }

    @Override
    public AuthorResponse getAuthor(Long id) {
        return getAuthorPort.getAuthor(id);
    }

    @Override
    public AuthorResponse updateAuthor(Long authorId, UpdateAuthorRequest request) {
        return updateAuthorPort.updateAuthor(authorId, request);
    }

    @Override
    public AuthorResponse createAuthor(CreateAuthorRequest authorRequest) {
        return createAuthorPort.createAuthor(authorRequest);
    }

    @Override
    public void deleteAuthor(Long authorId) {
        deleteAuthorPort.deleteAuthor(authorId);
    }
}
