package org.faillon.documentsapi.application.port.out.author;

import org.faillon.documentsapi.common.dto.author.AuthorResponse;
import org.faillon.documentsapi.common.dto.author.UpdateAuthorRequest;

public interface UpdateAuthorPort {

    AuthorResponse updateAuthor(Long authorId, UpdateAuthorRequest request);
}
