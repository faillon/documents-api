package org.faillon.documentsapi.common.dto.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.faillon.documentsapi.common.dto.author.AuthorResponse;

import java.util.Set;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class DocumentRequest {
    private String title;
    private String body;
    Set<AuthorResponse> authors;
    String references;

}
