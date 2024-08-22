package org.faillon.documentsapi.application.port.in.author;

import org.faillon.documentsapi.common.dto.author.AuthorResponse;

public interface GetAuthorUseCase {

    AuthorResponse getAuthor(Long authorId);

}
