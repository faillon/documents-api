package org.faillon.documentsapi.adapter.out.persistence.document;

import org.faillon.documentsapi.adapter.out.persistence.author.AuthorEntity;
import org.faillon.documentsapi.common.dto.author.AuthorResponse;
import org.faillon.documentsapi.common.dto.document.CreateDocumentRequest;
import org.faillon.documentsapi.common.dto.document.DocumentResponse;
import org.faillon.documentsapi.common.dto.document.UpdateDocumentRequest;

import java.util.function.Function;

public class DocumentMapper {

    private static Function<AuthorResponse, AuthorEntity> authorMapperToEntity = AuthorEntity::new;
    private static Function<AuthorEntity, AuthorResponse> authorMapperToDto = AuthorResponse::new;

    static DocumentEntity createRequestToEntity (CreateDocumentRequest request) {
        DocumentEntity entity = DocumentEntity.builder()
                .title(request.getTitle())
                .body(request.getBody())
                .authors(request.getAuthors().stream().map(authorMapperToEntity).toList())
                .references(request.getReferences())
                .build();

        return entity;
    }

    static DocumentResponse entityToResponse(DocumentEntity entity) {
        DocumentResponse response = DocumentResponse.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .body(entity.getBody())
                .authors(entity.getAuthors().stream().map(authorMapperToDto).toList())
                .references(entity.getReferences())
                .build();

        return response;
    }

    static DocumentEntity updateRequestToEntity(UpdateDocumentRequest request) {
        DocumentEntity entity = DocumentEntity.builder()
                .title(request.getTitle())
                .body(request.getBody())
                .authors(request.getAuthors().stream().map(authorMapperToEntity).toList())
                .references(request.getReferences())
                .build();

        return entity;
    }
}
