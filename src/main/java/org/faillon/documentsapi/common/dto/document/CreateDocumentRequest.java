package org.faillon.documentsapi.common.dto.document;

import lombok.*;
import org.faillon.documentsapi.common.dto.author.AuthorResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Setter @Getter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class CreateDocumentRequest {

    private String title;
    private String body;
    List<AuthorResponse> authors = new ArrayList<>();
    String references;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateDocumentRequest that = (CreateDocumentRequest) o;
        return title.equals(that.title) && body.equals(that.body) && authors.equals(that.authors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, body, authors);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CreateDocumentRequest{");
        sb.append("title='").append(title).append('\'');
        sb.append(", body='").append(body).append('\'');
        sb.append(", authors=").append(authors);
        sb.append(", references=").append(references);
        sb.append('}');
        return sb.toString();
    }
}
