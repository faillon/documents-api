package org.faillon.documentsapi.application.service;

import lombok.RequiredArgsConstructor;
import org.faillon.documentsapi.application.port.in.document.DocumentUseCase;
import org.faillon.documentsapi.application.port.out.document.DocumentPort;
import org.faillon.documentsapi.common.dto.document.CreateDocumentRequest;
import org.faillon.documentsapi.common.dto.document.DocumentResponse;
import org.faillon.documentsapi.common.dto.document.UpdateDocumentRequest;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentService implements DocumentUseCase {

    private final DocumentPort documentPort;

    @Override
    public List<DocumentResponse> getAllDocuments() {
        return documentPort.getAllDocuments();
    }

    @Override
    public DocumentResponse getDocument(Long documentId) {
        return documentPort.getDocument(documentId);
    }

    @Override
    public DocumentResponse updateDocument(Long documentId, UpdateDocumentRequest documentRequest) {
        return documentPort.updateDocument(documentId, documentRequest);
    }

    @Override
    public DocumentResponse createDocument(CreateDocumentRequest documentRequest) {
        return documentPort.createDocument(documentRequest);
    }

    @Override
    public void deleteDocument(Long documentId) {
        documentPort.deleteDocument(documentId);
    }
}
