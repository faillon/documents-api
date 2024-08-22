package org.faillon.documentsapi.common.dto.author;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.Objects;

@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class UpdateAuthorRequest {

    @NotEmpty(message = "name property cannot be empty")
    private String name;
    @NotEmpty(message = "last_name property cannot be empty")
    private String lastName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateAuthorRequest that = (UpdateAuthorRequest) o;
        return name.equals(that.name) && lastName.equals(that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UpdateAuthorRequest{");
        sb.append("name='").append(name).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
