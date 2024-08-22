package org.faillon.documentsapi.common.exception.document;

public class DocumentNotFoundException extends RuntimeException {

    public DocumentNotFoundException(Long documentId) {
        super("Document Not Found With ID: " + documentId);
    }
}
