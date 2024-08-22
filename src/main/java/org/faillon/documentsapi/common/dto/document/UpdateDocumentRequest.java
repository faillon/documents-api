package org.faillon.documentsapi.common.dto.document;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.faillon.documentsapi.common.dto.author.AuthorResponse;
import java.util.List;
import java.util.Objects;

@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class UpdateDocumentRequest {

    @NotEmpty(message = "title property is required")
    private String title;

    @NotEmpty(message = "body property is required")
    private String body;
    @NotEmpty(message = "authors list property cannot be empty")
    List<@Valid AuthorResponse> authors;
    String references;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateDocumentRequest that = (UpdateDocumentRequest) o;
        return title.equals(that.title) && body.equals(that.body) && authors.equals(that.authors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, body, authors);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UpdateDocumentRequest{");
        sb.append("title='").append(title).append('\'');
        sb.append(", body='").append(body).append('\'');
        sb.append(", authors=").append(authors);
        sb.append(", references='").append(references).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
