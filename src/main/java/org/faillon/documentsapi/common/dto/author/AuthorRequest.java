package org.faillon.documentsapi.common.dto.author;

import lombok.*;

import java.util.Objects;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class AuthorRequest {

    private Long id;
    private String name;
    private String lastName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorRequest that = (AuthorRequest) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AuthorRequest{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
