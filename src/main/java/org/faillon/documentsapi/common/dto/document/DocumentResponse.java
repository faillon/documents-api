package org.faillon.documentsapi.common.dto.document;

import lombok.*;
import org.faillon.documentsapi.adapter.out.persistence.author.AuthorEntity;
import org.faillon.documentsapi.adapter.out.persistence.document.DocumentEntity;
import org.faillon.documentsapi.common.dto.author.AuthorResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class DocumentResponse {
    private Long id;
    private String title;
    private String body;
    List<AuthorResponse> authors = new ArrayList<>();
    String references;

    private static Function<AuthorEntity, AuthorResponse> mapper = AuthorResponse::new;

    public DocumentResponse(DocumentEntity documentEntity) {
        this.id = documentEntity.getId();
        this.title = documentEntity.getTitle();
        this.body = documentEntity.getBody();
        this.authors = documentEntity.getAuthors().stream().map(mapper).toList();
        this.references = documentEntity.getReferences();
    }
}
