package org.faillon.documentsapi.adapter.out.persistence.author;

import jakarta.persistence.*;
import lombok.*;
import org.faillon.documentsapi.adapter.out.persistence.document.DocumentEntity;
import org.faillon.documentsapi.common.dto.author.AuthorResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "authors")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class AuthorEntity {

    @Id
    @Column(name = "author_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "author_name")
    private String name;
    @Column(name = "last_name")
    private String lastName;

    @ManyToMany(mappedBy = "authors", cascade = CascadeType.REMOVE)
    private List<DocumentEntity> documents = new ArrayList<>();

    public AuthorEntity(AuthorResponse authorResponse) {
        this.id = authorResponse.getId();
        this.name = authorResponse.getName();
        this.lastName = authorResponse.getLastName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorEntity that = (AuthorEntity) o;
        return name.equals(that.name) && lastName.equals(that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AuthorEntity{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", documents=").append(documents);
        sb.append('}');
        return sb.toString();
    }
}
