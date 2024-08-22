package org.faillon.documentsapi.adapter.out.persistence.document;

import jakarta.persistence.*;
import lombok.*;
import org.faillon.documentsapi.adapter.out.persistence.author.AuthorEntity;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "documents")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class DocumentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_id")
    private Long id;
    private String title;
    private String body;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "documents_authors",
            joinColumns = @JoinColumn(name = "document_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<AuthorEntity> authors = new ArrayList<>();
    @Column(name = "reference")
    private String references;
}
