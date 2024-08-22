package org.faillon.documentsapi.application.port.in.author;

import org.faillon.documentsapi.common.dto.author.AuthorResponse;
import org.faillon.documentsapi.common.dto.author.CreateAuthorRequest;

public interface CreateAuthorUseCase {

    AuthorResponse createAuthor(CreateAuthorRequest authorRequest);
}
