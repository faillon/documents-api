package org.faillon.documentsapi.application.port.in.author;

import org.faillon.documentsapi.common.dto.author.AuthorResponse;
import org.faillon.documentsapi.common.dto.author.UpdateAuthorRequest;

public interface UpdateAuthorUseCase {

    AuthorResponse updateAuthor(Long authorId, UpdateAuthorRequest request);
}
