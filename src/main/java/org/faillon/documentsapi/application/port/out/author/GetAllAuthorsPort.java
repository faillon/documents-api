package org.faillon.documentsapi.application.port.out.author;

import org.faillon.documentsapi.common.dto.author.AuthorResponse;

import java.util.List;

public interface GetAllAuthorsPort {

    List<AuthorResponse> getAllAuthors();
}
