package org.faillon.documentsapi.application.port.out.author;

import org.faillon.documentsapi.common.dto.author.AuthorResponse;
import org.faillon.documentsapi.common.dto.author.CreateAuthorRequest;

public interface CreateAuthorPort {

    AuthorResponse createAuthor(CreateAuthorRequest authorRequest);
}
