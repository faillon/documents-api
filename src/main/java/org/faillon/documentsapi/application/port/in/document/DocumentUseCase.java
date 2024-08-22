package org.faillon.documentsapi.application.port.in.document;

import org.faillon.documentsapi.common.dto.document.CreateDocumentRequest;
import org.faillon.documentsapi.common.dto.document.DocumentResponse;
import org.faillon.documentsapi.common.dto.document.UpdateDocumentRequest;
import java.util.List;

public interface DocumentUseCase {

    List<DocumentResponse> getAllDocuments();
    DocumentResponse getDocument(Long documentId);
    DocumentResponse updateDocument(Long documentId, UpdateDocumentRequest documentRequest);
    DocumentResponse createDocument(CreateDocumentRequest documentRequest);
    void deleteDocument(Long documentId);

}
