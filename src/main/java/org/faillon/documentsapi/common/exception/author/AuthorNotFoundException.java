package org.faillon.documentsapi.common.exception.author;

public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException(Long authorId) {
        super("Author Not Found With ID: " + authorId);
    }
}
