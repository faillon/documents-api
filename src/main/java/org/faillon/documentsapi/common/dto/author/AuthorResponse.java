package org.faillon.documentsapi.common.dto.author;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.faillon.documentsapi.adapter.out.persistence.author.AuthorEntity;

import java.util.Objects;

@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class AuthorResponse {

    @NotNull(message = "id property is required")
    private Long id;

    @NotEmpty(message = "name property is required")
    private String name;

    @NotEmpty(message = "last_name property is required")
    @JsonProperty("last_name")
    private String lastName;

    public AuthorResponse(AuthorEntity authorEntity) {
        this.id = authorEntity.getId();
        this.name = authorEntity.getName();
        this.lastName = authorEntity.getLastName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorResponse that = (AuthorResponse) o;
        return id.equals(that.id) && name.equals(that.name) && lastName.equals(that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AuthorResponse{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
