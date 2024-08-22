package org.faillon.documentsapi.adapter.out.persistence.document;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.faillon.documentsapi.application.port.out.document.DocumentPort;
import org.faillon.documentsapi.common.dto.document.CreateDocumentRequest;
import org.faillon.documentsapi.common.dto.document.DocumentResponse;
import org.faillon.documentsapi.common.dto.document.UpdateDocumentRequest;
import org.faillon.documentsapi.common.exception.document.DocumentNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Component
@Slf4j
@RequiredArgsConstructor
public class DocumentAdapter implements DocumentPort {

    Logger logger = LoggerFactory.getLogger(DocumentAdapter.class);

    private final DocumentRepository documentRepository;
    private Function<DocumentEntity, DocumentResponse> mapperToDto = DocumentResponse::new;
    @Override
    public List<DocumentResponse> getAllDocuments() {
        List<DocumentEntity> entityList = documentRepository.findAll();

        List<DocumentResponse> documentResponseList = entityList.stream()
                .map(mapperToDto)
                .toList();

        return documentResponseList;
    }

    @Override
    public DocumentResponse getDocument(Long documentId) {

        Optional<DocumentEntity> entity = documentRepository.findById(documentId);

        if(entity.isEmpty()) {
            throw new DocumentNotFoundException(documentId);
        }

        return DocumentMapper.entityToResponse(entity.get());
    }

    @Override
    public DocumentResponse updateDocument(Long documentId, UpdateDocumentRequest documentRequest) {
        logger.info("Request Received: " + documentRequest);
        getDocument(documentId);

        DocumentEntity entity = DocumentMapper.updateRequestToEntity(documentRequest);
        entity.setId(documentId);
        documentRepository.save(entity);

        DocumentResponse response = DocumentResponse.builder()
                .id(documentId)
                .title(documentRequest.getTitle())
                .body(documentRequest.getBody())
                .authors(documentRequest.getAuthors())
                .references(documentRequest.getReferences())
                .build();

        return response;
    }

    @Override
    public DocumentResponse createDocument(CreateDocumentRequest documentRequest) {
        DocumentEntity entity = DocumentMapper.createRequestToEntity(documentRequest);

        DocumentEntity entityCreated = documentRepository.save(entity);
        entity.setId(entityCreated.getId());

        DocumentResponse response = DocumentMapper.entityToResponse(entity);
        return response;
    }

    @Override
    public void deleteDocument(Long documentId) {

        getDocument(documentId);
        documentRepository.deleteById(documentId);
    }
}
