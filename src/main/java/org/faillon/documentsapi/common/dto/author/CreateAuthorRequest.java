package org.faillon.documentsapi.common.dto.author;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.Objects;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class CreateAuthorRequest {

    @NotEmpty(message = "name property cannot be empty")
    private String name;
    @JsonProperty("last_name")
    @NotEmpty(message = "last_name property cannot be empty")
    private String lastName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateAuthorRequest that = (CreateAuthorRequest) o;
        return name.equals(that.name) && lastName.equals(that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CreateAuthorRequest{");
        sb.append("name='").append(name).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
