package org.faillon.documentsapi.application.port.in.author;

import org.faillon.documentsapi.common.dto.author.AuthorResponse;

import java.util.List;

public interface GetAllAuthorsUseCase {

    List<AuthorResponse> getAllAuthors();
}
