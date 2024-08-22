package org.faillon.documentsapi.application.port.out.author;

import org.faillon.documentsapi.common.dto.author.AuthorResponse;

public interface GetAuthorPort {

    AuthorResponse getAuthor(Long authorId);
}
