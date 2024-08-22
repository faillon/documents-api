package org.faillon.documentsapi.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.faillon.documentsapi.common.dto.author.AuthorResponse;
import java.util.List;
import java.util.Set;

@Getter @Setter
@Builder
public class Document {

    private Long id;

    private String title;
    private String body;
    Set<AuthorResponse> authors;
    List<String > references;


}
